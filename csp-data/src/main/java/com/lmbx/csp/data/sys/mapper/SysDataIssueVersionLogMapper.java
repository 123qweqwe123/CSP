package com.lmbx.csp.data.sys.mapper;

import com.lmbx.csp.data.sys.domain.SysDataIssueVersionLog;
import com.lmbx.csp.data.sys.domain.SysDataIssueVersionLogExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysDataIssueVersionLogMapper {
    long countByExample(SysDataIssueVersionLogExample example);

    int deleteByExample(SysDataIssueVersionLogExample example);

    int deleteByPrimaryKey(String id);

    int insert(SysDataIssueVersionLog record);

    int insertSelective(SysDataIssueVersionLog record);

    List<SysDataIssueVersionLog> selectByExample(SysDataIssueVersionLogExample example);

    SysDataIssueVersionLog selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysDataIssueVersionLog record, @Param("example") SysDataIssueVersionLogExample example);

    int updateByExample(@Param("record") SysDataIssueVersionLog record, @Param("example") SysDataIssueVersionLogExample example);

    int updateByPrimaryKeySelective(SysDataIssueVersionLog record);

    int updateByPrimaryKey(SysDataIssueVersionLog record);
}