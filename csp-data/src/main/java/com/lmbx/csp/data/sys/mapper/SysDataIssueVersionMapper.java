package com.lmbx.csp.data.sys.mapper;

import com.lmbx.csp.data.sys.domain.SysDataIssueVersion;
import com.lmbx.csp.data.sys.domain.SysDataIssueVersionExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysDataIssueVersionMapper {
    long countByExample(SysDataIssueVersionExample example);

    int deleteByExample(SysDataIssueVersionExample example);

    int deleteByPrimaryKey(String id);

    int insert(SysDataIssueVersion record);

    int insertSelective(SysDataIssueVersion record);

    List<SysDataIssueVersion> selectByExample(SysDataIssueVersionExample example);

    SysDataIssueVersion selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysDataIssueVersion record, @Param("example") SysDataIssueVersionExample example);

    int updateByExample(@Param("record") SysDataIssueVersion record, @Param("example") SysDataIssueVersionExample example);

    int updateByPrimaryKeySelective(SysDataIssueVersion record);

    int updateByPrimaryKey(SysDataIssueVersion record);
}