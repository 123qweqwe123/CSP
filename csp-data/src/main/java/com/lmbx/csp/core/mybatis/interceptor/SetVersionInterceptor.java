package com.lmbx.csp.core.mybatis.interceptor;

import com.google.common.base.CaseFormat;
import com.google.common.collect.Maps;
import com.lmbx.csp.core.utils.Identities;
import com.lmbx.csp.core.utils.SpringContextHolder;
import com.lmbx.csp.data.conf.domain.*;
import com.lmbx.csp.data.conf.mapper.*;
import com.lmbx.csp.data.sys.domain.SysDataIssueVersion;
import com.lmbx.csp.data.sys.domain.SysDataIssueVersionExample;
import com.lmbx.csp.data.sys.mapper.SysDataIssueVersionMapper;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Description:
 * 
 * <pre>
 *     拦截 sql 语句，设置版本号
 * </pre>
 *
 * @author javahuang
 * @since 2017/12/8 上午10:51
 */
@Component
@Intercepts({ @Signature(type = StatementHandler.class, method = "update", args = { Statement.class }) })
public class SetVersionInterceptor implements Interceptor {

    Logger                      log              = LoggerFactory.getLogger(SetVersionInterceptor.class);
    SetVersionInterceptorUtils  interceptorUtils = new SetVersionInterceptorUtils();

    private Map<String, String> confId2ConfNo    = Maps.newConcurrentMap();
    private Map<String, String> checkinId2ConfNo = Maps.newConcurrentMap();
    private Map<String, String> certId2ConfNo = Maps.newConcurrentMap();

    private Map<String, String> confNo2Type2     = Maps.newConcurrentMap();
    private Map<String, String> confNo2Type3     = Maps.newConcurrentMap();
    private Map<String, String> confNo2Type4     = Maps.newConcurrentMap();

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        RoutingStatementHandler handler = (RoutingStatementHandler) invocation.getTarget();
        String sql = handler.getBoundSql().getSql();
        Object params = handler.getParameterHandler().getParameterObject();
        List<ParameterMapping> parameterMappingList = handler.getBoundSql().getParameterMappings();
        try {
            processStatement(sql, params, parameterMappingList);
        } catch (Exception e) {
            log.error("mybatis-interceptor-set-version-fail", e);
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

    /**
     * 会议列表
     */
    private static final String   DICT_1_TYPE           = "1";

    /**
     * 基本信息 通过 confId 字段来获取 confNo
     */
    private static final String[] DICT_2_TABLE_NAMES    = { "CSP_CONFERENCE", "CSP_CONF_WORKER" };

    private static final String   DICT_2_NAME           = "会议基本信息";
    private static final String   DICT_2_TYPE           = "2";

    /**
     * 登记信息 通过 confId 来获取 confNo
     */
    private static final String[] DICT_3_TABLE_NAMES    = { "CSP_CONF_REGISTER", "CSP_CONF_VISITOR",
                                                            "CSP_CONF_LECTURER" };
    private static final String   DICT_3_NAME           = "会议登记信息";
    private static final String   DICT_3_TYPE           = "3";

    /**
     * 会议签到信息 通过 checkinId -> confId -> confNo
     */
    private static final String[] DICT_4_TABLE_NAMES    = { "CSP_CONF_CHECKIN", "CSP_CONF_CHECKIN_PERSON",
                                                            "CSP_CONF_CHECKIN_WORKER", "CSP_CONF_CHECKIN_RECORD" };
    private static final String   DICT_4_NAME           = "会议签到信息";
    private static final String   DICT_4_TYPE           = "4";
    
    /**
     * 证件模板  通过 (certId ->)? confId -> confNo
     */
    private static final String[] DICT_5_TABLE_NAMES    = { "CSP_CONF_CERT", "CSP_CONF_CERT_ATTRIBUTE" };
    private static final String   DICT_5_NAME           = "证件模板";
    private static final String   DICT_5_TYPE           = "5";

    private static final String   TABLE_CSP_MAIN_PERSON = "CSP_MAIN_PERSON";

    private void processStatement(String sql, Object param,
                                  List<ParameterMapping> parameterMappingList) throws IllegalAccessException {
        // 判断 sql 是否位于
        String tableName = null;
        Map<String, String> dataMapBySelect = new HashMap<String, String>();
        String[] sqlGrammar = sql.toUpperCase().split("\\s+");
        if (StringUtils.contains(sqlGrammar[0], "UPDATE")) {
            tableName = sqlGrammar[1];
        } else if (StringUtils.contains(sqlGrammar[0], "INSERT")) {
            tableName = sqlGrammar[2];
        } else if (StringUtils.contains(sqlGrammar[0], "DELETE")) {
            tableName = sqlGrammar[2];
            if (tableName != null && tableName.contains(".")) {
                tableName = StringUtils.substringAfter(tableName, ".");
            }
            if (!ArrayUtils.contains(DICT_2_TABLE_NAMES, tableName)
                && !ArrayUtils.contains(DICT_3_TABLE_NAMES, tableName)
                && !ArrayUtils.contains(DICT_4_TABLE_NAMES, tableName)
                && !ArrayUtils.contains(DICT_5_TABLE_NAMES, tableName)) {
                return;
            }
            String sqlWithParam = interceptorUtils.formatSql(sql, param, parameterMappingList).toLowerCase();
            String querySql = sqlWithParam.replace("delete", "select * ");
            DataSource dataSource = SpringContextHolder.getBean(DataSource.class);
            dataMapBySelect = interceptorUtils.getData(querySql, dataSource);
        }
        // 防止有些表生成 mybatis 文件时带有 schema
        if (tableName != null && tableName.contains(".")) {
            tableName = StringUtils.substringAfter(tableName, ".");
        }
        String confNo = null;
        String type = null;
        String dictName = null;
        String idField = "id";
        String confNoField = "confNo";
        String confIdField = "confId";
        String checkinIdField = "checkinId";
        String certIdField = "certId";
        if (ArrayUtils.contains(DICT_2_TABLE_NAMES, tableName)) {
            type = DICT_2_TYPE;
            dictName = DICT_2_NAME;
            if (DICT_2_TABLE_NAMES[0].equals(tableName)) {
                String confNoValue = getFiledValue(param, dataMapBySelect, confNoField);
                if (confNoValue != null) {
                    confNo = confNoValue;
                } else {
                    String fieldValue = getFiledValue(param, dataMapBySelect, idField);
                    confNo = getConfNo(fieldValue);
                }
            } else {
                String fieldValue = getFiledValue(param, dataMapBySelect, confIdField);
                confNo = getConfNo(fieldValue);
            }
        } else if (ArrayUtils.contains(DICT_3_TABLE_NAMES, tableName)) {
            type = DICT_3_TYPE;
            dictName = DICT_3_NAME;
            String fieldValue = getFiledValue(param, dataMapBySelect, confIdField);
            confNo = getConfNo(fieldValue);
        } else if (ArrayUtils.contains(DICT_4_TABLE_NAMES, tableName)) {
            type = DICT_4_TYPE;
            dictName = DICT_4_NAME;
            if (DICT_4_TABLE_NAMES[0].equals(tableName)) {
                String fieldValue = getFiledValue(param, dataMapBySelect, confIdField);
                confNo = getConfNo(fieldValue);
            } else {
                String fieldValue = getFiledValue(param, dataMapBySelect, certIdField);
                confNo = getConfNoByCheckinId(fieldValue);
            }
        }  else if (ArrayUtils.contains(DICT_5_TABLE_NAMES, tableName)) {
            type = DICT_5_TYPE;
            dictName = DICT_5_NAME;
            if (DICT_5_TABLE_NAMES[0].equals(tableName)) {
                String fieldValue = getFiledValue(param, dataMapBySelect, confIdField);
                confNo = getConfNo(fieldValue);
            } else {
                String fieldValue = getFiledValue(param, dataMapBySelect, checkinIdField);
                confNo = getConfNoByCertId(fieldValue);
            }
        } else if (TABLE_CSP_MAIN_PERSON.equals(tableName)) {
            // 更新所有 csp_main_person 关联表
            String personId = getFiledValue(param, dataMapBySelect, idField);
            updateMainPerson(personId);
        }
        setVersion(confNo, type, dictName);

        if (DICT_2_TABLE_NAMES[0].equals(tableName) && !StringUtils.contains(sqlGrammar[0], "UPDATE")) {
            updateDictType1();
        }
    }

    /**
     * 当有添加或者删除会议时，会修改会议的版本号
     */
    private void updateDictType1() {
        SysDataIssueVersionMapper versionMapper = SpringContextHolder.getBean(SysDataIssueVersionMapper.class);
        SysDataIssueVersionExample exp = new SysDataIssueVersionExample();
        exp.createCriteria().andDictTypeEqualTo(DICT_1_TYPE);
        List<SysDataIssueVersion> versions = versionMapper.selectByExample(exp);
        SysDataIssueVersion version = new SysDataIssueVersion();
        if (versions.size() == 1) {
            // 更新
            String currentVersion = getVersion(versions.get(0).getDictVersion());
            version.setDictVersion(currentVersion);
            versionMapper.updateByExampleSelective(version, exp);
        } else if (versions.size() == 0) {
            // 插入
            String currentVersion = getVersion(null);
            version.setDictVersion(currentVersion);
            version.setId(Identities.uuid());
            version.setDictType(DICT_1_TYPE);
            version.setDictName("会议列表");
            versionMapper.insertSelective(version);
        }
    }

    /**
     * 当修改人员信息时，所有关联该人员的会议的版本号都会发生变化
     * 
     * @param personId
     */
    private void updateMainPerson(String personId) {
        // 更新访客
        CspConfVisitorMapper confVisitor = SpringContextHolder.getBean(CspConfVisitorMapper.class);
        CspConfVisitorExample visitorExample = new CspConfVisitorExample();
        visitorExample.createCriteria().andVisitorIdEqualTo(personId);
        confVisitor.selectByExample(visitorExample).forEach(visitor -> {
            setVersion(getConfNo(visitor.getConfId()), DICT_3_TYPE, DICT_3_NAME);
        });
        // 更新工作人员
        CspConfWorkerMapper confWorker = SpringContextHolder.getBean(CspConfWorkerMapper.class);
        CspConfWorkerExample workerExample = new CspConfWorkerExample();
        workerExample.createCriteria().andWorkerIdEqualTo(personId);
        confWorker.selectByExample(workerExample).forEach(worker -> {
            setVersion(getConfNo(worker.getConfId()), DICT_2_TYPE, DICT_2_NAME);
        });
        // 更新讲师
        CspConfLecturerMapper confLecturer = SpringContextHolder.getBean(CspConfLecturerMapper.class);
        CspConfLecturerExample lecturerExample = new CspConfLecturerExample();
        lecturerExample.createCriteria().andConfLecturerIdEqualTo(personId);
        confLecturer.selectByExample(lecturerExample).forEach(lecturer -> {
            setVersion(getConfNo(lecturer.getConfId()), DICT_3_TYPE, DICT_3_NAME);
        });
        // 更新签到
        CspConfCheckinPersonMapper checkinMapper = SpringContextHolder.getBean(CspConfCheckinPersonMapper.class);
        CspConfCheckinPersonExample checkinExample = new CspConfCheckinPersonExample();
        checkinExample.createCriteria().andPersonIdEqualTo(personId);
        checkinMapper.selectByExample(checkinExample).forEach(checkin -> {
            setVersion(getConfNoByCheckinId(checkin.getCheckinId()), DICT_4_TYPE, DICT_4_NAME);
        });
    }

    private String getFiledValue(Object param, Map<String, String> dataMapBySelect, String confNoField) {
        String confNoValue = null;
        try {
            confNoValue = (String) FieldUtils.readDeclaredField(param, confNoField, true);
        } catch (Exception e) {
            String key = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, confNoField).toUpperCase();
            confNoValue = dataMapBySelect.get(key);
        }

        return confNoValue;
    }

    private String getConfNo(String confId) {
        if (confId == null) {
            return null;
        }
        String confNo = confId2ConfNo.get(confId);
        if (confNo == null) {
            CspConferenceMapper cspConferenceMapper = SpringContextHolder.getBean(CspConferenceMapper.class);
            CspConference conference = cspConferenceMapper.selectByPrimaryKey(confId);
            if (conference != null) {
                confId2ConfNo.put(confId, conference.getConfNo());
                confNo = conference.getConfNo();
            }
        }
        return confNo;
    }

    private String getConfNoByCheckinId(String checkinId) {
        if (checkinId == null) {
            return null;
        }
        String confNo = checkinId2ConfNo.get(checkinId);
        if (confNo == null) {
            CspConfCheckinMapper checkinMapper = SpringContextHolder.getBean(CspConfCheckinMapper.class);
            CspConfCheckin checkin = checkinMapper.selectByPrimaryKey(checkinId);
            if (checkin != null) {
                confNo = getConfNo(checkin.getConfId());
                checkinId2ConfNo.put(checkinId, confNo);
            }
        }
        return confNo;
    }
    
    private String getConfNoByCertId(String certId) {
        if (certId == null) {
            return null;
        }
        String confNo = certId2ConfNo.get(certId);
        if (confNo == null) {
            CspConfCertMapper certMapper = SpringContextHolder.getBean(CspConfCertMapper.class);
            CspConfCert cert = certMapper.selectByPrimaryKey(certId);
            if (cert != null) {
                confNo = getConfNo(cert.getConfId());
                certId2ConfNo.put(certId, confNo);
            }
        }
        return confNo;
    }

    private void setVersion(String confNo, String type, String dictName) {
        if (confNo == null || type == null) {
            return;
        }
        SysDataIssueVersionMapper versionMapper = SpringContextHolder.getBean(SysDataIssueVersionMapper.class);
        SysDataIssueVersion version = new SysDataIssueVersion();

        SysDataIssueVersionExample exp = new SysDataIssueVersionExample();
        exp.createCriteria().andLccCodeEqualTo(confNo).andDictTypeEqualTo(type);
        List<SysDataIssueVersion> versions = versionMapper.selectByExample(exp);
        if (versions.size() > 0) {
            version = versions.get(0);
        }
        String dictVersion = getVersion(version.getDictVersion());

        // 更新操作
        if (version.getDictVersion() != null) {
            version.setDictVersion(dictVersion);
            versionMapper.updateByPrimaryKeySelective(version);
        } else {
            version.setDictType(type);
            version.setDictVersion(dictVersion);
            version.setId(Identities.uuid());
            version.setProjectId("009");
            version.setDictName(dictName);
            version.setLccCode(confNo);
            versionMapper.insertSelective(version);
        }
    }

    private String getVersion(String prevVersion) {
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        String prefix = df.format(new Date());
        String currentVersion = null;
        // 更新操作
        if (prevVersion != null) {
            String dictVersionPrefix = prevVersion.substring(0, 8); // yyyyMMdd
            String dictVersionSuffix = prevVersion.substring(8, 12);
            if (dictVersionPrefix.equals(prefix)) {
                currentVersion = dictVersionPrefix + String.format("%04d", Integer.parseInt(dictVersionSuffix) + 1);
            } else {
                currentVersion = prefix + "0001";
            }
        } else {
            currentVersion = prefix + "0001";
        }
        return currentVersion;
    }
}
