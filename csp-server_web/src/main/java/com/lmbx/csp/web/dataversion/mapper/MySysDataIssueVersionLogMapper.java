package com.lmbx.csp.web.dataversion.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lmbx.csp.data.sys.domain.SysDataIssueVersionLog;

/**
 * TODO
 * 
 * @author qianyanan
 *
 *         2017年12月26日
 */
@Mapper
public interface MySysDataIssueVersionLogMapper {

    /**
     * 查询最新手机端和pc端安装包
     * 
     * @return
     */
    List<SysDataIssueVersionLog> selectCurrentDataVersion();

    /**
     * 查询历史手机端和pc端安装包
     * 
     * @param sysDataIssueVersionLog
     * @return
     */
    List<SysDataIssueVersionLog> selectDataVersionLog(SysDataIssueVersionLog sysDataIssueVersionLog);

}