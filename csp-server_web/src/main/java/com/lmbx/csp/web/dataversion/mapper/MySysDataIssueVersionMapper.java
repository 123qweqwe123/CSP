package com.lmbx.csp.web.dataversion.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lmbx.csp.data.sys.domain.SysDataIssueVersion;
import com.lmbx.csp.web.dataversion.filter.DataVersionFilter;

/**
 * TODO
 * 
 * @author qianyanan
 *
 *         2017年12月26日
 */
@Mapper
public interface MySysDataIssueVersionMapper {

    /**
     * 查询所有会议对应数据包信息
     * 
     * @param dataVersionFilter
     * @return
     */
    List<SysDataIssueVersion> selectByDataVersionFilter(DataVersionFilter dataVersionFilter);

    /**
     * 手动更新版本
     * 
     * @param record
     * @return
     */
    int updateDictVersion(SysDataIssueVersion record);

}