package com.lmbx.csp.dispatch.service.download.impl;

import com.lmbx.csp.core.qrcode.PersonQRCodeUtils;
import com.lmbx.csp.data.conf.domain.*;
import com.lmbx.csp.data.conf.mapper.CspConfPlaceMapper;
import com.lmbx.csp.data.conf.mapper.CspConfWorkerMapper;
import com.lmbx.csp.data.conf.mapper.CspConferenceMapper;
import com.lmbx.csp.data.main.domain.CspMainPerson;
import com.lmbx.csp.data.main.domain.CspMainPersonExample;
import com.lmbx.csp.data.main.mapper.CspMainPersonMapper;
import com.lmbx.csp.data.sys.domain.SysAccount;
import com.lmbx.csp.dispatch.mapper.MyCspMainPersonMapper;
import com.lmbx.csp.dispatch.mapper.MySysAccountMapper;
import com.lmbx.csp.dispatch.service.download.dto.VersionDTO;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wanghongwei
 */
@Service
public class Dict2DataDownLoadImpl extends AbstractDataDownLoad {

    @Autowired
    private CspConferenceMapper   cspConferenceMapper;

    @Autowired
    private CspConfWorkerMapper   cspConfWorkerMapper;

    @Autowired
    private MySysAccountMapper    mysysAccountMapper;

    @Autowired
    private CspConfPlaceMapper    cspConfPlaceMapper;

    @Autowired
    private CspMainPersonMapper   cspMainPersonMapper;

    @Autowired
    private MyCspMainPersonMapper myCspMainPersonMapper;

    @Override
    public List<EntityInZip> getEntitys(VersionDTO versionDTO) {
        List<EntityInZip> returnList = new ArrayList<EntityInZip>();
        String confNo = versionDTO.getConfNo();
        // 会议基本信息
        CspConference cspConference = getConferenceInfo(confNo);
        if (cspConference != null) {
            // 课程（分会场）信息
            List<CspConfPlace> confPlace = getConfPlace(cspConference.getId());
            // 工作人员
            List<CspConfWorker> cspConfWorkerList = getConfWork(cspConference.getId(), cspConference.getConfNo());
            // 人员信息
            List<CspMainPerson> workerInfo = getWorkerInfo(confNo);
            // 人员账户信息
            List<SysAccount> sysAccounts = getSysAccount(confNo);
            try {
                ObjectMapper mapper = new ObjectMapper();
                mapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, true);
                // 会议信息
                addEntity("CSP_CONFERENCE.json", mapper.writeValueAsString(cspConference), returnList);
                // 工作人员
                addEntity("CSP_CONF_WORKER.json", mapper.writeValueAsString(cspConfWorkerList), returnList);
                // 会场
                addEntity("CSP_CONF_PLACE.json", mapper.writeValueAsString(confPlace), returnList);
                // 工作人员详情
                addEntity("CSP_MAIN_PERSON.json", mapper.writeValueAsString(workerInfo), returnList);
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
     * 工作人员详细信息
     *
     * @param confNo 会议编号
     * @return
     */
    private List<CspMainPerson> getWorkerInfo(String confNo) {

        List<CspMainPerson> baseWorkers = myCspMainPersonMapper.selectBaseWorkersByConfNo(confNo);

        return baseWorkers;
    }

    /**
     * 工作人员账户信息
     *
     * @param confNo
     * @return
     */
    private List<SysAccount> getSysAccount(String confNo) {
        List<SysAccount> baseAccounts = mysysAccountMapper.selectBaseAccountByConfNo(confNo);
        return baseAccounts;
    }

    /**
     * 会议工作人员
     *
     * @param confId 会议id
     * @param confNo 会议编号
     * @return
     */
    private List<CspConfWorker> getConfWork(String confId, String confNo) {
        List<CspConfWorker> workerList = new ArrayList<CspConfWorker>();
        CspConfWorkerExample workExample = new CspConfWorkerExample();
        workExample.createCriteria().andConfIdEqualTo(confId);
        List<CspConfWorker> cspConfWorkerList = cspConfWorkerMapper.selectByExample(workExample);
        if (cspConfWorkerList.size() > 0) {
            for (CspConfWorker cspConfWorker : cspConfWorkerList) {
                String workerId = cspConfWorker.getWorkerId();
                CspMainPersonExample example = new CspMainPersonExample();
                example.createCriteria().andIdEqualTo(workerId);
                List<CspMainPerson> cspMainPeople = cspMainPersonMapper.selectByExample(example);
                if (cspMainPeople.size() > 0) {
                    CspMainPerson person = cspMainPeople.get(0);
                    cspConfWorker.setWorkerConfNo(PersonQRCodeUtils.createPersonQRCode(confNo, person));
                    workerList.add(cspConfWorker);
                }
            }
        }
        return workerList;
    }

    /**
     * 会议 分会场
     *
     * @param confId 会议id
     * @return
     */
    private List<CspConfPlace> getConfPlace(String confId) {
        CspConfPlaceExample placeExample = new CspConfPlaceExample();
        placeExample.createCriteria().andConfIdEqualTo(confId);
        List<CspConfPlace> cspConfPlaceList = cspConfPlaceMapper.selectByExample(placeExample);
        return cspConfPlaceList;
    }

    /**
     * 会议基本信息
     *
     * @param confNo 会议编号
     * @return
     */
    private CspConference getConferenceInfo(String confNo) {
        CspConferenceExample example = new CspConferenceExample();
        example.createCriteria().andConfNoEqualTo(confNo);
        List<CspConference> cspConferenceList = cspConferenceMapper.selectByExample(example);
        if (cspConferenceList != null && cspConferenceList.size() > 0) {
            return cspConferenceList.get(0);
        }
        return null;
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
