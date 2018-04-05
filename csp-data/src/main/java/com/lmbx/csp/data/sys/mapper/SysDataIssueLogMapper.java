package com.lmbx.csp.data.sys.mapper;

import com.lmbx.csp.data.sys.domain.SysDataIssueLog;
import com.lmbx.csp.data.sys.domain.SysDataIssueLogExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysDataIssueLogMapper {
    long countByExample(SysDataIssueLogExample example);

    int deleteByExample(SysDataIssueLogExample example);

    int deleteByPrimaryKey(String id);

    int insert(SysDataIssueLog record);

    int insertSelective(SysDataIssueLog record);

    List<SysDataIssueLog> selectByExample(SysDataIssueLogExample example);

    SysDataIssueLog selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysDataIssueLog record, @Param("example") SysDataIssueLogExample example);

    int updateByExample(@Param("record") SysDataIssueLog record, @Param("example") SysDataIssueLogExample example);

    int updateByPrimaryKeySelective(SysDataIssueLog record);

    int updateByPrimaryKey(SysDataIssueLog record);
}