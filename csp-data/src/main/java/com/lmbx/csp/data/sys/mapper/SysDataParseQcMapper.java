package com.lmbx.csp.data.sys.mapper;

import com.lmbx.csp.data.sys.domain.SysDataParseQc;
import com.lmbx.csp.data.sys.domain.SysDataParseQcExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysDataParseQcMapper {
    long countByExample(SysDataParseQcExample example);

    int deleteByExample(SysDataParseQcExample example);

    int deleteByPrimaryKey(String id);

    int insert(SysDataParseQc record);

    int insertSelective(SysDataParseQc record);

    List<SysDataParseQc> selectByExample(SysDataParseQcExample example);

    SysDataParseQc selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysDataParseQc record, @Param("example") SysDataParseQcExample example);

    int updateByExample(@Param("record") SysDataParseQc record, @Param("example") SysDataParseQcExample example);

    int updateByPrimaryKeySelective(SysDataParseQc record);

    int updateByPrimaryKey(SysDataParseQc record);
}