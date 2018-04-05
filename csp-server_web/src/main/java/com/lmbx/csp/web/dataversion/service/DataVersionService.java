package com.lmbx.csp.web.dataversion.service;

import java.util.List;

import com.lmbx.csp.data.sys.domain.SysDataIssueVersion;
import com.lmbx.csp.web.dataversion.filter.DataVersionFilter;

/**
 * TODO
 * 
 * @author qianyanan 2017年12月26日
 */
public interface DataVersionService {

    /**
     * 查询会议对应的数据包
     * 
     * @param dataVersionFilter
     * @return
     */
    public List<?> querySysDataIssueVersion(DataVersionFilter dataVersionFilter);

    /**
     * 手动更新数据包版本
     * 
     * @param sysDataIssueVersion
     */
    public void updateSysDataIssueVersion(SysDataIssueVersion sysDataIssueVersion);

}
