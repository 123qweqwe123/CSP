package com.lmbx.csp.dispatch.service.download.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.lmbx.csp.constant.DictTypeConsts;
import com.lmbx.csp.core.utils.CryptoUtil;
import com.lmbx.csp.core.utils.EncodeUtils;
import com.lmbx.csp.data.conf.domain.*;
import com.lmbx.csp.data.conf.mapper.CspConfCertMapper;
import com.lmbx.csp.data.conf.mapper.CspConfCheckinMapper;
import com.lmbx.csp.data.conf.mapper.CspConfVisitorMapper;
import com.lmbx.csp.data.conf.mapper.CspConferenceMapper;
import com.lmbx.csp.data.sys.domain.SysDataIssueVersion;
import com.lmbx.csp.data.sys.domain.SysDataIssueVersionExample;
import com.lmbx.csp.data.sys.mapper.SysDataIssueVersionMapper;
import com.lmbx.csp.dispatch.service.download.IDataDownload;
import com.lmbx.csp.dispatch.service.download.dto.VersionDTO;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wanghongwei
 */
@Service
public abstract class AbstractDataDownLoad implements IDataDownload {

    private Logger                    logger = LoggerFactory.getLogger(AbstractDataDownLoad.class);

    @Autowired
    private CspConferenceMapper       cspConferenceMapper;

    @Autowired
    private CspConfVisitorMapper      cspConfVisitorMapper;

    @Autowired
    private CspConfCheckinMapper      cspConfCheckinMapper;

    @Autowired
    private CspConfCertMapper         cspConfCertMapper;

    @Autowired
    private SysDataIssueVersionMapper sysDataIssueVersionMapper;

    @Value("${third-service.fastdfs.tracker-servers}")
    String                            trackerServers;

    /**
     * 版本号查询
     *
     * @param vervionDto
     * @return
     */
    @Override
    public String getVersion(VersionDTO vervionDto) {
        String dictType = vervionDto.getDictType();
        String confNo = vervionDto.getConfNo();
        String projectId = vervionDto.getProjectId();
        SysDataIssueVersionExample example = new SysDataIssueVersionExample();
        // dictType为0或者1或者98
        if (DictTypeConsts.PARA_TYPE.equals(dictType) || DictTypeConsts.CONFERENCELIST_TYPE.equals(dictType)
            || DictTypeConsts.CONFERENCEPC_TYPE.equals(dictType)) {
            example.createCriteria().andDictTypeEqualTo(dictType).andProjectIdEqualTo(projectId);
            List<SysDataIssueVersion> list = this.sysDataIssueVersionMapper.selectByExample(example);
            if (list.size() > 0) {
                String dictVersion = list.get(0).getDictVersion();
                return dictVersion;
            }
        }
        if (DictTypeConsts.CONFERENCEMOBILE_TYPE.equals(dictType)) {
            ObjectMapper objectMapper = new ObjectMapper();
            example.createCriteria().andDictTypeEqualTo(dictType).andProjectIdEqualTo(projectId);
            List<SysDataIssueVersion> list = this.sysDataIssueVersionMapper.selectByExample(example);
            try {
                if (list.size() > 0) {
                    SysDataIssueVersion version = list.get(0);
                    Map map = new HashMap<>(16);
                    map.put("versionCode", version.getValue4());
                    map.put("versionName", version.getValue2());
                    map.put("length", version.getValue1());
                    map.put("remark", version.getValue3());
                    objectMapper.writeValueAsString(map);
                    return objectMapper.writeValueAsString(map);
                }
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        // dictType为2
        else if (DictTypeConsts.CONFERENCEBASEINFO_TYPE.equals(dictType)) {
            List<CspConference> conferenceData = getConferenceData(confNo);
            if (conferenceData.size() > 0) {
                List<SysDataIssueVersion> versionData = getVersionData(vervionDto);
                if (versionData.size() > 0) {
                    return versionData.get(0).getDictVersion();
                } else {
                    return "0";
                }
            } else {
                return "-1";
            }
        }
        // dictType为3
        else if (DictTypeConsts.CONFERENCEREG_TYPE.equals(dictType)) {
            List<CspConference> conferenceData = getConferenceData(confNo);
            if (conferenceData.size() > 0) {
                String confId = conferenceData.get(0).getId();
                // 判断登记来宾表数据
                List<CspConfVisitor> visitorData = getVisitorData(confId);
                if (visitorData.size() > 0) {
                    List<SysDataIssueVersion> versionData = getVersionData(vervionDto);
                    if (versionData.size() > 0) {
                        return versionData.get(0).getDictVersion();
                    } else {
                        return "0";
                    }

                } else {
                    return "-2";
                }
            } else {
                return "-1";
            }
        }
        // dictType为4
        else if (DictTypeConsts.CONFERENCECHECK_TYPE.equals(dictType)) {
            List<CspConference> conferenceData = getConferenceData(confNo);
            if (conferenceData.size() > 0) {
                String confId = conferenceData.get(0).getId();
                List<CspConfCheckin> checkInData = getCheckInData(confId);
                // 判断签到表数据
                if (checkInData.size() > 0) {
                    List<SysDataIssueVersion> versionData = getVersionData(vervionDto);
                    if (versionData.size() > 0) {
                        return versionData.get(0).getDictVersion();
                    } else {
                        return "0";
                    }
                } else {
                    return "-2";
                }
            } else {
                return "-1";
            }
        }
        // dictType为5证件版本
        else if (DictTypeConsts.CONFERENCEIDENTITY_TYPE.equals(dictType)) {
            List<CspConference> conferenceData = getConferenceData(confNo);
            if (conferenceData.size() > 0) {
                String confId = conferenceData.get(0).getId();
                List<CspConfCert> cspConfCertData = getIdentityData(confId);
                // 判断签到表数据
                if (cspConfCertData.size() > 0) {
                    List<SysDataIssueVersion> versionData = getVersionData(vervionDto);
                    if (versionData.size() > 0) {
                        return versionData.get(0).getDictVersion();
                    } else {
                        return "0";
                    }
                } else {
                    return "-2";
                }
            } else {
                return "-1";
            }
        }
        return null;
    }

    /**
     * 根据会议编号查询会议表数据
     *
     * @param confNo
     * @return
     */
    public List<CspConference> getConferenceData(String confNo) {
        CspConferenceExample conferenceExample = new CspConferenceExample();
        conferenceExample.createCriteria().andConfNoEqualTo(confNo);
        List<CspConference> conferenceList = cspConferenceMapper.selectByExample(conferenceExample);
        return conferenceList;

    }

    /**
     * 查询登记会议来宾表数据
     *
     * @param confId
     * @return
     */
    private List<CspConfVisitor> getVisitorData(String confId) {
        CspConfVisitorExample example = new CspConfVisitorExample();
        example.createCriteria().andConfIdEqualTo(confId);
        List<CspConfVisitor> visitorList = cspConfVisitorMapper.selectByExample(example);
        return visitorList;
    }

    /**
     * 获得签到表数据
     *
     * @param confId
     * @return
     */
    List<CspConfCheckin> getCheckInData(String confId) {
        CspConfCheckinExample example = new CspConfCheckinExample();
        example.createCriteria().andConfIdEqualTo(confId);
        List<CspConfCheckin> checkInList = cspConfCheckinMapper.selectByExample(example);
        return checkInList;
    }

    /**
     * 获得证件表数据
     *
     * @param confId
     * @return
     */
    List<CspConfCert> getIdentityData(String confId) {
        CspConfCertExample example = new CspConfCertExample();
        example.createCriteria().andConfIdEqualTo(confId);
        List<CspConfCert> cspConfCertList = cspConfCertMapper.selectByExample(example);
        return cspConfCertList;
    }

    /**
     * 获得版本号数据
     *
     * @param vervionDto
     * @return
     */

    List<SysDataIssueVersion> getVersionData(VersionDTO vervionDto) {
        String dictType = vervionDto.getDictType();
        String confNo = vervionDto.getConfNo();
        String projectId = vervionDto.getProjectId();
        SysDataIssueVersionExample example = new SysDataIssueVersionExample();
        example.createCriteria().andDictTypeEqualTo(dictType).andLccCodeEqualTo(confNo).andProjectIdEqualTo(projectId);
        List<SysDataIssueVersion> versionList = this.sysDataIssueVersionMapper.selectByExample(example);
        return versionList;
    }

    /**
     * 获得压缩包数据
     *
     * @param vervionDto
     * @return
     */

    @Override
    public byte[] getData(VersionDTO vervionDto) {
        // 是否需要压缩
        if (needZip()) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ZipOutputStream zos = new ZipOutputStream(baos);
            zos.setEncoding("UTF-8");
            List<EntityInZip> entitys = this.getEntitys(vervionDto);
            try {
                if (entitys == null) {
                    zos.close();
                    return null;
                }
                for (EntityInZip entityInZip : entitys) {
                    ZipEntry entry = new ZipEntry(entityInZip.entityName);
                    byte[] entryData = entityInZip.entityData;
                    // 数据加密
                    if (this.needEncode()) {
                        entryData = CryptoUtil.encrypt(getEncodeKey(vervionDto), entryData);
                        entryData = EncodeUtils.base64Encode(entryData).getBytes("utf-8");
                    }
                    entry.setSize(entryData.length);
                    // 目录755 文件644
                    entry.setUnixMode(644);
                    zos.putNextEntry(entry);
                    zos.write(entryData);
                    zos.closeEntry();
                }
                zos.close();
                byte[] val = baos.toByteArray();
                return val;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (baos != null) {
                        baos.close();
                    }
                } catch (IOException e) {
                }
            }
        }
        return null;
    }

    /**
     * 得到需要压缩到 压缩包的文件
     *
     * @param vervionDto
     * @return
     */
    public List<EntityInZip> getEntitys(VersionDTO vervionDto) {
        List<EntityInZip> returnList = new ArrayList<EntityInZip>();
        return returnList;
    }

    @PostConstruct
    public void init() {
        try {
            ClientGlobal.initByTrackers(trackerServers);
            logger.info("系统文件服务初始化成功。");
        } catch (Exception e) {
            logger.error("文件服务器初始化异常", e);
            e.printStackTrace();
        }
    }

    /**
     * 从文件服务器获取文件数据
     *
     * @param vervionDto
     * @return
     */
    @Override
    public Map<String, Object> fileByteFromFS(VersionDTO vervionDto) {
        String dictType = vervionDto.getDictType();
        SysDataIssueVersionExample example = new SysDataIssueVersionExample();
        example.createCriteria().andDictTypeEqualTo(dictType);
        List<SysDataIssueVersion> list = sysDataIssueVersionMapper.selectByExample(example);
        SysDataIssueVersion version = list.get(0);
        if (version == null) {
            return null;
        }
        try {
            TrackerClient tracker = new TrackerClient();
            TrackerServer trackerServer = tracker.getConnection();
            StorageClient1 client = new StorageClient1(trackerServer, null);
            byte[] body = client.download_file1(version.getFileId());
            NameValuePair[] metaData1 = client.get_metadata1(version.getFileId());
            Map<String, Object> result = Maps.newHashMap();
            result.put("meta", metaData1);
            result.put("body", body);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 是否需要加密
     *
     * @return
     */
    public abstract boolean needEncode();

    /**
     * 是否需要做压缩
     *
     * @return
     */
    public abstract boolean needZip();

    /**
     * 获取数据解密的key 需要加密的数据 需要调用此方法。 默认使用项目id作为key ，
     *
     * @param vervionDto
     * @return
     */
    protected String getEncodeKey(VersionDTO vervionDto) {
        return vervionDto.getProjectId();
    }

    public static class EntityInZip {

        public String entityName;
        public byte[] entityData;
    }

    void addEntity(String entityName, String entityContent,
                   List<EntityInZip> returnList) throws UnsupportedEncodingException {
        EntityInZip entity = new EntityInZip();
        entity.entityName = entityName;
        byte[] bytes = entityContent.getBytes("UTF-8");
        entity.entityData = bytes;
        returnList.add(entity);
    }
}
