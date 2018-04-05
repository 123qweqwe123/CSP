package com.lmbx.csp.web.dataversion.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lmbx.csp.core.utils.DictVersionUtils;
import com.lmbx.csp.data.sys.domain.SysDataIssueVersion;
import com.lmbx.csp.web.dataversion.filter.DataVersionFilter;
import com.lmbx.csp.web.dataversion.mapper.MySysDataIssueVersionMapper;
import com.lmbx.csp.web.dataversion.service.DataVersionService;

/**
 * TODO
 * 
 * @author qianyanan 2017年12月26日
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DataVersionServiceImpl implements DataVersionService {

    @Autowired
    private MySysDataIssueVersionMapper sysDataIssueVersionMapper;

    @Override
    public List<?> querySysDataIssueVersion(DataVersionFilter dataVersionFilter) {
        if ("".equals(dataVersionFilter.getLccCode())) {
            dataVersionFilter.setLccCode(null);
        }
        List<SysDataIssueVersion> sysDataIssueVersions = sysDataIssueVersionMapper.selectByDataVersionFilter(dataVersionFilter);
        if (sysDataIssueVersions.size() > 0) {
            for (SysDataIssueVersion dataIssueVersion : sysDataIssueVersions) {
                String dictName = dataIssueVersion.getDictName();
                String dictType = dataIssueVersion.getDictType();
                // version.setDictName(dictName + "(" + dictType + ")");
                dataIssueVersion.setDictName(dictName + "(" + dictType + ")");
            }
        }
        return sysDataIssueVersions;
    }

    @Override
    public void updateSysDataIssueVersion(SysDataIssueVersion sysDataIssueVersion) {
        String versionCode = DictVersionUtils.getDictVersion(sysDataIssueVersion.getDictVersion());
        sysDataIssueVersion.setDictVersion(versionCode);
        sysDataIssueVersion.setDictType(null);
        sysDataIssueVersionMapper.updateDictVersion(sysDataIssueVersion);
    }

}
