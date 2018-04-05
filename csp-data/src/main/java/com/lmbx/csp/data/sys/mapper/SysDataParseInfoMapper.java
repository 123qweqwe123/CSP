package com.lmbx.csp.data.sys.mapper;

import com.lmbx.csp.data.sys.domain.SysDataParseInfo;
import com.lmbx.csp.data.sys.domain.SysDataParseInfoExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysDataParseInfoMapper {
    long countByExample(SysDataParseInfoExample example);

    int deleteByExample(SysDataParseInfoExample example);

    int deleteByPrimaryKey(String id);

    int insert(SysDataParseInfo record);

    int insertSelective(SysDataParseInfo record);

    List<SysDataParseInfo> selectByExample(SysDataParseInfoExample example);

    SysDataParseInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysDataParseInfo record, @Param("example") SysDataParseInfoExample example);

    int updateByExample(@Param("record") SysDataParseInfo record, @Param("example") SysDataParseInfoExample example);

    int updateByPrimaryKeySelective(SysDataParseInfo record);

    int updateByPrimaryKey(SysDataParseInfo record);
}