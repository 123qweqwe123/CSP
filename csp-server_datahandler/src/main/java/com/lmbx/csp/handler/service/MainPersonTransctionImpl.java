package com.lmbx.csp.handler.service;

import com.lmbx.csp.core.utils.Identities;
import com.lmbx.csp.data.conf.domain.*;
import com.lmbx.csp.data.conf.mapper.*;
import com.lmbx.csp.data.main.domain.CspMainPerson;
import com.lmbx.csp.data.main.mapper.CspMainPersonMapper;
import com.lmbx.csp.data.sys.domain.SysDataParseInfoToBus;
import com.lmbx.csp.data.sys.mapper.SysDataParseInfoToBusMapper;
import com.lmbx.csp.handler.data.vo.MainPerson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author yrj
 * 2017-11
 */
@Service
public class MainPersonTransctionImpl {
    Logger log = LoggerFactory.getLogger(MainPersonTransctionImpl.class);
    @Autowired
    private CspMainPersonMapper cspMainPersonMapper;
    @Autowired
    private SysDataParseInfoToBusMapper sysDataParseInfoToBusMapper;
    @Autowired
    private CspConfRegisterMapper cspConfRegisterMapper;
    @Autowired
    private CspConfVisitorMapper cspConfVisitorMapper;
    @Autowired
    private CspConfLecturerMapper cspConfLecturerMapper;
    @Autowired
    private CspConfWorkerMapper cspConfWorkerMapper;
    @Autowired
    private CspConfCheckinMapper cspConfCheckinMapper;
    @Autowired
    private CspConfCheckinPersonMapper cspConfCheckinPersonMapper;

    /**
     *人员数据处理
     * @param mainPersons
     * @param projectId
     * @param infoId
     * @return 成功插入的条数 ，未包括更新的数量
     * @throws Exception
     */
    @Transactional(rollbackFor = { RuntimeException.class, Exception.class, Error.class })
    public HandlerMainPersonResult parseMainPerson(List<MainPerson> mainPersons, String projectId, String infoId) throws Exception {
        if(mainPersons.size() == 0){
            return null;
        }

        //会议ID
        String confId = mainPersons.get(0).getConfId();

        List result = dataFormat(mainPersons);
        //待更新的数据
        List<CspMainPerson> upRecords = (List<CspMainPerson>) result.get(0);
        //待插入的数据
        List<CspMainPerson> records = (List<CspMainPerson>) result.get(1);

        HandlerMainPersonResult returnValue = new HandlerMainPersonResult();
        int insertCount = 0;
        int deleteCount = 0;
        int updateCount = 0;

        try {
            //更新数据(根据id更新数据)，修改了IdNumber或者IdType才会执行该处代码，其它字段也更新
            for (CspMainPerson person : upRecords){
                //原数据
                CspMainPerson record = cspMainPersonMapper.selectByPrimaryKey(person.getId());
                //需要更新的数据在CSP_MAIN_PERSON表中不存在，将记录放入待插入数据中,结束本次循环
                if (record == null) {
                    records.add(person);
                    continue;
                }

                Date updateTimeNew = person.getUpdateTime();
                Date updateTimeOld = record.getUpdateTime();
                if (updateTimeOld == null) {
                    updateTimeOld = record.getCreateTime();
                }
                if (updateTimeOld.after(updateTimeNew)) {
                    //新记录更新时间较小，则不更新
                    continue;
                }
                updateCount++;
                String info = "ID:" + record.getId() + ",Name:" + record.getName();
                if (person.getIdNumber() != null){
                    info += ",change IdNumber:" + record.getIdNumber() + " => " + person.getIdNumber();
                }
                if (person.getIdType() != null){
                    info += ",change IdType:" + record.getIdType() + " => " + person.getIdType();
                }

                cspMainPersonMapper.updateByPrimaryKey(person);
                log.info("MainPerson---->update====>" + info);
                returnValue.setExist(true);
            }
            //插入数据，插入新数据或修改字段值，执行该处代码
            for (CspMainPerson record : records) {
                String busId = record.getId();
                CspMainPerson personOld = cspMainPersonMapper.selectByPrimaryKey(busId);
                if (personOld != null) {
                    returnValue.setExist(true);
                    //原数据存在，比较更新时间
                    Date updateTimeNew = record.getUpdateTime();
                    Date updateTimeOld = personOld.getUpdateTime();
                    if (updateTimeOld == null) {
                        updateTimeOld = record.getCreateTime();
                    }
                    if (!updateTimeNew.before(updateTimeOld)) {
                        //新数据更新时间较大(或相等)，则删除原数据，插入新数据（相当于更新操作）；否则，不作处理
                        deleteCount++;
                        String info = "ID:" + personOld.getId() + ",Name:" + personOld.getName() + ",IdNumber:" + personOld.getIdNumber();
                        //删除原数据
                        cspMainPersonMapper.deleteByPrimaryKey(busId);
                        log.info("MainPerson---->delete====>" + info);
                    } else {
                        continue;
                    }
                } else {
                    //原数据不存在，相当于执行插入操作
                    int type = record.getType();
                    //新添加人员，根据type插入工作人员、来宾或者讲师表,插入CSP_CONF_CHECKIN_PERSON表
                    insertPersonByType(confId, busId, type);
                }
//                //String idNumber = record.getIdNumber();
//                CspMainPersonExample mainPersonExample = new CspMainPersonExample();
//                //根据idNumber查询数据，可能会造成[违反唯一约束条件],暂修改为根据主键查询
//                //mainPersonExample.createCriteria().andIdNumberEqualTo(idNumber);
//                mainPersonExample.createCriteria().andIdEqualTo(busId);
//                int count = (int) cspMainPersonMapper.countByExample(mainPersonExample);
//                deleteCount += count;
//                //数据存在，使用新的数据覆盖原数据（id也更新）
//                if (count > 0){ //原数据存在，相当于执行更新操作
//                    List<CspMainPerson> delRecords = cspMainPersonMapper.selectByExample(mainPersonExample);
//                    for (CspMainPerson person : delRecords) {
//                        String oldId = person.getId();
//                        //根据id查询时，id永远一致
//                        //新数据与原数据id不一致时，更新相关数据
//                        if (!busId.trim().equals(oldId.trim())){
//                            //使用原id删除csp_conf_register中的数据，更新工作人员、来宾或者讲师表中的数据,更新CSP_CONF_CHECKIN_PERSON
//                            short personType = record.getType();
//                            dataHandelForPersonId(oldId, busId, personType);
//                        }
//
//                        String info = "ID:" + person.getId() + ",Name:" + person.getName() + ",IdNumber:" + person.getIdNumber();
//                        //删除原数据
//                        cspMainPersonMapper.deleteByPrimaryKey(oldId);
//                        log.info("MainPerson---->delete====>" + info);
//                    }
//
//                    record.setUpdateBy("sys");
//                    record.setUpdateTime(new Date());
//
//                    returnValue.setExist(true);
//                } else { //原数据不存在，相当于执行插入操作
//                    int type = record.getType();
//                    //新添加人员，根据type插入工作人员、来宾或者讲师表,插入CSP_CONF_CHECKIN_PERSON表
//                    insertPersonByType(confId, busId, type);
//                }

                insertCount++;
                if (!infoId.isEmpty()) {
                    // 插入数据到关联表
                    SysDataParseInfoToBus infoToBus = new SysDataParseInfoToBus();
                    infoToBus.setInfoId(infoId);
                    infoToBus.setBusId(busId);
                    sysDataParseInfoToBusMapper.insertSelective(infoToBus);
                }

                cspMainPersonMapper.insert(record);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        log.info("MainPerson--==> allCount {} insert {} delete {} update {}", mainPersons.size(), insertCount, deleteCount, updateCount);

        returnValue.setInsertCount(insertCount);
        return returnValue;
    }

    /**
     * 使用原id删除csp_conf_register中的数据，更新工作人员、来宾或者讲师表中的数据，更新CSP_CONF_CHECKIN_PERSON
     * @param oldId
     * @param newId
     * @param personType
     */
    public void dataHandelForPersonId(String oldId, String newId, short personType){
        //删除csp_conf_register中的数据
        CspConfRegisterExample registerExample = new CspConfRegisterExample();
        registerExample.createCriteria().andPersonIdEqualTo(oldId);
        cspConfRegisterMapper.deleteByExample(registerExample);
        //更新工作人员表
        CspConfWorkerExample workerExample = new CspConfWorkerExample();
        workerExample.createCriteria().andWorkerIdEqualTo(oldId);
        List<CspConfWorker> workers = cspConfWorkerMapper.selectByExample(workerExample);
        for (CspConfWorker worker : workers){
            worker.setWorkerId(newId);
            worker.setUpdateBy("sys");
            worker.setUpdateTime(new Date());
            cspConfWorkerMapper.updateByPrimaryKey(worker);
        }
        //更新讲师表
        CspConfLecturerExample lecturerExample = new CspConfLecturerExample();
        lecturerExample.createCriteria().andConfLecturerIdEqualTo(oldId);
        List<CspConfLecturer> lecturers = cspConfLecturerMapper.selectByExample(lecturerExample);
        for (CspConfLecturer lecturer : lecturers){
            lecturer.setConfLecturerId(newId);
            lecturer.setUpdateBy("sys");
            lecturer.setUpdateTime(new Date());
            cspConfLecturerMapper.updateByPrimaryKey(lecturer);
        }
        //更新来宾表
        CspConfVisitorExample visitorExample = new CspConfVisitorExample();
        visitorExample.createCriteria().andVisitorIdEqualTo(oldId);
        List<CspConfVisitor> visitors = cspConfVisitorMapper.selectByExample(visitorExample);
        for (CspConfVisitor visitor : visitors){
            visitor.setVisitorId(newId);
            visitor.setUpdateBy("sys");
            visitor.setUpdateTime(new Date());
            cspConfVisitorMapper.updateByPrimaryKey(visitor);
        }
        //更新CSP_CONF_CHECKIN_PERSON
        CspConfCheckinPersonExample personExample = new CspConfCheckinPersonExample();
        personExample.createCriteria().andPersonIdEqualTo(oldId);
        List<CspConfCheckinPerson> persons = cspConfCheckinPersonMapper.selectByExample(personExample);
        for (CspConfCheckinPerson person : persons){
            person.setPersonId(newId);
            person.setPersonType(personType);
            person.setUpdateBy("sys");
            person.setUpdateTime(new Date());
            cspConfCheckinPersonMapper.updateByPrimaryKey(person);
        }
    }

    /**
     * 新添加人员，根据type插入工作人员、来宾或者讲师表，插入CSP_CONF_CHECKIN_PERSON表
     * @param confId
     * @param personId
     * @param type
     */
    public void insertPersonByType(String confId, String personId, int type){
        String id = Identities.uuid();
        if (type == 1){     //来宾
            CspConfVisitor visitor = new CspConfVisitor();
            visitor.setId(id);
            visitor.setConfId(confId);
            visitor.setVisitorId(personId);
            visitor.setCreateBy("sys");
            visitor.setCreateTime(new Date());
            cspConfVisitorMapper.insertSelective(visitor);
        } else if (type == 2){//讲师
            CspConfLecturer lecturer = new CspConfLecturer();
            lecturer.setId(id);
            lecturer.setConfId(confId);
            lecturer.setConfLecturerId(personId);
            lecturer.setCreateBy("sys");
            lecturer.setCreateTime(new Date());
            cspConfLecturerMapper.insertSelective(lecturer);
        } else if (type == 3){//工作人员
            CspConfWorker worker = new CspConfWorker();
            worker.setId(id);
            worker.setConfId(confId);
            worker.setWorkerId(personId);
            worker.setCreateBy("sys");
            worker.setCreateTime(new Date());
            cspConfWorkerMapper.insertSelective(worker);
        }

        //插入CSP_CONF_CHECKIN_PERSON表
        CspConfCheckinExample checkinExample = new CspConfCheckinExample();
        checkinExample.createCriteria().andConfIdEqualTo(confId);
        List<CspConfCheckin> checkins = cspConfCheckinMapper.selectByExample(checkinExample);
        for (CspConfCheckin checkin : checkins){
            CspConfCheckinPerson person = new CspConfCheckinPerson();
            person.setId(Identities.uuid());
            person.setPersonId(personId);
            person.setPersonType((short) type);
            person.setCheckinId(checkin.getId());
            person.setCreateBy("sys");
            person.setCreateTime(new Date());
            cspConfCheckinPersonMapper.insertSelective(person);
        }
    }

    /**
     * 将数据转化为CspMainPerson对象
     * @param mainPersons
     * @return
     */
    public List dataFormat(List<MainPerson> mainPersons){
        List result = new ArrayList<>();
        //存放需要插入的数据
        List<CspMainPerson> records = new ArrayList<>();
        //存放需要更新的数据（只更新idNumber和idType）
        List<CspMainPerson> upRecords = new ArrayList<>();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat dateSf = new SimpleDateFormat("yyyy-MM-dd");
        for (MainPerson person : mainPersons){
            //是否需要更新的数据
            boolean isUpdate = false;
            CspMainPerson record = new CspMainPerson();
            //ID
            String id = person.getId();
            if (id != null && id.trim().length() > 0 && !"null".equals(id.trim())){
                record.setId(id);
            } else {
                throw new RuntimeException("存在id为空的数据");
            }
            //证件号
            String idNumber = person.getIdNumber();
            if (idNumber == null || idNumber.trim().length() == 0 || "null".equals(idNumber.trim())){
                throw new RuntimeException("存在idNumber为空的数据");
            }

            //待更新证件号
            String pcIdNumber = person.getPcIdNumber();
            if (pcIdNumber != null && pcIdNumber.trim().length() > 0 && !"null".equals(pcIdNumber.trim())){
                record.setIdNumber(pcIdNumber);
                isUpdate = true;
            } else {
                record.setIdNumber(idNumber);
            }
            //待更新证件类型
            String pcIdType = person.getPcIdType();
            if (pcIdType != null && pcIdType.trim().length() > 0 && !"null".equals(pcIdType.trim())){
                record.setIdType(Short.valueOf(pcIdType));
                isUpdate = true;
            } else {
                String idType = person.getIdType();
                if (idType != null && idType.trim().length() > 0 && !"null".equals(idType.trim())){
                    record.setIdType(Short.valueOf(idType.trim()));
                }
            }

            String type = person.getType();
            if (type != null && type.trim().length() > 0 && !"null".equals(type.trim())){
                record.setType(Short.valueOf(type.trim()));
            } else {
                throw new RuntimeException("存在type为空的数据");
            }

            record.setName(person.getName());

            String gender = person.getGender();
            if (gender != null && gender.trim().length() > 0 && !"null".equals(gender.trim())){
                record.setGender(Short.valueOf(gender.trim()));
            }

            record.setTel(person.getTel());
            record.setEmail(person.getEmail());
            record.setWorkplace(person.getWorkplace());
            record.setDepartment(person.getDepartment());
            record.setMajor(person.getMajor());
            record.setDegree(person.getDegree());
            record.setDuty(person.getDuty());
            record.setProvince(person.getProvince());
            record.setCity(person.getCity());
            record.setCounty(person.getCounty());
            record.setAddress(person.getAddress());
            record.setRemark(person.getRemark());
            record.setFiles(person.getFiles());
            record.setDataVersion(person.getDataVersion());
            record.setCreateBy(person.getCreateBy());
            record.setUpdateBy(person.getUpdateBy());
            record.setSrcId(person.getSrcId());
            record.setTerminalId(person.getTerminalId());

            try {
                String birthday = person.getBirthday();
                if (birthday != null && birthday.trim().length() > 0 && !"null".equals(birthday.trim())){
                    record.setBirthday(dateSf.parse(birthday));
                }
                String createTime = person.getCreateTime();
                if (createTime != null && createTime.trim().length() > 0 && !"null".equals(createTime.trim())){
                    record.setCreateTime(sf.parse(createTime));
                }

                String updateTime = person.getUpdateTime();
                if (updateTime != null && updateTime.trim().length() > 0 && !"null".equals(updateTime.trim())){
                    record.setUpdateTime(sf.parse(updateTime));
                } else {
                    if (record.getCreateTime() != null) {
                        record.setUpdateTime(sf.parse(createTime));
                    } else {
                        throw new RuntimeException("存在updateTime为空的数据");
                    }

                }
            } catch (ParseException e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }

            if (isUpdate){
                upRecords.add(record);
            } else {
                records.add(record);
            }
        }

        result.add(upRecords);
        result.add(records);
        return result;
    }

    public static class HandlerMainPersonResult{
        //CSP_MAIN_PERSON 表中是否存在数据
        private boolean isExist = false;
        private int insertCount = 0;
        public boolean isExist() {
            return isExist;
        }
        public void setExist(boolean isExist) {
            this.isExist = isExist;
        }
        public int getInsertCount() {
            return insertCount;
        }
        public void setInsertCount(int insertCount) {
            this.insertCount = insertCount;
        }
    }
}
