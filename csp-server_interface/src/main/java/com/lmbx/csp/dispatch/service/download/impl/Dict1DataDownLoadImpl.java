package com.lmbx.csp.dispatch.service.download.impl;

import com.lmbx.csp.constant.DictTypeConsts;
import com.lmbx.csp.data.conf.domain.*;
import com.lmbx.csp.data.conf.mapper.CspConfCertMapper;
import com.lmbx.csp.data.conf.mapper.CspConfCheckinMapper;
import com.lmbx.csp.data.conf.mapper.CspConfVisitorMapper;
import com.lmbx.csp.data.conf.mapper.CspConferenceMapper;
import com.lmbx.csp.data.sys.domain.SysDataIssueVersion;
import com.lmbx.csp.data.sys.domain.SysDataIssueVersionExample;
import com.lmbx.csp.data.sys.mapper.SysDataIssueVersionMapper;
import com.lmbx.csp.dispatch.service.download.dto.ConfData1;
import com.lmbx.csp.dispatch.service.download.dto.VersionDTO;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author wanghongwei
 */

@Service
public class Dict1DataDownLoadImpl extends AbstractDataDownLoad {

    @Autowired
    private SysDataIssueVersionMapper sysDataIssueVersionMapper;

    @Autowired
    private CspConferenceMapper       cspConferenceMapper;

    @Autowired
    private CspConfVisitorMapper      cspConfVisitorMapper;

    @Autowired
    private CspConfCheckinMapper      cspConfCheckinMapper;

    @Autowired
    private CspConfCertMapper         cspConfCertMapper;

    @Override
    public List<AbstractDataDownLoad.EntityInZip> getEntitys(VersionDTO vervionDto) {
        List<EntityInZip> returnList = new ArrayList<EntityInZip>();
        ConfData1 confData = new ConfData1();
        String jasonString = null;
        List<CspConference> list = this.cspConferenceMapper.selectByExample(null);
        // 当dictType为0和1时
        List<SysDataIssueVersion> versions = getConfDataVersion();
        ConfData1.ConfWithVersion confWithVersion1 = new ConfData1.ConfWithVersion();
        confWithVersion1.setConf(null);
        confWithVersion1.setVersions(versions);
        confData.getConfs().add(confWithVersion1);
        // 当dictType不为0和1时
        for (CspConference conf : list) {
            List<SysDataIssueVersion> versionList = addConfDataVersion(conf);
            ConfData1.ConfWithVersion confWithVersion = new ConfData1.ConfWithVersion();
            confWithVersion.setConf(conf);
            confWithVersion.setVersions(versionList);
            confData.getConfs().add(confWithVersion);
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, true);
            jasonString = mapper.writeValueAsString(confData);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] bytes1 = jasonString.getBytes();
        AbstractDataDownLoad.EntityInZip entity = new AbstractDataDownLoad.EntityInZip();
        entity.entityName = "cspConference.json";
        entity.entityData = bytes1;
        returnList.add(entity);
        return returnList;
    }

    /**
     * 获取会议三个数据包的版本号 先从版本记录表中查询版本号， 若版本号存在，再判断此版本号是否有数据
     *
     * @param conf
     * @return
     */
    private List<SysDataIssueVersion> addConfDataVersion(CspConference conf) {
        SysDataIssueVersionExample example = new SysDataIssueVersionExample();
        example.createCriteria().andLccCodeEqualTo(conf.getConfNo());
        List<SysDataIssueVersion> versionList = this.sysDataIssueVersionMapper.selectByExample(example);
        //
        Set<String> dictTypes = new TreeSet<String>();
        for (SysDataIssueVersion versionIsuue : versionList) {
            String dictType = versionIsuue.getDictType();
            dictTypes.add(dictType);
        }
        if (!dictTypes.contains(DictTypeConsts.CONFERENCEBASEINFO_TYPE)) {
            SysDataIssueVersion version = new SysDataIssueVersion();
            version.setProjectId("009");
            version.setLccCode(conf.getConfNo());
            version.setDictType("2");
            version.setDictVersion("0");
            versionList.add(version);
        }

        if (!dictTypes.contains(DictTypeConsts.CONFERENCEREG_TYPE)) {
            SysDataIssueVersion version = new SysDataIssueVersion();
            version.setProjectId("009");
            version.setLccCode(conf.getConfNo());
            version.setDictType("3");
            version.setDictVersion("0");
            versionList.add(version);

            String confId = conf.getId();
            CspConfVisitorExample confConfVisitorExample = new CspConfVisitorExample();
            confConfVisitorExample.createCriteria().andConfIdEqualTo(confId);
            List<CspConfVisitor> visitorList = this.cspConfVisitorMapper.selectByExample(confConfVisitorExample);
            // 判断 是否存在数据
            if (visitorList.size() == 0) {
                version.setDictVersion("-2");
            }
        }

        if (!dictTypes.contains(DictTypeConsts.CONFERENCECHECK_TYPE)) {
            SysDataIssueVersion version = new SysDataIssueVersion();
            version.setProjectId("009");
            version.setLccCode(conf.getConfNo());
            version.setDictType("4");
            version.setDictVersion("0");
            versionList.add(version);

            String confId = conf.getId();
            CspConfCheckinExample checkinExample = new CspConfCheckinExample();
            checkinExample.createCriteria().andConfIdEqualTo(confId);
            List<CspConfCheckin> checkinList = this.cspConfCheckinMapper.selectByExample(checkinExample);
            // 判断 是否存在数据
            if (checkinList.size() == 0) {
                version.setDictVersion("-2");
            }
        }
        if (!dictTypes.contains(DictTypeConsts.CONFERENCEIDENTITY_TYPE)) {
            SysDataIssueVersion version = new SysDataIssueVersion();
            version.setProjectId("009");
            version.setLccCode(conf.getConfNo());
            version.setDictType("5");
            version.setDictVersion("0");
            versionList.add(version);

            String confId = conf.getId();
            CspConfCertExample certExample = new CspConfCertExample();
            certExample.createCriteria().andConfIdEqualTo(confId);
            List<CspConfCert> cspConfCertList = this.cspConfCertMapper.selectByExample(certExample);
            // 判断 是否存在数据
            if (cspConfCertList.size() == 0) {
                version.setDictVersion("-2");
            }
        }
        return versionList;
    }

    /**
     * 当dictType为0和1时的版本信息
     * 
     * @return
     */
    private List<SysDataIssueVersion> getConfDataVersion() {
        SysDataIssueVersionExample example = new SysDataIssueVersionExample();
        example.createCriteria().andDictTypeEqualTo("0");
        example.or().andDictTypeEqualTo("1");
        List<SysDataIssueVersion> versionList = this.sysDataIssueVersionMapper.selectByExample(example);
        return versionList;
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
