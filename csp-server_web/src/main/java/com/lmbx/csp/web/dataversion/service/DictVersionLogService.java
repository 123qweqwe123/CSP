package com.lmbx.csp.web.dataversion.service;

import java.util.List;

import com.lmbx.csp.data.sys.domain.SysDataIssueVersionLog;

/**
 * TODO
 * 
 * @author qianyanan
 *
 *         2017年12月26日
 */
public interface DictVersionLogService {

    /**
     * 查询最新安装包信息
     * 
     * @return
     */
    public List<?> querySysDataIssueVersionLog();

    /**
     * 查询历史安装包信息
     * 
     * @param sysDataIssueVersionLog
     * @return
     */
    public List<?> querySysDataIssueVersionLog(SysDataIssueVersionLog sysDataIssueVersionLog);

    /**
     * 创建历史安装包
     * 
     * @param sysDataIssueVersionLog
     */
    public void createSysDataIssueVersionLog(SysDataIssueVersionLog sysDataIssueVersionLog);

}
