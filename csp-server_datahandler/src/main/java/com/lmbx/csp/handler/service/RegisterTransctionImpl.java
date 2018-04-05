package com.lmbx.csp.handler.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lmbx.csp.data.conf.domain.*;
import com.lmbx.csp.data.conf.mapper.CspConfLecturerMapper;
import com.lmbx.csp.data.conf.mapper.CspConfVisitorMapper;
import com.lmbx.csp.data.conf.mapper.CspConfWorkerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lmbx.csp.core.utils.Identities;
import com.lmbx.csp.data.conf.mapper.CspConfRegisterMapper;
import com.lmbx.csp.data.sys.domain.SysDataParseInfoToBus;
import com.lmbx.csp.data.sys.mapper.SysDataParseInfoToBusMapper;
import com.lmbx.csp.handler.data.mapper.RegisterBMapper;
import com.lmbx.csp.handler.data.vo.Register;

/**
 * @author yrj
 * 2017-11
 */
@Service
public class RegisterTransctionImpl {
    Logger log = LoggerFactory.getLogger(RegisterTransctionImpl.class);
    @Autowired
    private CspConfRegisterMapper cspConfRegisterMapper;
    @Autowired
    private SysDataParseInfoToBusMapper sysDataParseInfoToBusMapper;
    @Autowired
    private RegisterBMapper registerBMapper;
    @Autowired
    private CspConfVisitorMapper cspConfVisitorMapper;
    @Autowired
    private CspConfLecturerMapper cspConfLecturerMapper;
    @Autowired
    private CspConfWorkerMapper cspConfWorkerMapper;

    /**
     *登记数据处理
     * @param registers
     * @param projectId
     * @param infoId
     * @return 成功插入的条数 ，未包括更新的数量
     * @throws Exception
     */
    @Transactional(rollbackFor = { RuntimeException.class, Exception.class, Error.class })
    public HandlerRegisterResult parseRegister(List<Register> registers, String projectId, String infoId) throws Exception {
        if(registers.size() == 0){
            return null;
        }

        List result = dataFormat(registers);
        //待删除的数据
        //List<CspConfRegister> delRecords = (List<CspConfRegister>) result.get(0);
        //待插入的数据
        List<CspConfRegister> records = (List<CspConfRegister>) result.get(0);
        //待删除数据的Id
        //List<String> ids = delRecords.stream().map(mapper -> mapper.getId()).collect(Collectors.toList());

        HandlerRegisterResult returnValue = new HandlerRegisterResult();
        int insertCount = 0;
        int deleteCount = 0;

//        if (ids.size() > 0){
//            CspConfRegisterExample example = new CspConfRegisterExample();
//            example.createCriteria().andIdIn(ids);
//            deleteCount = (int) cspConfRegisterMapper.countByExample(example);
//            if(deleteCount > 0){
//                List<CspConfRegister> backups = cspConfRegisterMapper.selectByExample(example);
//                //备份需要删除的数据
//                registerBMapper.insertBatch(backups);
//                //cspConfRegisterMapper.deleteByExample(example);
//                for (CspConfRegister register : backups){
//                    cspConfRegisterMapper.deleteByPrimaryKey(register.getId());
//                }
//                returnValue.setExist(true);
//            }
//        }

        try {
            //存放登记表需要删除的数据
            List<CspConfRegister> deletes = new ArrayList<>();
            for (CspConfRegister record : records) {
                boolean isInsert = true;
                String confId = record.getConfId();
                String personId = record.getPersonId();
                String personType = record.getPersonType();
                CspConfRegisterExample example = new CspConfRegisterExample();
                example.createCriteria().andConfIdEqualTo(confId).andPersonIdEqualTo(personId)
                        .andPersonTypeEqualTo(personType);
                List<CspConfRegister> registerList = cspConfRegisterMapper.selectByExample(example);
                if (registerList.size() > 0) {
                    //存在原记录，比较登记时间；不存在原记录，插入数据
                    Date registerTimeNew = record.getRegisterTime();
                    for (CspConfRegister register : registerList) {
                        Date registerTimeOld = register.getRegisterTime();
                        if (!registerTimeNew.before(registerTimeOld)) {
                            //新记录登记时间在后(或相等)，删除原数据，插入新数据；否则，不作处理
                            deletes.add(register);
                        } else {
                            isInsert = false;
                        }
                    }
                }

                if (!isInsert) {
                    continue;
                }

                int countP = 0;
                if (personType.equals("1")) {//来宾
                    CspConfVisitorExample visitorExample = new CspConfVisitorExample();
                    visitorExample.createCriteria().andVisitorIdEqualTo(personId).andConfIdEqualTo(confId);
                    countP = (int) cspConfVisitorMapper.countByExample(visitorExample);
                } else if (personType.equals("2")) {//讲师
                    CspConfLecturerExample lecturerExample = new CspConfLecturerExample();
                    lecturerExample.createCriteria().andConfLecturerIdEqualTo(personId).andConfIdEqualTo(confId);
                    countP = (int) cspConfLecturerMapper.countByExample(lecturerExample);
                } else {//personType=3  工作人员
                    CspConfWorkerExample workerExample = new CspConfWorkerExample();
                    workerExample.createCriteria().andWorkerIdEqualTo(personId).andConfIdEqualTo(confId);
                    countP = (int) cspConfWorkerMapper.countByExample(workerExample);
                }
                //本次会议没有该人员时，登记记录不存
                if (countP <= 0) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    String recordStr = objectMapper.writeValueAsString(record);
                    log.info("不属于参会人员，登记记录不存储：{}", recordStr);
                    continue;
                }

                insertCount++;
                String busId = record.getId();

                if (!infoId.isEmpty()) {
                    // 插入数据到关联表
                    SysDataParseInfoToBus infoToBus = new SysDataParseInfoToBus();
                    infoToBus.setInfoId(infoId);
                    infoToBus.setBusId(busId);
                    sysDataParseInfoToBusMapper.insertSelective(infoToBus);
                }

                cspConfRegisterMapper.insert(record);
            }

            if (deletes.size() > 0) {
                List<String> ids = deletes.stream().map(mapper -> mapper.getId()).collect(Collectors.toList());
                CspConfRegisterExample example1 = new CspConfRegisterExample();
                example1.createCriteria().andIdIn(ids);
                List<CspConfRegister> backups = cspConfRegisterMapper.selectByExample(example1);
                deleteCount = backups.size();
                //备份需要删除的数据
                registerBMapper.insertBatch(backups);
                for (CspConfRegister register : backups){
                    cspConfRegisterMapper.deleteByPrimaryKey(register.getId());
                }
                returnValue.setExist(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        log.info("Register---===> allCount {} insert {} delete {}", records.size(), insertCount, deleteCount);

        returnValue.setInsertCount(insertCount);
        return returnValue;
    }

    /**
     * 将数据转化为CspConfRegister对象
     * @param registers
     * @return
     */
    public List dataFormat(List<Register> registers){
        List result = new ArrayList<>();
        //存放需要删除数据
        //List<CspConfRegister> delRecords = new ArrayList<>();
        //存放需要插入的登记信息
        List<CspConfRegister> records = new ArrayList<>();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (Register register : registers){
            CspConfRegister record = new CspConfRegister();
            //CspConfRegisterExample example = new CspConfRegisterExample();
            //CspConfRegisterExample.Criteria criteria = example.createCriteria();
            String confId = register.getConfId();
            if (confId != null && confId.trim().length() > 0 && !"null".equals(confId.trim())){
                //criteria.andConfIdEqualTo(confId);
                record.setConfId(confId);
            } else {
                throw new RuntimeException("存在confId为空的数据");
            }

            String personId = register.getPersonId();
            if (personId != null && personId.trim().length() > 0 && !"null".equals(personId.trim())){
                //criteria.andPersonIdEqualTo(personId);
                record.setPersonId(personId);
            } else {
                throw new RuntimeException("存在personId为空的数据");
            }

            String personType = register.getPersonType();
            if (personType != null && personType.trim().length() > 0 && !"null".equals(personType.trim()) ){
                //criteria.andPersonTypeEqualTo(personType);
                record.setPersonType(personType);
            } else {
                throw new RuntimeException("存在personType为空的数据");
            }

            //List<CspConfRegister> delRecord = cspConfRegisterMapper.selectByExample(example);
            //delRecords.addAll(delRecord);
            //登记时，operatorType没有用到
//            if (register.getOperatorType().trim().length() > 0 && !"null".equals(register.getOperatorType().trim())){
//                int operatorType = Integer.parseInt(register.getOperatorType());
//                //operatorType=2时，数据不入库
//                if (operatorType == 2){
//                    continue;
//                }
//            }

            record.setId(Identities.uuid());
            record.setConfPlaceId(register.getConfPlaceId());
            record.setRemark(register.getRemark());
            record.setRegisterBy(register.getRegisterBy());
            record.setCreateBy(register.getCreateBy());
            record.setUpdateBy(register.getUpdateBy());
            record.setSrcId(register.getSrcId());
            record.setIsScancard(register.getIsScancard());
            record.setTerminalId(register.getTerminalId());

            try {
                String registerTime = register.getRegisterTime();
                if (registerTime != null && registerTime.trim().length() > 0 && !"null".equals(registerTime.trim())){
                    record.setRegisterTime(sf.parse(registerTime));
                } else {
                    throw new RuntimeException("存在registerTime为空的数据");
                }
                String createTime = register.getCreateTime();
                if (createTime != null && createTime.trim().length() > 0 && !"null".equals(createTime.trim())){
                    record.setCreateTime(sf.parse(createTime));
                }

                String updateTime = register.getUpdateTime();
                if (updateTime != null && updateTime.trim().length() > 0 && !"null".equals(updateTime.trim())){
                    record.setUpdateTime(sf.parse(updateTime));
                }
            } catch (ParseException e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }

            records.add(record);
        }

        //result.add(delRecords);
        result.add(records);
        return result;
    }

    public static class HandlerRegisterResult{
        //CSP_CONF_REGISTER 表中是否存在数据
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
