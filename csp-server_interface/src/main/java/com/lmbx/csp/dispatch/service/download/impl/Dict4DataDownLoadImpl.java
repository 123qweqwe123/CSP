package com.lmbx.csp.dispatch.service.download.impl;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lmbx.csp.data.conf.domain.CspConfCheckin;
import com.lmbx.csp.data.conf.domain.CspConfCheckinExample;
import com.lmbx.csp.data.conf.domain.CspConfCheckinPerson;
import com.lmbx.csp.data.conf.domain.CspConfCheckinRecord;
import com.lmbx.csp.data.conf.domain.CspConfCheckinRecordExample;
import com.lmbx.csp.data.conf.domain.CspConfCheckinWorker;
import com.lmbx.csp.data.conf.domain.CspConference;
import com.lmbx.csp.data.conf.domain.CspConferenceExample;
import com.lmbx.csp.data.conf.mapper.CspConfCheckinMapper;
import com.lmbx.csp.data.conf.mapper.CspConfCheckinRecordMapper;
import com.lmbx.csp.data.conf.mapper.CspConferenceMapper;
import com.lmbx.csp.data.main.domain.CspMainPerson;
import com.lmbx.csp.data.sys.domain.SysAccount;
import com.lmbx.csp.dispatch.mapper.MyCspConfCheckinPersonMapper;
import com.lmbx.csp.dispatch.mapper.MyCspConfCheckinWorkerMapper;
import com.lmbx.csp.dispatch.mapper.MyCspMainPersonMapper;
import com.lmbx.csp.dispatch.mapper.MySysAccountMapper;
import com.lmbx.csp.dispatch.service.download.dto.VersionDTO;

/**
 * @author wanghongwei
 */
@Service
public class Dict4DataDownLoadImpl extends AbstractDataDownLoad {

    @Autowired
    private CspConferenceMapper cspConferenceMapper;

    @Autowired
    private CspConfCheckinMapper cspConfCheckinMapper;

    @Autowired
    private CspConfCheckinRecordMapper cspConfCheckinRecordMapper;

    @Autowired
    private MyCspConfCheckinWorkerMapper mycspConfCheckinWorkerMapper;

    @Autowired
    private MySysAccountMapper mysysAccountMapper;

    @Autowired
    private MyCspConfCheckinPersonMapper mycspConfCheckinPersonMapper;

    @Autowired
    private MyCspMainPersonMapper myCspMainPersonMapper;

    @Override
    public List<EntityInZip> getEntitys(VersionDTO vervionDto) {
        List<EntityInZip> returnList = new ArrayList<EntityInZip>();
        String confNo = vervionDto.getConfNo();
        CspConferenceExample example = new CspConferenceExample();
        example.createCriteria().andConfNoEqualTo(confNo);
        List<CspConference> cspConferenceList = cspConferenceMapper.selectByExample(example);
        if (cspConferenceList.size() != 0) {
            CspConference cspConference = cspConferenceList.get(0);
            // 会议签到信息
            List<CspConfCheckin> checkInList = getConfCheckIn(cspConference.getId());
            // 签到记录信息
            List<CspConfCheckinRecord> checkinRecordList = getConfCheckInRecord(cspConference.getId());
            // 签到工作人员信息
            List<CspConfCheckinWorker> checkInWorkerList = getConfCheckInWorker(confNo);
            // 签到讲师来宾信息
            List<CspConfCheckinPerson> checkInPersonList = getConfcheckInPersonList(confNo);
            // 签到人员详细信息
            List<CspMainPerson> mainPersonInfoList = getCheckInInfo(confNo);
            // 人员账户信息
            List<SysAccount> sysAccounts = getSysAccount(confNo);

            try {
                ObjectMapper mapper = new ObjectMapper();
                mapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, true);
                // 会议签到信息
                addEntity("CSP_CONF_CHECKIN.json", mapper.writeValueAsString(checkInList), returnList);
                // 签到记录信息
                addEntity("CSP_CONF_CHECKIN_RECORD.json", mapper.writeValueAsString(checkinRecordList), returnList);
                // 签到工作人员信息
                addEntity("CSP_CONF_CHECKIN_WORKER.json", mapper.writeValueAsString(checkInWorkerList), returnList);
                // 签到讲师来宾信息
                addEntity("CSP_CONF_CHECKIN_PERSON.json", mapper.writeValueAsString(checkInPersonList), returnList);
                // 签到人员详细信息
                addEntity("CSP_MAIN_PERSON.json", mapper.writeValueAsString(mainPersonInfoList), returnList);
                // 人员账户信息
                addEntity("SYS_ACCOUNT.json", mapper.writeValueAsString(sysAccounts), returnList);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return returnList;

        }
        return null;

    }

    /**
     * 会议签到表
     *
     * @param confId
     * @return
     */
    private List<CspConfCheckin> getConfCheckIn(String confId) {
        CspConfCheckinExample checkinExample = new CspConfCheckinExample();
        checkinExample.createCriteria().andConfIdEqualTo(confId);
        List<CspConfCheckin> cspConfCheckinList = cspConfCheckinMapper.selectByExample(checkinExample);
        return cspConfCheckinList;
    }

    /**
     * 签到记录表
     *
     * @param confId
     * @return
     */
    private List<CspConfCheckinRecord> getConfCheckInRecord(String confId) {
        CspConfCheckinRecordExample checkInRecordExample = new CspConfCheckinRecordExample();
        checkInRecordExample.createCriteria().andConfIdEqualTo(confId);
        List<CspConfCheckinRecord> cspConfCheckInRecordList = cspConfCheckinRecordMapper.selectByExample(checkInRecordExample);
        return cspConfCheckInRecordList;
    }

    /**
     * 签到工作人员
     *
     * @param confNo
     * @return
     */
    private List<CspConfCheckinWorker> getConfCheckInWorker(String confNo) {
        List<CspConfCheckinWorker> checkInWorkersList = mycspConfCheckinWorkerMapper.selectCheckInWorkersByConfNo(confNo);

        return checkInWorkersList;
    }

    /**
     * 签到讲师来宾表
     *
     * @param confNo
     * @return
     */
    private List<CspConfCheckinPerson> getConfcheckInPersonList(String confNo) {

        List<CspConfCheckinPerson> cspConfCheckinPersonList = mycspConfCheckinPersonMapper.selectCheckInPersonsByConfNo(confNo);
        CspConferenceExample example1 = new CspConferenceExample();
        example1.createCriteria().andConfNoEqualTo(confNo);
        return cspConfCheckinPersonList;
    }

    /**
     * 签到人员详细信息
     *
     * @param confNo
     * @return
     */
    private List<CspMainPerson> getCheckInInfo(String confNo) {
        // 工作人员所对详细信息
        List<CspMainPerson> cspMainPersonList = myCspMainPersonMapper.selectCheckInWorkersByConfNo(confNo);
        // 讲师来宾所对详细信息
        List<CspMainPerson> cspMainPersonList1 = myCspMainPersonMapper.selectCheckInPersonByConfNo(confNo);
        for (CspMainPerson cspMainPerson : cspMainPersonList) {
            cspMainPersonList1.add(cspMainPerson);
        }
        return cspMainPersonList1;
    }

    /**
     * 工作人员账户信息
     *
     * @param confNo
     * @return
     */
    private List<SysAccount> getSysAccount(String confNo) {
        List<SysAccount> checkInAccounts = mysysAccountMapper.selectCheckInAccountByConfNo(confNo);

        return checkInAccounts;
    }

    @Override
    public boolean needEncode() {
        return false;
    }

    @Override
    public boolean needZip() {
        return true;
    }

}
