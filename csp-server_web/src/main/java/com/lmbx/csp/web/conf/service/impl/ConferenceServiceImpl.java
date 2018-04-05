package com.lmbx.csp.web.conf.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lmbx.csp.core.constant.AppConsts;
import com.lmbx.csp.core.exception.ValidatorException;
import com.lmbx.csp.core.qrcode.PersonQRCodeUtils;
import com.lmbx.csp.core.utils.*;
import com.lmbx.csp.data.conf.domain.*;
import com.lmbx.csp.data.conf.mapper.*;
import com.lmbx.csp.data.main.domain.CspMainPerson;
import com.lmbx.csp.data.main.domain.CspMainPersonExample;
import com.lmbx.csp.data.main.mapper.CspMainPersonMapper;
import com.lmbx.csp.data.sys.domain.SysAccount;
import com.lmbx.csp.data.sys.domain.SysAccountExample;
import com.lmbx.csp.data.sys.domain.SysDataIssueVersionExample;
import com.lmbx.csp.data.sys.mapper.SysAccountMapper;
import com.lmbx.csp.data.sys.mapper.SysDataIssueVersionMapper;
import com.lmbx.csp.thirdservice.fdfs.FileService;
import com.lmbx.csp.web.conf.filter.*;
import com.lmbx.csp.web.conf.mapper.MyConferenceMapper;
import com.lmbx.csp.web.conf.service.ConferenceService;
import com.lmbx.csp.web.conf.vo.*;
import com.lmbx.csp.web.sys.service.SysService;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.jxls.reader.ReaderBuilder;
import org.jxls.reader.ReaderConfig;
import org.jxls.reader.XLSReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xml.sax.SAXException;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Description:
 * <p>
 * <p>
 * <p>
 * <pre>
 * </pre>
 *
 * @author javahuang
 * @since 2017/11/27 上午9:26
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ConferenceServiceImpl implements ConferenceService {

    private static Logger logger = LoggerFactory.getLogger(ConferenceServiceImpl.class);

    @Autowired
    private CspConferenceMapper conferenceMapper;

    @Autowired
    private MyConferenceMapper myConferenceMapper;

    @Autowired
    private SysDataIssueVersionMapper sysDataIssueVersionMapper;

    @Autowired
    private CspMainPersonMapper cspMainPersonMapper;

    @Autowired
    private CspConfWorkerMapper cspConfWorkerMapper;

    @Autowired
    private CspConfLecturerMapper cspConfLecturerMapper;

    @Autowired
    private CspConfRegisterMapper cspConfRegisterMapper;

    @Autowired
    private CspConfVisitorMapper cspConfVisitorMapper;

    @Autowired
    private CspConfCheckinMapper cspConfCheckinMapper;

    @Autowired
    private CspConfCheckinPersonMapper cspConfCheckinPersonMapper;

    @Autowired
    private CspConfCheckinWorkerMapper cspConfCheckinWorkerMapper;

    @Autowired
    private FileService fileService;

    @Autowired
    private Validator validator;

    @Autowired
    private SysService sysService;

    @Autowired
    private SysAccountMapper sysAccountMapper;

    @Autowired
    private CspConfPlaceMapper confPlaceMapper;

    @Autowired
    private CspConfPlaceRoomMapper confPlaceRoomMapper;

    @Autowired
    private CspConfFileMapper cspConfFileMapper;

    @Autowired
    private CspConfCourseMapper cspConfCourseMapper;

    @Autowired
    private CspConfCertMapper cspConfCertMapper;

    @Autowired
    private CspConfCertAttributeMapper cspConfCertAttributeMapper;

    @Autowired
    private CspConfCheckinRecordMapper cspConfCheckinRecordMapper;

    @Override
    public List<?> queryConferences(ConferenceFilter filter) {
        return myConferenceMapper.selectConferenceByFilter(filter);
    }

    @Override
    public void createConference(CspConference conference) {
        conference.setId(Utils.generateUUID());
        conference.setCreateBy(Securitys.getAccountId());
        conference.setCreateTime(new Date());
        conference.setStatus(AppConsts.CONF_STATUS_1);
        conferenceMapper.insertSelective(conference);
    }

    @Scheduled(cron = "0 1 0 * * ?")
    @Override
    public void setConfStatus() {
        logger.info("==========>setConfStatus begin");
        try {
            Date currentDate = DateConvertUtils.asUtilDate(LocalDate.now());
            // 将大于开始日期小于结束日期的会议的状态设置为2
            CspConferenceExample exp2 = new CspConferenceExample();
            exp2.createCriteria().andStartTimeLessThanOrEqualTo(currentDate).andEndTimeGreaterThanOrEqualTo(currentDate);
            CspConference conf2 = new CspConference();
            conf2.setStatus(AppConsts.CONF_STATUS_2);
            conferenceMapper.updateByExampleSelective(conf2, exp2);

            // 将大于结束日期的会议(1,2)状态设置为3
            CspConferenceExample exp3 = new CspConferenceExample();
            List<Short> status = new ArrayList<>();
            status.add(AppConsts.CONF_STATUS_1);
            status.add(AppConsts.CONF_STATUS_2);
            exp3.createCriteria().andStatusIn(status).andEndTimeLessThan(currentDate);
            CspConference conf3 = new CspConference();
            conf3.setStatus(AppConsts.CONF_STATUS_3);
            conferenceMapper.updateByExampleSelective(conf3, exp3);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("==========>end");
    }

    @Override
    public void updateConference(CspConference conference) {
        conference.setUpdateBy(Securitys.getAccountId());
        conference.setUpdateTime(new Date());
        conferenceMapper.updateByPrimaryKeySelective(conference);
    }

    @Override
    public void deleteConference(String id) {
        // 删除会议对应的版本
        CspConference cspConference = conferenceMapper.selectByPrimaryKey(id);
        String confNo = cspConference.getConfNo();
        // 删除会议
        conferenceMapper.deleteByPrimaryKey(id);
        // 删除签到和签到人员信息
        CspConfCheckinExample checkinExample = new CspConfCheckinExample();
        checkinExample.createCriteria().andConfIdEqualTo(id);
        cspConfCheckinMapper.selectByExample(checkinExample).forEach(checkin -> {
            CspConfCheckinPersonExample personExample = new CspConfCheckinPersonExample();
            personExample.createCriteria().andCheckinIdEqualTo(checkin.getId());
            cspConfCheckinPersonMapper.deleteByExample(personExample);
            CspConfCheckinWorkerExample workerExample = new CspConfCheckinWorkerExample();
            workerExample.createCriteria().andCheckinIdEqualTo(checkin.getId());
            cspConfCheckinWorkerMapper.deleteByExample(workerExample);
        });
        cspConfCheckinMapper.deleteByExample(checkinExample);
        // 删除会议的签到记录信息
        CspConfCheckinRecordExample example = new CspConfCheckinRecordExample();
        example.createCriteria().andConfIdEqualTo(id);
        cspConfCheckinRecordMapper.deleteByExample(example);
        // 删除课程
        CspConfCourseExample courseExample = new CspConfCourseExample();
        courseExample.createCriteria().andConfIdEqualTo(id);
        cspConfCourseMapper.deleteByExample(courseExample);
        // 删除工作人员
        CspConfWorkerExample workerExample = new CspConfWorkerExample();
        workerExample.createCriteria().andConfIdEqualTo(id);
        cspConfWorkerMapper.deleteByExample(workerExample);
        // 删除访客
        CspConfVisitorExample visitorExample = new CspConfVisitorExample();
        visitorExample.createCriteria().andConfIdEqualTo(id);
        cspConfVisitorMapper.deleteByExample(visitorExample);
        // 删除讲师
        CspConfLecturerExample lecturerExample = new CspConfLecturerExample();
        lecturerExample.createCriteria().andConfIdEqualTo(id);
        cspConfLecturerMapper.deleteByExample(lecturerExample);
        // 删除登记记录信息
        CspConfRegisterExample registerExample = new CspConfRegisterExample();
        registerExample.createCriteria().andConfIdEqualTo(id);
        cspConfRegisterMapper.deleteByExample(registerExample);
        // 删除模板
        CspConfCertExample certExample = new CspConfCertExample();
        certExample.createCriteria().andConfIdEqualTo(id);
        cspConfCertMapper.selectByExample(certExample).forEach(cert -> {
            CspConfCertAttributeExample attributeExample = new CspConfCertAttributeExample();
            attributeExample.createCriteria().andCertIdEqualTo(cert.getId());
            cspConfCertAttributeMapper.deleteByExample(attributeExample);
        });
        cspConfCertMapper.deleteByExample(certExample);
        SysDataIssueVersionExample versionExample = new SysDataIssueVersionExample();
        versionExample.createCriteria().andLccCodeEqualTo(confNo);
        sysDataIssueVersionMapper.deleteByExample(versionExample);
    }

    /**
     * 编号规则 M_00001_TYPE_yyyyMMdd_index7
     *
     * @return
     */
    @Override
    public String createConferenceNo(String confType) {
        StringBuilder confNo = new StringBuilder();
        confNo.append("M_");
        confNo.append(getConfIndex());
        confNo.append("_");
        confNo.append(confType);
        confNo.append("_");
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        String dateStr = df.format(new Date());
        confNo.append(dateStr);
        confNo.append("_");
        CspConferenceExample exp = new CspConferenceExample();
        exp.createCriteria().andConfNoLike("%" + dateStr + "%");
        long totalOfToday = conferenceMapper.countByExample(exp);
        confNo.append(totalOfToday + 1);
        return confNo.toString();
    }

    /**
     * 会议可能存在中间删除的情况，所以索引不能按照数量增加
     *
     * @return
     */
    private String getConfIndex() {
        String maxConfNo = myConferenceMapper.selectMaxConfNo();
        if (maxConfNo == null) {
            return "00001";
        }
        String maxIndex = maxConfNo.substring(2, 7);
        return String.format("%05d", Integer.parseInt(maxIndex) + 1);
    }

    @Override
    public void savePerson(CspMainPerson person, String confId) {
        String personId = Utils.generateUUID();
        // 如果证件已存在，则对该人员做更新操作
        CspMainPersonExample exp = new CspMainPersonExample();
        exp.createCriteria().andIdTypeEqualTo(person.getIdType()).andIdNumberEqualTo(person.getIdNumber());
        List<CspMainPerson> persons = cspMainPersonMapper.selectByExample(exp);
        if (persons.size() > 0) {
            CspMainPerson existPerson = persons.get(0);
            person.setId(existPerson.getId());
            personId = existPerson.getId();
            person.setUpdateTime(new Date());
            person.setUpdateBy(Securitys.getAccountId());
            cspMainPersonMapper.updateByPrimaryKeySelective(person);
            // 将工作人员添加到账户列表
            if (AppConsts.PERSON_TYPE_3.equals(person.getType())) {
                updateAccount(person);
            }
        } else {
            person.setId(personId);
            person.setCreateBy(Securitys.getAccountId());
            person.setCreateTime(new Date());
            person.setDataVersion(Utils.generateUUID());
            cspMainPersonMapper.insertSelective(person);

            if (AppConsts.PERSON_TYPE_3.equals(person.getType())) {
                createAccount(person);
            }
        }

        if (AppConsts.PERSON_TYPE_1.equals(person.getType())) {
            CspConfVisitorExample visitorExample = new CspConfVisitorExample();
            visitorExample.createCriteria().andConfIdEqualTo(confId).andVisitorIdEqualTo(personId);
            if (cspConfVisitorMapper.selectByExample(visitorExample).size() > 0) {
                return;
            }
            CspConfVisitor visitor = new CspConfVisitor();
            visitor.setId(Utils.generateUUID());
            visitor.setConfId(confId);
            visitor.setVisitorConfNo(""); // TODO
            visitor.setCreateBy(Securitys.getAccountId());
            visitor.setCreateTime(new Date());
            visitor.setVisitorId(personId);
            cspConfVisitorMapper.insertSelective(visitor);
        } else if (AppConsts.PERSON_TYPE_2.equals(person.getType())) {
            CspConfLecturerExample lecturerExample = new CspConfLecturerExample();
            lecturerExample.createCriteria().andConfIdEqualTo(confId).andConfLecturerIdEqualTo(personId);
            if (cspConfLecturerMapper.selectByExample(lecturerExample).size() > 0) {
                return;
            }
            CspConfLecturer lecturer = new CspConfLecturer();
            lecturer.setId(Utils.generateUUID());
            lecturer.setConfId(confId);
            lecturer.setCreateBy(Securitys.getAccountId());
            lecturer.setCreateTime(new Date());
            lecturer.setConfLecturerId(personId);
            cspConfLecturerMapper.insertSelective(lecturer);
        } else if (AppConsts.PERSON_TYPE_3.equals(person.getType())) {
            CspConfWorkerExample workerExample = new CspConfWorkerExample();
            workerExample.createCriteria().andConfIdEqualTo(confId).andWorkerIdEqualTo(personId);
            if (cspConfWorkerMapper.selectByExample(workerExample).size() > 0) {
                return;
            }
            CspConfWorker worker = new CspConfWorker();
            worker.setId(Utils.generateUUID());
            worker.setConfId(confId);
            worker.setCreateBy(Securitys.getAccountId());
            worker.setCreateTime(new Date());
            worker.setWorkerId(personId);
            cspConfWorkerMapper.insertSelective(worker);
        }
        // 将人员保存到签到表中
        CspConfCheckinExample checkinExample = new CspConfCheckinExample();
        checkinExample.createCriteria().andConfIdEqualTo(confId);
        cspConfCheckinMapper.selectByExample(checkinExample).stream().forEach(checkin -> {
            if (checkin.getPersonType() != null) {
                if ((checkin.getPersonType().contains("1") && AppConsts.PERSON_TYPE_1.equals(person.getType()))
                        || (checkin.getPersonType().contains("2") && AppConsts.PERSON_TYPE_2.equals(person.getType()))) {
                    insertCheckinPerson(person.getType(), person.getId(), checkin.getId());
                } else if (checkin.getPersonType().contains("3") && AppConsts.PERSON_TYPE_3.equals(person.getType())) {
                    insertCheckinWorker(person.getId(), checkin.getId());
                }
            }
        });
    }

    private void createAccount(CspMainPerson person) {
        SysAccount account = new SysAccount();
        account.setName(person.getName());
        account.setUserId(person.getId());
        account.setUserType(person.getType());
        sysService.createAccount(account, null);
    }

    private void updateAccount(CspMainPerson person) {
        SysAccountExample exp = new SysAccountExample();
        exp.createCriteria().andUserIdEqualTo(person.getId());
        List<SysAccount> accounts = sysAccountMapper.selectByExample(exp);
        if (accounts.size() == 1) {
            SysAccount account = accounts.get(0);
            // 防止修改密码
            account.setPassword(null);
            account.setName(person.getName());
            sysService.updateAccount(account, null);
        }
    }

    /**
     * 只删除会议和人员关联信息，不删除人员明细信息
     *
     * @param id
     * @param confId
     * @param type
     */
    @Override
    public void deletePerson(String id, String confId, Short type) {
        // 删除人员对应的签到
        CspConfCheckinExample checkinExample = new CspConfCheckinExample();
        checkinExample.createCriteria().andConfIdEqualTo(confId);
        cspConfCheckinMapper.selectByExample(checkinExample).forEach(checkin -> {
            String pId = null;
            if (AppConsts.PERSON_TYPE_3.equals(type)) {
                CspConfWorker worker = cspConfWorkerMapper.selectByPrimaryKey(id);
                pId = worker.getWorkerId();
                CspConfCheckinWorkerExample exp = new CspConfCheckinWorkerExample();
                exp.createCriteria().andCheckinIdEqualTo(checkin.getId()).andWorkerIdEqualTo(worker.getWorkerId());
                cspConfCheckinWorkerMapper.deleteByExample(exp);
            } else {
                String personId = null;
                if (AppConsts.PERSON_TYPE_1.equals(type)) {
                    personId = cspConfVisitorMapper.selectByPrimaryKey(id).getVisitorId();
                } else if (AppConsts.PERSON_TYPE_2.equals(type)) {
                    personId = cspConfLecturerMapper.selectByPrimaryKey(id).getConfLecturerId();
                }
                pId = personId;
                CspConfCheckinPersonExample exp = new CspConfCheckinPersonExample();
                exp.createCriteria().andCheckinIdEqualTo(checkin.getId()).andPersonIdEqualTo(pId);
                cspConfCheckinPersonMapper.deleteByExample(exp);
                // 删除人员的签到记录
                CspConfCheckinRecordExample example = new CspConfCheckinRecordExample();
                example.createCriteria().andCheckinIdEqualTo(checkin.getId()).andVisitorIdEqualTo(personId);
                cspConfCheckinRecordMapper.deleteByExample(example);
            }
            // 删除人员的签到记录
            CspConfCheckinRecordExample example = new CspConfCheckinRecordExample();
            example.createCriteria().andCheckinIdEqualTo(checkin.getId()).andVisitorIdEqualTo(pId);
            cspConfCheckinRecordMapper.deleteByExample(example);
            // 删除人员的登记记录
            CspConfRegisterExample example1 = new CspConfRegisterExample();
            example1.createCriteria().andConfIdEqualTo(confId).andPersonIdEqualTo(pId).andPersonTypeEqualTo(type + "");
            cspConfRegisterMapper.deleteByExample(example1);
        });
        // 删除人员
        if (AppConsts.PERSON_TYPE_1.equals(type)) {
            cspConfVisitorMapper.deleteByPrimaryKey(id);
        }
        if (AppConsts.PERSON_TYPE_2.equals(type)) {
            cspConfLecturerMapper.deleteByPrimaryKey(id);
        }
        if (AppConsts.PERSON_TYPE_3.equals(type)) {
            cspConfWorkerMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public void updateConfPerson(CspMainPerson person, String confId) {
        person.setUpdateBy(Securitys.getAccountId());
        person.setUpdateTime(new Date());
        cspMainPersonMapper.updateByPrimaryKeySelective(person);

        if (AppConsts.PERSON_TYPE_3.equals(person.getType())) {
            updateAccount(person);
        }
    }

    @Override
    public List<?> queryConfPersons(String confId, ConferencePersonFilter filter) {
        filter.setConfId(confId);
        return myConferenceMapper.selectConfPerson(filter);
    }

    @Override
    public void uploadConfPerson(String file, String confId) {
        try {
            List<UploadPersonVO> uploadPersons = parseFile(file);
            List<CspMainPerson> mainPersons = transformPerson(uploadPersons);
            mainPersons.forEach(person -> {
                savePerson(person, confId);
            });
        } catch (IOException | SAXException | InvalidFormatException e) {
            e.printStackTrace();
            throw Exceptions.unchecked(e);
        }
    }

    /**
     * 校验上传文件，并抛出错误信息
     *
     * @param fileId
     * @throws IOException
     * @throws SAXException
     * @throws InvalidFormatException
     */
    private List<UploadPersonVO> parseFile(String fileId) throws IOException, SAXException, InvalidFormatException {
        byte[] fileBytes = fileService.downloadFile(fileId);
        Resource xmlResource = new ClassPathResource("template/参会人员导入解析模板.xml");
        Resource inputXls = new ByteArrayResource(fileBytes);
        List<UploadPersonVO> persons = Lists.newArrayList();
        // Resource inputXls = new FileSystemResource("/Users/huangrupeng/Downloads/会议服务平台-参会人员导入模板 (1).xlsx");
        try (InputStream xmlInputStream = xmlResource.getInputStream()) {
            XLSReader mainReader = ReaderBuilder.buildFromXML(xmlInputStream);
            try (InputStream inputXLS = inputXls.getInputStream()) {
                Map<String, Object> beans = Maps.newHashMap();
                beans.put("persons", persons);
                ReaderConfig.getInstance().setSkipErrors(true);
                mainReader.read(inputXLS, beans);
                String validMsg = validatePersons(persons);
                if (StringUtils.isNotBlank(validMsg)) {
                    logger.error("校验上传的参会人员文件{}失败，校验信息{}", fileId, validMsg);
                    throw new ValidatorException(validMsg);
                }
            }
        }
        return persons;
    }

    /**
     * 使用 hibernate validator 对字段进行校验
     *
     * @param persons
     * @return
     */
    private String validatePersons(List<UploadPersonVO> persons) {
        StringBuilder errMsg = new StringBuilder();
        for (int i = 0; i < persons.size(); i++) {
            final StringBuilder subErrMsg = new StringBuilder();
            subErrMsg.append("第" + (i + 2) + "行:");
            UploadPersonVO currPerson = persons.get(i);
            // 校验错误信息
            Set<ConstraintViolation<UploadPersonVO>> validResult = validator.validate(currPerson);
            validResult.forEach(x -> {
                subErrMsg.append(x.getMessage());
            });
            if (validResult.size() > 0) {
                subErrMsg.append("|");
                errMsg.append(subErrMsg);
            }
        }
        return errMsg.toString();
    }

    /**
     * 校验通过，将人员插入到数据库
     *
     * @param persons
     */
    private void inertPerson(List<UploadPersonVO> persons) {
        List<CspMainPerson> mainPersons = transformPerson(persons);

    }

    /**
     * 将导入的 excel bean 转换成 数据库对象 bean，主要是进行一些字典的转换
     *
     * @param persons
     * @return
     */
    private List<CspMainPerson> transformPerson(List<UploadPersonVO> persons) {
        return persons.stream().map(uploadPerson -> {
            CspMainPerson mainPerson = new CspMainPerson();
            BeanUtils.copyProperties(uploadPerson, mainPerson);
            // TODO 后期将从字典进行转换
            if ("身份证".equals(uploadPerson.getIdTypeStr())) {
                mainPerson.setIdType((short) 1);
            } else if ("护照".equals(uploadPerson.getIdTypeStr())) {
                mainPerson.setIdType((short) 2);
            } else if ("其他".equals(uploadPerson.getIdTypeStr())) {
                mainPerson.setIdType((short) 3);
            }
            // 人员类型
            if ("来宾".equals(uploadPerson.getTypeStr())) {
                mainPerson.setType(AppConsts.PERSON_TYPE_1);
            } else if ("工作人员".equals(uploadPerson.getTypeStr())) {
                mainPerson.setType(AppConsts.PERSON_TYPE_3);
            } else if ("讲师".equals(uploadPerson.getTypeStr())) {
                mainPerson.setType(AppConsts.PERSON_TYPE_2);
            }
            // 性别
            if ("男".equals(uploadPerson.getGenderStr())) {
                mainPerson.setGender((short) 1);
            } else if ("女".equals(uploadPerson.getGenderStr())) {
                mainPerson.setGender((short) 2);
            } else if ("未知".equals(uploadPerson.getGenderStr())) {
                mainPerson.setGender((short) 3);
            }
            // 二维码
            if ("是".equals(uploadPerson.getSameQrcode())) {
                mainPerson.setSameQrcode(1 + "");
            } else if ("否".equals(uploadPerson.getSameQrcode())) {
                mainPerson.setSameQrcode(0 + "");
            }
            return mainPerson;
        }).collect(Collectors.toList());
    }

    @Override
    public List<CspConfCheckinVO> queryConfCheckins(String confId, ConferenceCheckinFilter filter) {
        filter.setConfId(confId);
        return myConferenceMapper.selectConfCheckinByFilter(filter);
    }

    @Override
    public void createConfCheckin(CspConfCheckin checkin, String confId, String personType) {
        checkin.setCreateBy(Securitys.getAccountId());
        checkin.setCreateTime(new Date());
        checkin.setId(Utils.generateUUID());
        checkin.setCheckinNo(createCheckinNo(checkin, confId));
        checkin.setPersonType(personType);
        cspConfCheckinMapper.insertSelective(checkin);
        // 添加会议人员
        createCheckinPerson(checkin.getId(), confId, personType);
    }

    /**
     * 格式 C_会议index_签到类型_签到日期_total
     *
     * @param checkin
     * @param confId
     * @return
     */
    private String createCheckinNo(CspConfCheckin checkin, String confId) {
        CspConference conference = conferenceMapper.selectByPrimaryKey(confId);
        String confNo = conference.getConfNo();
        StringBuilder checkinNoBuilder = new StringBuilder();
        checkinNoBuilder.append("C_");
        checkinNoBuilder.append(confNo.substring(2, 7));
        checkinNoBuilder.append("_");
        checkinNoBuilder.append(String.format("%02d", checkin.getCheckinType()));
        checkinNoBuilder.append("_");
        DateFormat format = new SimpleDateFormat("yyyyMMdd");
        String checkinDateStr = format.format(checkin.getStartTime());
        checkinNoBuilder.append(checkinDateStr);
        CspConfCheckinExample exp = new CspConfCheckinExample();
        exp.createCriteria().andCheckinNoLike("%" + checkinDateStr + "%");
        checkinNoBuilder.append("_");
        long total = cspConfCheckinMapper.countByExample(exp);
        checkinNoBuilder.append(String.format("%02d", total + 1));
        return checkinNoBuilder.toString();
    }

    private void createCheckinPerson(String checkinId, String confId, String personType) {
        Arrays.asList(personType.split(",")).stream().map(Short::parseShort).forEach(type -> {
            // 来宾 讲师
            if (AppConsts.PERSON_TYPE_1.equals(type)) {
                CspConfVisitorExample exp = new CspConfVisitorExample();
                exp.createCriteria().andConfIdEqualTo(confId);
                cspConfVisitorMapper.selectByExample(exp).forEach(visitor -> {
                    insertCheckinPerson(type, visitor.getVisitorId(), checkinId);
                });
            } else if (AppConsts.PERSON_TYPE_2.equals(type)) {
                CspConfLecturerExample exp = new CspConfLecturerExample();
                exp.createCriteria().andConfIdEqualTo(confId);
                cspConfLecturerMapper.selectByExample(exp).forEach(lecturer -> {
                    insertCheckinPerson(type, lecturer.getConfLecturerId(), checkinId);
                });
            } else if (AppConsts.PERSON_TYPE_3.equals(type)) {
                CspConfWorkerExample exp = new CspConfWorkerExample();
                exp.createCriteria().andConfIdEqualTo(confId);
                cspConfWorkerMapper.selectByExample(exp).forEach(worker -> {
                    insertCheckinWorker(worker.getWorkerId(), checkinId);
                });
            }
        });
    }

    /**
     * 添加访客/讲师签到信息
     *
     * @param type
     * @param personId
     * @param checkinId
     */
    private void insertCheckinPerson(Short type, String personId, String checkinId) {
        CspConfCheckinPerson person = new CspConfCheckinPerson();
        person.setPersonType(type);
        person.setCreateBy(Securitys.getAccountId());
        person.setCreateTime(new Date());
        person.setPersonId(personId);
        person.setCheckinId(checkinId);
        person.setId(Utils.generateUUID());
        cspConfCheckinPersonMapper.insertSelective(person);
    }

    /**
     * 添加工作人员签到信息
     *
     * @param workerId
     * @param checkinId
     */
    private void insertCheckinWorker(String workerId, String checkinId) {
        CspConfCheckinWorker person = new CspConfCheckinWorker();
        person.setCreateBy(Securitys.getAccountId());
        person.setCreateTime(new Date());
        person.setCheckinId(checkinId);
        person.setId(Utils.generateUUID());
        person.setWorkerId(workerId);
        cspConfCheckinWorkerMapper.insertSelective(person);
    }

    @Override
    public void updateConfCheckin(CspConfCheckin checkin, String confId, String personType) {
        checkin.setUpdateBy(Securitys.getAccountId());
        checkin.setUpdateTime(new Date());
        checkin.setPersonType(personType);
        cspConfCheckinMapper.updateByPrimaryKeySelective(checkin);

        // 删除关联信息
        CspConfCheckinPersonExample personExp = new CspConfCheckinPersonExample();
        personExp.createCriteria().andCheckinIdEqualTo(checkin.getId());
        cspConfCheckinPersonMapper.deleteByExample(personExp);
        CspConfCheckinWorkerExample workerExp = new CspConfCheckinWorkerExample();
        workerExp.createCriteria().andCheckinIdEqualTo(checkin.getId());
        cspConfCheckinWorkerMapper.deleteByExample(workerExp);
        // 重新关联
        createCheckinPerson(checkin.getId(), confId, personType);
    }

    private List<Short> queryCheckinPersonType(String checkinId) {
        List<Short> types = Lists.newArrayList();
        CspConfCheckinPersonExample visitorExp = new CspConfCheckinPersonExample();
        visitorExp.createCriteria().andPersonTypeEqualTo(AppConsts.PERSON_TYPE_1).andCheckinIdEqualTo(checkinId);
        long visitors = cspConfCheckinPersonMapper.countByExample(visitorExp);
        if (visitors > 0) {
            types.add(AppConsts.PERSON_TYPE_1);
        }

        CspConfCheckinPersonExample lecturerExp = new CspConfCheckinPersonExample();
        lecturerExp.createCriteria().andPersonTypeEqualTo(AppConsts.PERSON_TYPE_2).andCheckinIdEqualTo(checkinId);
        long lecturers = cspConfCheckinPersonMapper.countByExample(lecturerExp);
        if (lecturers > 0) {
            types.add(AppConsts.PERSON_TYPE_2);
        }

        CspConfCheckinWorkerExample workerExp = new CspConfCheckinWorkerExample();
        workerExp.createCriteria().andCheckinIdEqualTo(checkinId);
        long workers = cspConfCheckinWorkerMapper.countByExample(workerExp);
        if (workers > 0) {
            types.add(AppConsts.PERSON_TYPE_3);
        }
        return types;
    }

    @Override
    public void deleteConfCheckin(String id, String confId) {
        cspConfCheckinMapper.deleteByPrimaryKey(id);

        CspConfCheckinPersonExample visitorExp = new CspConfCheckinPersonExample();
        List<Short> personTypes = Lists.newArrayList();
        personTypes.add(AppConsts.PERSON_TYPE_1);
        personTypes.add(AppConsts.PERSON_TYPE_2);
        visitorExp.createCriteria().andCheckinIdEqualTo(id).andPersonTypeIn(personTypes);
        cspConfCheckinPersonMapper.deleteByExample(visitorExp);

        CspConfCheckinWorkerExample workerExp = new CspConfCheckinWorkerExample();
        workerExp.createCriteria().andCheckinIdEqualTo(id);
        cspConfCheckinWorkerMapper.deleteByExample(workerExp);

        // 删除签到记录
        CspConfCheckinRecordExample recordExp = new CspConfCheckinRecordExample();
        recordExp.createCriteria().andCheckinIdEqualTo(id).andConfIdEqualTo(confId);
        cspConfCheckinRecordMapper.deleteByExample(recordExp);
    }

    @Override
    public List<ConfEventVO> queryEvents(ConferenceEventFilter filter) {
        List<ConfEventVO> events = Lists.newArrayList();
        if (filter.getType() == null || AppConsts.EVENT_TYPE_1.equals(filter.getType())) {
            events.addAll(conferenceMapper.selectByExample(null).stream().map(conf -> {
                ConfEventVO event = new ConfEventVO();
                event.setStart(conf.getStartTime());
                event.setEnd(conf.getEndTime());
                event.setAllDay(true);
                event.setTitle(conf.getConfName());
                event.setDesc(conf.getRemark());
                event.setType(AppConsts.EVENT_TYPE_1);
                event.setId(conf.getId());
                return event;
            }).collect(Collectors.toList()));
        } else if (AppConsts.EVENT_TYPE_2.equals(filter.getType())) {
            // 课程
            CspConfCourseExample courseExample = new CspConfCourseExample();
            courseExample.createCriteria().andConfIdEqualTo(filter.getConfId());
            events.addAll(cspConfCourseMapper.selectByExample(courseExample).stream().map(course -> {
                ConfEventVO event = new ConfEventVO();
                event.setStart(course.getStartTime());
                event.setEnd(course.getEndTime());
                event.setTitle(course.getName());
                event.setDesc(course.getRemark());
                event.setType(AppConsts.EVENT_TYPE_2);
                event.setId(course.getId());
                return event;
            }).collect(Collectors.toList()));
        } else if (AppConsts.EVENT_TYPE_3.equals(filter.getType())) {
            // 签到
            CspConfCheckinExample checkinExample = new CspConfCheckinExample();
            checkinExample.createCriteria().andConfIdEqualTo(filter.getConfId());
            events.addAll(cspConfCheckinMapper.selectByExample(checkinExample).stream().map(checkin -> {
                ConfEventVO event = new ConfEventVO();
                event.setStart(checkin.getStartTime());
                event.setEnd(checkin.getEndTime());
                event.setTitle(checkin.getCheckinName());
                event.setDesc(checkin.getRemark());
                event.setType(AppConsts.EVENT_TYPE_3);
                event.setId(checkin.getId());
                return event;
            }).collect(Collectors.toList()));
        }
        return events;
    }

    @Override
    public void deletePlaceRoom(String id, String placeId) {
        confPlaceRoomMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updatePlaceRoom(CspConfPlaceRoom room, String placeId) {
        room.setUpdateBy(Securitys.getAccountId());
        room.setUpdateTime(new Date());
        confPlaceRoomMapper.updateByPrimaryKeySelective(room);
    }

    @Override
    public void createPlaceRoom(CspConfPlaceRoom room, String placeId) {
        room.setCreateBy(Securitys.getAccountId());
        room.setCreateTime(new Date());
        room.setId(Utils.generateUUID());
        confPlaceRoomMapper.insertSelective(room);
    }

    @Override
    public List<?> queryPlaceRoom(String placeId) {
        CspConfPlaceRoomExample exp = new CspConfPlaceRoomExample();
        exp.createCriteria().andPlaceIdEqualTo(placeId);
        return confPlaceRoomMapper.selectByExample(exp);
    }

    @Override
    public void deletePlace(String id) {
        confPlaceMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updatePlace(CspConfPlace place) {
        place.setUpdateBy(Securitys.getAccountId());
        place.setUpdateTime(new Date());
        confPlaceMapper.updateByPrimaryKeySelective(place);
    }

    @Override
    public void createPlace(CspConfPlace place) {
        place.setCreateBy(Securitys.getAccountId());
        place.setCreateTime(new Date());
        place.setId(Utils.generateUUID());
        confPlaceMapper.insertSelective(place);
    }

    @Override
    public List<?> queryPlace(ConferencePlaceFilter filter) {
        return myConferenceMapper.selectPlaceByFilter(filter);
    }

    @Override
    public List<?> queryAllPlacesAndRooms() {
        final List<ConfCoursePlaceVO> places = Lists.newArrayList();
        confPlaceMapper.selectByExample(null).forEach(place -> {
            ConfCoursePlaceVO vo = new ConfCoursePlaceVO();
            vo.setLabel(place.getPlaceName());
            vo.setValue(place.getId());
            CspConfPlaceRoomExample roomExample = new CspConfPlaceRoomExample();
            roomExample.createCriteria().andPlaceIdEqualTo(place.getId());
            final List<ConfCoursePlaceVO> rooms = Lists.newArrayList();
            confPlaceRoomMapper.selectByExample(roomExample).forEach(room -> {
                ConfCoursePlaceVO roomVo = new ConfCoursePlaceVO();
                roomVo.setLabel(room.getRoomName());
                roomVo.setValue(room.getId());
                rooms.add(roomVo);
            });
            vo.setChildren(rooms);
            places.add(vo);
        });
        return places;
    }

    @Override
    public void createConfCourse(CspConfCourse course, String confId, String roomId, String file) {
        validatePlace(course, roomId);
        course.setId(Utils.generateUUID());
        course.setCreateBy(Securitys.getAccountId());
        course.setCreateTime(new Date());
        course.setRoomId(roomId);
        cspConfCourseMapper.insertSelective(course);

        // 创建签到
        if (AppConsts.TRUE.equals(course.getIsCheckin()) && StringUtils.isNotBlank(course.getCheckinType())) {
            saveCourseCheckin(course, confId);
        }

        // 保存课程文件
        if (StringUtils.isNotBlank(file)) {
            saveCourseFile(file, course.getId());
        }
    }

    @Override
    public void updateConfCourse(CspConfCourse course, String confId, String roomId, String file) {
        validatePlace(course, roomId);
        course.setUpdateBy(Securitys.getAccountId());
        course.setUpdateTime(new Date());
        course.setRoomId(roomId);

        // 删除之前的签到
        CspConfCheckinExample exp = new CspConfCheckinExample();
        exp.createCriteria().andRemarkEqualTo(course.getId());
        cspConfCheckinMapper.deleteByExample(exp);
        // 创建签到
        if (AppConsts.TRUE.equals(course.getIsCheckin()) && StringUtils.isNotBlank(course.getCheckinType())) {
            saveCourseCheckin(course, confId);
        } else {
            course.setCheckinType("");
        }
        // 保存课程文件
        if (StringUtils.isNotBlank(file)) {
            saveCourseFile(file, course.getId());
        }
        cspConfCourseMapper.updateByPrimaryKeySelective(course);
    }

    private void validatePlace(CspConfCourse course, String roomId) {
        if (roomId == null) {
            throw new ValidatorException("会场不能为空");
        }
        // 与其它
        CspConfCourseExample exp = new CspConfCourseExample();
        CspConfCourseExample.Criteria criterion = exp.createCriteria();
        if (course.getId() != null) {
            criterion.andIdNotEqualTo(course.getId());
        }
        criterion.andRoomIdEqualTo(roomId);
        // 开始时间在已有时间范围内
        criterion.andStartTimeLessThanOrEqualTo(course.getStartTime());
        criterion.andEndTimeGreaterThanOrEqualTo(course.getStartTime());
        CspConfCourseExample.Criteria criterion1 = exp.createCriteria();
        if (course.getId() != null) {
            criterion1.andIdNotEqualTo(course.getId());
        }
        criterion1.andRoomIdEqualTo(roomId);
        criterion1.andStartTimeLessThanOrEqualTo(course.getEndTime());
        criterion1.andEndTimeGreaterThanOrEqualTo(course.getEndTime());
        exp.or(criterion1);
        long total = cspConfCourseMapper.countByExample(exp);
        if (total > 0) {
            throw new ValidatorException("会场在该时间段已被别的课程占用");
        }
    }

    private void saveCourseCheckin(CspConfCourse course, String confId) {
        CspConfCheckin checkin = new CspConfCheckin();
        checkin.setCheckinType(AppConsts.CONF_CHECKIN_TYPE_4);
        // 默认开始时间往前推半个小时
        Date startTime = DateConvertUtils.asUtilDate(DateConvertUtils.asLocalDateTime(course.getStartTime()).minusMinutes(30));
        Date endTime = course.getStartTime();
        checkin.setStartTime(startTime);
        checkin.setEndTime(endTime);
        checkin.setCheckinName(course.getName() + "签到");
        // 将备注信息设置为课程 ID，间接关联课程
        checkin.setRemark(course.getId());
        checkin.setConfId(confId);
        createConfCheckin(checkin, confId, course.getCheckinType());
    }

    private void saveCourseFile(String file, String courseId) {
        String[] filesInfo = file.split(";");
        for (String fileInfo : filesInfo) {
            String[] nameAndId = fileInfo.split(",");
            CspConfFile courseFile = new CspConfFile();
            courseFile.setCreateBy(Securitys.getAccountId());
            courseFile.setCreateTime(new Date());
            courseFile.setMainId(courseId);
            courseFile.setSrcFileName(nameAndId[0]);
            courseFile.setFileId(nameAndId[1]);
            courseFile.setFileType(AppConsts.CONF_FILE_TYPE_2);
            courseFile.setId(Utils.generateUUID());
            cspConfFileMapper.insertSelective(courseFile);
        }

    }

    @Override
    public void deleteConfCourse(String courseId, String confId) {
        // 删除之前的签到
        CspConfCheckinExample exp = new CspConfCheckinExample();
        exp.createCriteria().andRemarkEqualTo(courseId);
        List<CspConfCheckin> checkins = cspConfCheckinMapper.selectByExample(exp);
        if (checkins.size() == 1) {
            CspConfCheckin courseCheckin = checkins.get(0);
            deleteConfCheckin(courseCheckin.getId(), confId);
        }

        cspConfCourseMapper.deleteByPrimaryKey(courseId);

        // 删除上传的附件
        CspConfFileExample fileExp = new CspConfFileExample();
        fileExp.createCriteria().andMainIdEqualTo(courseId);
        cspConfFileMapper.deleteByExample(fileExp);
    }

    @Override
    public CspConfCourse queryConfCourse(String confId, String id) {
        return cspConfCourseMapper.selectByPrimaryKey(id);
    }

    /**
     * 证件增加
     *
     * @param confId
     * @return
     */

    @Override
    public void createConfIdentity(IdentityVO identityVO, String confId) {
        CspConfCert cspConfCert = new CspConfCert();
        BeanUtils.copyProperties(identityVO, cspConfCert);
        String certId = Utils.generateUUID();
        cspConfCert.setId(certId);
        cspConfCert.setCreateTime(new Date());
        cspConfCert.setCreateBy(Securitys.getAccountId());
        cspConfCertMapper.insertSelective(cspConfCert);

        identityVO.setId(certId);
        // 添加布局文件占位符
        handleParameter(identityVO, (short) 0);
        // 添加底板文件占位
        handleParameter(identityVO, (short) 1);
    }

    /**
     * 证件删除
     *
     * @param confId
     */
    @Override
    public void deleteIdentity(String confId, String id) {
        cspConfCertMapper.deleteByPrimaryKey(id);
        CspConfCertAttributeExample attributeExample = new CspConfCertAttributeExample();
        attributeExample.createCriteria().andCertIdEqualTo(id);
        cspConfCertAttributeMapper.deleteByExample(attributeExample);
    }

    /**
     * 证件修改
     *
     * @param confId
     */
    @Override
    public void updateConfIdentity(IdentityVO identityVO, String confId) {
        if (identityVO.getId() != null) {
            CspConfCert cert = cspConfCertMapper.selectByPrimaryKey(identityVO.getId());
            BeanUtils.copyProperties(identityVO, cert);
            cert.setUpdateBy(Securitys.getAccountId());
            cert.setUpdateTime(new Date());
            cspConfCertMapper.updateByPrimaryKeySelective(cert);

            // 删除参数
            CspConfCertAttributeExample attributeExample = new CspConfCertAttributeExample();
            attributeExample.createCriteria().andCertIdEqualTo(identityVO.getId());
            cspConfCertAttributeMapper.deleteByExample(attributeExample);
            // 添加布局文件占位符
            handleParameter(identityVO, (short) 0);
            // 添加底板文件占位
            handleParameter(identityVO, (short) 1);
        }
    }

    /**
     * 证件查询
     *
     * @param confId
     * @return
     */
    @Override
    public List<?> queryConfIdentity(String confId) {
        CspConfCertExample example = new CspConfCertExample();
        example.createCriteria().andConfIdEqualTo(confId);
        return cspConfCertMapper.selectByExample(example).stream().map(cspConfCert -> {
            IdentityVO identityDTO = new IdentityVO();
            String certId = cspConfCert.getId();
            identityDTO.setId(certId);
            identityDTO.setConfId(cspConfCert.getConfId());
            identityDTO.setLayoutfileId(cspConfCert.getLayoutfileId());
            identityDTO.setBottomfileId(cspConfCert.getBottomfileId());
            identityDTO.setIdentityType(cspConfCert.getIdentityType());
            // 拼接布局文件占位符
            setIdentityParameter(identityDTO, certId, (short) 0);
            // 拼接底板文件占位符
            setIdentityParameter(identityDTO, certId, (short) 1);
            return identityDTO;
        }).collect(Collectors.toList());
    }

    /**
     * 添加占位符参数公共方法
     *
     * @param fileType
     */

    private void handleParameter(IdentityVO identityVO, short fileType) {
        String attrName = null;
        String querySql = null;
        String queryParameter = null;
        switch (fileType) {
            case 0:
                attrName = identityVO.getLayoutAttrName();
                querySql = identityVO.getLayoutQuerySql();
                queryParameter = identityVO.getLayoutQueryParameter();
                break;
            case 1:
                attrName = identityVO.getBottomAttrName();
                querySql = identityVO.getBottomQuerySql();
                queryParameter = identityVO.getBottomQueryParameter();
                break;
            default:
        }
        if (StringUtils.isBlank(attrName) || StringUtils.isBlank(querySql)) {
            return;
        }
        CspConfCertAttribute cspConfCertAttribute = new CspConfCertAttribute();
        cspConfCertAttribute.setCertId(identityVO.getId());
        cspConfCertAttribute.setCreateBy(Securitys.getAccountId());
        cspConfCertAttribute.setCreateTime(new Date());
        String[] attrNames = attrName.split("###");
        String[] querySqls = querySql.split("###");
        if (StringUtils.isBlank(queryParameter)) {
            if (attrNames.length != 0 && querySqls.length != 0) {
                if ((attrNames.length == querySqls.length)) {
                    for (int i = 0; i < attrNames.length; i++) {
                        cspConfCertAttribute.setId(Utils.generateUUID());
                        cspConfCertAttribute.setAttrName(attrNames[i]);
                        cspConfCertAttribute.setQuerySql(querySqls[i]);
                        cspConfCertAttribute.setFileType(fileType);
                        cspConfCertAttributeMapper.insertSelective(cspConfCertAttribute);
                    }
                }
            }
        } else {
            String[] queryParameters = queryParameter.split("###");
            if (attrNames.length != 0 && querySqls.length != 0 && queryParameters.length != 0) {
                if ((attrNames.length == querySqls.length) && (attrNames.length == queryParameters.length)) {
                    for (int i = 0; i < attrNames.length; i++) {
                        cspConfCertAttribute.setId(Utils.generateUUID());
                        cspConfCertAttribute.setAttrName(attrNames[i]);
                        cspConfCertAttribute.setQuerySql(querySqls[i]);
                        cspConfCertAttribute.setQueryParameter(queryParameters[i]);
                        cspConfCertAttribute.setFileType(fileType);
                        cspConfCertAttributeMapper.insertSelective(cspConfCertAttribute);
                    }
                }
            }
        }
    }

    /**
     * 查询占位符参数公共方法
     *
     * @param certId
     * @param fileType
     * @return
     */
    private void setIdentityParameter(IdentityVO identityVO, String certId, short fileType) {
        CspConfCertAttributeExample attributeExample = new CspConfCertAttributeExample();
        attributeExample.createCriteria().andCertIdEqualTo(certId).andFileTypeEqualTo(fileType);
        List<CspConfCertAttribute> cspConfCertAttributes = cspConfCertAttributeMapper.selectByExample(attributeExample);
        if (cspConfCertAttributes.size() > 0) {
            StringBuilder attrNameBuf = new StringBuilder();
            StringBuilder querySqlBuf = new StringBuilder();
            StringBuilder queryParameterBuf = new StringBuilder();
            for (CspConfCertAttribute cspConfCertAttribute : cspConfCertAttributes) {
                String attrName = cspConfCertAttribute.getAttrName();
                String querySql = cspConfCertAttribute.getQuerySql();
                String queryParameter = cspConfCertAttribute.getQueryParameter();
                attrNameBuf.append(attrName).append("###");
                querySqlBuf.append(querySql).append("###");
                if (queryParameter != null) {
                    queryParameterBuf.append(queryParameter).append("###");
                }
            }
            String attrName = StringUtils.substringBeforeLast(attrNameBuf.toString(), "###");
            String querySql = StringUtils.substringBeforeLast(querySqlBuf.toString(), "###");
            String queryParameter = StringUtils.substringBeforeLast(queryParameterBuf.toString(), "###");
            if (fileType == 0) {
                identityVO.setLayoutAttrName(attrName);
                identityVO.setLayoutQuerySql(querySql);
                identityVO.setLayoutQueryParameter(queryParameter);
            } else if (fileType == 1) {
                identityVO.setBottomAttrName(attrName);
                identityVO.setBottomQuerySql(querySql);
                identityVO.setBottomQueryParameter(queryParameter);
            }
        }
    }

    @Override
    public List<?> queryConfRooms(String confId) {
        CspConference conference = conferenceMapper.selectByPrimaryKey(confId);
        String placeId = conference.getPlace();
        CspConfPlaceRoomExample exp = new CspConfPlaceRoomExample();
        exp.createCriteria().andPlaceIdEqualTo(placeId);
        return confPlaceRoomMapper.selectByExample(exp);
    }

    @Override
    public List<?> personAutoComplete(ConferencePersonFilter filter) {
        if (filter.getStr() != null && filter.getStr().contains("'")) {
            filter.setStr(filter.getStr().replaceAll("'", "''"));
        }
        return myConferenceMapper.selectMainPersonByFilter(filter);
    }

    @Override
    public File exportQRCode(String confId, Short type) {
        try {
            CspConference conference = conferenceMapper.selectByPrimaryKey(confId);
            String confNo = conference.getConfNo();
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
            String zipName = confNo + "_" + type + "_" + timestamp + ".zip";
            File zipFile = new File(zipName);
            ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));

            int[] idx = {0};
            if (AppConsts.PERSON_TYPE_1.equals(type)) {
                CspConfVisitorExample visitorExample = new CspConfVisitorExample();
                visitorExample.createCriteria().andConfIdEqualTo(confId);
                cspConfVisitorMapper.selectByExample(visitorExample).forEach(visitor -> {
                    sendBarcodeToZipFile(visitor.getVisitorId(), confNo, zipOut, idx);
                });
            } else if (AppConsts.PERSON_TYPE_2.equals(type)) {
                CspConfLecturerExample lecturerExample = new CspConfLecturerExample();
                lecturerExample.createCriteria().andConfIdEqualTo(confId);
                cspConfLecturerMapper.selectByExample(lecturerExample).forEach(lecturer -> {
                    sendBarcodeToZipFile(lecturer.getConfLecturerId(), confNo, zipOut, idx);
                });
            } else if (AppConsts.PERSON_TYPE_3.equals(type)) {
                CspConfWorkerExample workerExample = new CspConfWorkerExample();
                workerExample.createCriteria().andConfIdEqualTo(confId);
                cspConfWorkerMapper.selectByExample(workerExample).forEach(visitor -> {
                    sendBarcodeToZipFile(visitor.getWorkerId(), confNo, zipOut, idx);
                });
            }
            zipOut.close();
            return zipFile;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void sendBarcodeToZipFile(String personId, String confNo, ZipOutputStream zipOut, int[] idx) {
        CspMainPerson person = cspMainPersonMapper.selectByPrimaryKey(personId);
        String text = PersonQRCodeUtils.createPersonQRCode(confNo, person);
        idx[0]++;
        String province = StringUtils.isNotBlank(person.getProvince()) ? (person.getProvince() + "_") : "";
        String qrCodeFileName = idx[0] + "_"
                + province
                + person.getName() + "_"
                + person.getTel() + ".png";
        File qrCodeFile = new File(qrCodeFileName);
        try {
            QRCodeUtils.writeToFile(QRCodeUtils.create(450, 450, text), "png", qrCodeFile);
            zipOut.putNextEntry(new ZipEntry(qrCodeFileName));
            int temp = 0;
            InputStream input = new FileInputStream(qrCodeFile); // 定义文件的输入流
            while ((temp = input.read()) != -1) { // 读取内容
                zipOut.write(temp); // 压缩输出
            }
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
