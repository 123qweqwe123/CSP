package com.lmbx.csp.dispatch.service.download.impl;

import com.lmbx.csp.core.qrcode.PersonQRCodeUtils;
import com.lmbx.csp.data.conf.domain.*;
import com.lmbx.csp.data.conf.mapper.CspConfLecturerMapper;
import com.lmbx.csp.data.conf.mapper.CspConfRegisterMapper;
import com.lmbx.csp.data.conf.mapper.CspConfVisitorMapper;
import com.lmbx.csp.data.conf.mapper.CspConferenceMapper;
import com.lmbx.csp.data.main.domain.CspMainPerson;
import com.lmbx.csp.data.main.domain.CspMainPersonExample;
import com.lmbx.csp.data.main.mapper.CspMainPersonMapper;
import com.lmbx.csp.dispatch.mapper.MyCspMainPersonMapper;
import com.lmbx.csp.dispatch.service.download.dto.VersionDTO;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wanghongwei
 */
@Service
public class Dict3DataDownLoadImpl extends AbstractDataDownLoad {

    @Autowired
    private CspConferenceMapper   cspConferenceMapper;

    @Autowired
    private CspConfVisitorMapper  cspConfVisitorMapper;

    @Autowired
    private CspConfLecturerMapper cspConfLecturerMapper;

    @Autowired
    private CspConfRegisterMapper cspConfRegisterMapper;

    @Autowired
    private CspMainPersonMapper   cspMainPersonMapper;

    @Autowired
    private MyCspMainPersonMapper myCspMainPersonMapper;

    @Override
    public List<EntityInZip> getEntitys(VersionDTO versionDTO) {
        List<EntityInZip> returnList = new ArrayList<EntityInZip>();
        String confNo = versionDTO.getConfNo();
        CspConferenceExample example = new CspConferenceExample();
        example.createCriteria().andConfNoEqualTo(confNo);
        List<CspConference> cspConferenceList = cspConferenceMapper.selectByExample(example);
        if (cspConferenceList.size() != 0) {
            CspConference cspConference = cspConferenceList.get(0);
            // 会议登记信息-登记表
            List<CspConfRegister> cspConfRegisterList = getConfRegister(cspConference.getId());
            // 会议登记信息-会议来宾
            List<CspConfVisitor> confVisitorList = getConfVisitor(cspConference.getConfNo(), cspConference.getId());
            // 会议登记信息-会议讲师
            List<CspConfLecturer> cspConfLecturerList = getConfLecturer(cspConference.getId(),
                                                                        cspConference.getConfNo());
            // 会议登记信息-人员信息
            List<CspMainPerson> mainPersonInfoList = getRegisterInfo(confNo);

            try {
                ObjectMapper mapper = new ObjectMapper();
                mapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, true);
                // 会议登记信息
                addEntity("CSP_CONF_REGISTER.json", mapper.writeValueAsString(cspConfRegisterList), returnList);
                // 会议登记信息-会议来宾
                addEntity("CSP_CONF_VISITOR.json", mapper.writeValueAsString(confVisitorList), returnList);
                // 会议登记信息-会议讲师
                addEntity("CSP_CONF_LECTURER.json", mapper.writeValueAsString(cspConfLecturerList), returnList);
                // 人员详情
                addEntity("CSP_MAIN_PERSON.json", mapper.writeValueAsString(mainPersonInfoList), returnList);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return returnList;

        }
        return null;
    }

    /**
     * 会议 登记
     *
     * @param confId 会议的id
     * @return
     */
    private List<CspConfRegister> getConfRegister(String confId) {
        CspConfRegisterExample registerExample = new CspConfRegisterExample();
        registerExample.createCriteria().andConfIdEqualTo(confId);
        List<CspConfRegister> cspConfRegisterList = cspConfRegisterMapper.selectByExample(registerExample);
        return cspConfRegisterList;
    }

    /**
     * 会议来宾
     *
     * @param confId 会议的id
     * @param confNo 会议的编号
     * @return
     */
    private List<CspConfVisitor> getConfVisitor(String confNo, String confId) {
        CspConfVisitorExample visitorExample = new CspConfVisitorExample();
        List<CspConfVisitor> visitorList = new ArrayList<>();
        visitorExample.createCriteria().andConfIdEqualTo(confId);
        List<CspConfVisitor> cspConfVisitorList = cspConfVisitorMapper.selectByExample(visitorExample);
        if (cspConfVisitorList.size() > 0) {
            for (CspConfVisitor visitor : cspConfVisitorList) {
                CspConfVisitorExample example1 = new CspConfVisitorExample();
                String id = visitor.getId();
                example1.createCriteria().andIdEqualTo(id);
                String visitorId = visitor.getVisitorId();
                visitorExample.createCriteria().andVisitorIdEqualTo(visitorId);
                CspMainPersonExample example = new CspMainPersonExample();
                example.createCriteria().andIdEqualTo(visitorId);
                List<CspMainPerson> cspMainPeopleList = cspMainPersonMapper.selectByExample(example);
                if (cspMainPeopleList.size() > 0) {
                    CspMainPerson person = cspMainPeopleList.get(0);
                    visitor.setVisitorConfNo(PersonQRCodeUtils.createPersonQRCode(confNo, person));
                    visitorList.add(visitor);
                }

            }
        }
        return visitorList;
    }

    /**
     * 会议讲师
     *
     * @param confId 会议的id
     * @param confNo 会议的编号
     * @return
     */
    private List<CspConfLecturer> getConfLecturer(String confId, String confNo) {
        List<CspConfLecturer> lecturerList = new ArrayList<>();
        CspConfLecturerExample lecturerExample = new CspConfLecturerExample();
        lecturerExample.createCriteria().andConfIdEqualTo(confId);
        List<CspConfLecturer> cspConfLecturerList = cspConfLecturerMapper.selectByExample(lecturerExample);
        if (cspConfLecturerList.size() > 0) {
            for (CspConfLecturer cspConfLecturer : cspConfLecturerList) {
                String confLecturerId = cspConfLecturer.getConfLecturerId();
                CspMainPersonExample example = new CspMainPersonExample();
                example.createCriteria().andIdEqualTo(confLecturerId);
                List<CspMainPerson> cspMainPeopleList = cspMainPersonMapper.selectByExample(example);
                if (cspMainPeopleList.size() > 0) {
                    CspMainPerson cspMainPerson = cspMainPeopleList.get(0);
                    // 调用生成二维码的方法
                    String personQRCode = PersonQRCodeUtils.createPersonQRCode(confNo, cspMainPerson);
                    cspConfLecturer.setLecturerConfNo(personQRCode);
                    lecturerList.add(cspConfLecturer);
                }

            }
        }

        return lecturerList;
    }

    /**
     * 登记人员详细信息
     *
     * @param confNo 会议的编号
     * @return
     */
    private List<CspMainPerson> getRegisterInfo(String confNo) {
        List<CspMainPerson> regLecturersList = myCspMainPersonMapper.selectRegiLecturersByConfNo(confNo);
        List<CspMainPerson> regVisitorsList = myCspMainPersonMapper.selectRegiVistorsByConfNo(confNo);
        if (regVisitorsList.size() > 0) {
            for (CspMainPerson cspMainPerson : regLecturersList) {
                regVisitorsList.add(cspMainPerson);
            }
        }
        return regVisitorsList;
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
