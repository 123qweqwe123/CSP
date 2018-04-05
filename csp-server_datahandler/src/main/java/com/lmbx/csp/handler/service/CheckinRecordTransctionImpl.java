package com.lmbx.csp.handler.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lmbx.csp.data.conf.domain.CspConfCheckinPersonExample;
import com.lmbx.csp.data.conf.domain.CspConfCheckinWorkerExample;
import com.lmbx.csp.data.conf.mapper.CspConfCheckinPersonMapper;
import com.lmbx.csp.data.conf.mapper.CspConfCheckinWorkerMapper;
import com.lmbx.csp.handler.data.vo.CheckinRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lmbx.csp.core.utils.Identities;
import com.lmbx.csp.data.conf.domain.CspConfCheckinRecord;
import com.lmbx.csp.data.conf.domain.CspConfCheckinRecordExample;
import com.lmbx.csp.data.conf.mapper.CspConfCheckinRecordMapper;
import com.lmbx.csp.data.sys.domain.SysDataParseInfoToBus;
import com.lmbx.csp.data.sys.mapper.SysDataParseInfoToBusMapper;
import com.lmbx.csp.handler.data.mapper.CheckinRecordBMapper;

/**
 * @author yrj
 * 2017-11
 */
@Service
public class CheckinRecordTransctionImpl {
    Logger log = LoggerFactory.getLogger(CheckinRecordTransctionImpl.class);
    @Autowired
    private CspConfCheckinRecordMapper cspConfCheckinRecordMapper;
    @Autowired
    private SysDataParseInfoToBusMapper sysDataParseInfoToBusMapper;
    @Autowired
    private CheckinRecordBMapper checkinRecordBMapper;
    @Autowired
    private CspConfCheckinPersonMapper cspConfCheckinPersonMapper;
    @Autowired
    private CspConfCheckinWorkerMapper cspConfCheckinWorkerMapper;

    /**
     * 签到数据处理
     * @param list
     * @param projectId
     * @param infoId
     * @return 成功插入的条数 ，未包括更新的数量
     * @throws Exception
     */
    @Transactional(rollbackFor = { RuntimeException.class, Exception.class, Error.class })
    public HandlerCheckinRecordResult parseCheckinRecord(List<CheckinRecord> list, String projectId, String infoId) throws Exception {
        if (list.size() == 0) {
            return null;
        }

        List result = dataFormat(list);
        //取消签到记录
        List<CspConfCheckinRecord> recordBs = (List<CspConfCheckinRecord>) result.get(0);
        //签到记录
        List<CspConfCheckinRecord> records = (List<CspConfCheckinRecord>) result.get(1);

        HandlerCheckinRecordResult returnValue = new HandlerCheckinRecordResult();
        int insertCount = 0;
        int deleteCount = 0;

        try {
            //签到表需要删除的数据
            List<CspConfCheckinRecord> deletes = new ArrayList<>();
            // 插入取消签到的记录(需要插入签到备份表的数据：取消签到的数据+签到表删除的数据)
            if (recordBs.size() > 0) {
                for (CspConfCheckinRecord record : recordBs) {
                    List<CspConfCheckinRecord> cancalResult = handleCancelCheckin(record);
                    if (cancalResult.size() > 0){
                        deletes.addAll(cancalResult);
                    }
                }

                //插入取消签到的数据
                checkinRecordBMapper.insertBatch(recordBs);
                log.info("CheckinRecord--checkinRecord_B==> insert {}", recordBs.size() + deleteCount);
            }

            //签到表需要插入的数据
            List<CspConfCheckinRecord> inserts = new ArrayList<>();
            if (records.size() > 0) {
                for (CspConfCheckinRecord record : records) {
                    List recordRet = handleCheckin(record);
                    boolean isInsert = (boolean) recordRet.get(0);
                    List<CspConfCheckinRecord> deletes1 = (List<CspConfCheckinRecord>) recordRet.get(1);
                    if (isInsert) {
                        inserts.add(record);
                    }

                    if (deletes1.size() > 0) {
                        deletes.addAll(deletes1);
                    }
                }
            }

            //签到表需要删除的数据
            if (deletes.size() > 0){
                List<String> ids = deletes.stream().map(mapper -> mapper.getId()).collect(Collectors.toList());
                CspConfCheckinRecordExample example1 = new CspConfCheckinRecordExample();
                example1.createCriteria().andIdIn(ids);
                List<CspConfCheckinRecord> backups = cspConfCheckinRecordMapper.selectByExample(example1);
                deleteCount = backups.size();
                //备份需要删除的数据
                checkinRecordBMapper.insertBatch(backups);
                for (CspConfCheckinRecord record : backups){
                    cspConfCheckinRecordMapper.deleteByPrimaryKey(record.getId());
                }
                returnValue.setExist(true);
                //log.info("CheckinRecord--checkinRecord_B==> backup {}", backups.size());
            }

            //签到表插入数据
            for (CspConfCheckinRecord record : inserts) {
                String visitorId = record.getVisitorId();
                String checkinId = record.getCheckinId();
                CspConfCheckinPersonExample personExample = new CspConfCheckinPersonExample();
                personExample.createCriteria().andPersonIdEqualTo(visitorId).andCheckinIdEqualTo(checkinId);
                int countP = (int) cspConfCheckinPersonMapper.countByExample(personExample);
                if (countP <= 0) {
                    CspConfCheckinWorkerExample workerExample = new CspConfCheckinWorkerExample();
                    workerExample.createCriteria().andCheckinIdEqualTo(checkinId).andWorkerIdEqualTo(visitorId);
                    int countW = (int) cspConfCheckinWorkerMapper.countByExample(workerExample);
                    if (countW <= 0){
                        ObjectMapper objectMapper = new ObjectMapper();
                        String recordStr = objectMapper.writeValueAsString(record);
                        log.info("不属于需要签到的人员，签到记录不存储：{}", recordStr);
                        continue;
                    }
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
                cspConfCheckinRecordMapper.insert(record);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        log.info("CheckinRecord--checkinRecord==> allCount {} insert {} delete {}", list.size(), insertCount, deleteCount);

        returnValue.setInsertCount(insertCount);
        return returnValue;
    }

    /**
     * 处理取消签到信息
     * @param record
     * @return
     */
    public List<CspConfCheckinRecord> handleCancelCheckin(CspConfCheckinRecord record) {
        //更新时间
        Date updateTime = record.getUpdateTime();
        //参会人员编号
        String vcNo = record.getVisitorConfNo();
        //签到Id
        String checkinId = record.getCheckinId();
        CspConfCheckinRecordExample example = new CspConfCheckinRecordExample();
        example.createCriteria().andVisitorConfNoEqualTo(vcNo).andCheckinIdEqualTo(checkinId);
        List<CspConfCheckinRecord> checkinRecords = cspConfCheckinRecordMapper.selectByExample(example);
        //需要删除的数据
        List<CspConfCheckinRecord> deletes = new ArrayList<>();
        if (checkinRecords.size() > 0) {
            //存在签到记录，比较更新时间
            for (CspConfCheckinRecord recordOld: checkinRecords) {
                Date updateOld = recordOld.getUpdateTime();
                if (!updateTime.before(updateOld)) {
                    //取消签到的时间在后(或相等)，则删除签到记录(备份到签到备份表)
                    deletes.add(recordOld);
                }
            }
        }

        return deletes;
    }

    /**
     * 处理签到信息
     * @param record
     * @return
     */
    public List handleCheckin(CspConfCheckinRecord record) {
        List result = new ArrayList();
        //签到表需要删除的数据
        List<CspConfCheckinRecord> deletes = new ArrayList<>();
        boolean isInsert = true;
        //更新时间
        Date updateTime = record.getUpdateTime();
        //参会人员编号
        String vcNo = record.getVisitorConfNo();
        //签到Id
        String checkinId = record.getCheckinId();
        CspConfCheckinRecordExample example = new CspConfCheckinRecordExample();
        example.createCriteria().andVisitorConfNoEqualTo(vcNo).andCheckinIdEqualTo(checkinId);
        List<CspConfCheckinRecord> checkinRecords = cspConfCheckinRecordMapper.selectByExample(example);
        if (checkinRecords.size() > 0) {
            //存在签到记录，比较更新时间
            for (CspConfCheckinRecord checkin : checkinRecords) {
                Date updateOld = checkin.getUpdateTime();
                if (!updateTime.before(updateOld)) {
                    //新记录时间在后(或相等)，则备份旧记录到备份表后删除，插入新记录；否则，新记录不作处理
                    deletes.add(checkin);
                } else {
                    isInsert = false;
                }
            }
        } else {
            //不存在签到记录，比较备份表中同一人的最大更新时间
            List<Map<String, Object>> maxList = checkinRecordBMapper.getMaxDate(vcNo, checkinId);
            if (maxList.size() > 0 && maxList.get(0) != null) {
                Date updateMax = (Date) maxList.get(0).get("MAXTIME");
                if (updateMax.after(updateTime)) {
                    //新记录时间较小，不作处理
                    isInsert = false;
                }
            }
        }

        result.add(isInsert);
        result.add(deletes);
        return result;
    }

    /**
     * 将数据转化为CspConfCheckinRecord对象
     * @param checkinRecords
     * @return
     */
    public List dataFormat(List<CheckinRecord> checkinRecords){
        List result = new ArrayList<>();
        //存放需要插入的签到信息
        List<CspConfCheckinRecord> records = new ArrayList<>();
        //存放取消签到的信息（插入到签到备份表）
        List<CspConfCheckinRecord> cancelRecords = new ArrayList<>();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (CheckinRecord checkinRecord : checkinRecords) {
            CspConfCheckinRecord record = new CspConfCheckinRecord();
            record.setId(Identities.uuid());
            String visitorConfNo = checkinRecord.getVisitorConfNo();
            if (visitorConfNo != null && visitorConfNo.trim().length() == 0 || "null".equals(visitorConfNo)) {
                throw new RuntimeException("存在visitorConfNo为空的数据");
            }
            String checkinId = checkinRecord.getCheckinId();
            if (checkinId != null && checkinId.trim().length() == 0 || "null".equals(checkinId)) {
                throw new RuntimeException("存在checkinId为空的数据");
            }
            record.setVisitorConfNo(visitorConfNo);
            record.setVisitorId(checkinRecord.getVisitorId());
            record.setConfId(checkinRecord.getConfId());
            record.setConfPlaceId(checkinRecord.getConfPlaceId());
            record.setRemark(checkinRecord.getRemark());
            record.setCheckinBy(checkinRecord.getCheckinBy());
            record.setCreateBy(checkinRecord.getCreateBy());
            record.setUpdateBy(checkinRecord.getUpdateBy());
            record.setSrcId(checkinRecord.getSrcId());
            record.setCheckinId(checkinId);
            String checkinSource = checkinRecord.getCheckinSource();
            if (checkinSource != null && checkinSource.trim().length() > 0 && !"null".equals(checkinSource.trim())) {
                record.setCheckinSource(Short.valueOf(checkinSource));
            }

            record.setTerminalId(checkinRecord.getTerminalId());

            try {
                String checkinTime = checkinRecord.getCheckinTime();
                if (checkinTime != null && checkinTime.trim().length() > 0 && !"null".equals(checkinTime.trim())) {
                    record.setCheckinTime(sf.parse(checkinTime));
                }
                String createTime = checkinRecord.getCreateTime();
                if (createTime != null && createTime.trim().length() > 0 && !"null".equals(createTime.trim())) {
                    record.setCreateTime(sf.parse(createTime));
                }
                String updateTime = checkinRecord.getUpdateTime();
                if (updateTime != null && updateTime.trim().length() > 0 && !"null".equals(updateTime.trim())) {
                    record.setUpdateTime(sf.parse(updateTime));
                } else {
                    throw new RuntimeException("存在updateTime为空的数据");
                }
            } catch (ParseException e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }

            String operatorType = checkinRecord.getOperatorType();
            if (operatorType != null && operatorType.trim().length() > 0 && !"null".equals(operatorType.trim())) {
                int operator = Integer.parseInt(operatorType);
                if (operator == 2) {
                    cancelRecords.add(record);
                } else {
                    records.add(record);
                }
            } else {
                throw new RuntimeException("存在operatorType为空的数据");
            }
        }

        result.add(cancelRecords);
        result.add(records);
        return result;
    }

    public static class HandlerCheckinRecordResult {

        // CSP_CONF_CHECKIN_RECORD 表中是否存在数据
        private boolean isExist     = false;
        private int     insertCount = 0;

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
