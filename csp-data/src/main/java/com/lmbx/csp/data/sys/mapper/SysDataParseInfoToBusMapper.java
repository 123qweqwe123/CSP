package com.lmbx.csp.data.sys.mapper;

import com.lmbx.csp.data.sys.domain.SysDataParseInfoToBus;
import com.lmbx.csp.data.sys.domain.SysDataParseInfoToBusExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysDataParseInfoToBusMapper {
    long countByExample(SysDataParseInfoToBusExample example);

    int deleteByExample(SysDataParseInfoToBusExample example);

    int insert(SysDataParseInfoToBus record);

    int insertSelective(SysDataParseInfoToBus record);

    List<SysDataParseInfoToBus> selectByExample(SysDataParseInfoToBusExample example);

    int updateByExampleSelective(@Param("record") SysDataParseInfoToBus record, @Param("example") SysDataParseInfoToBusExample example);

    int updateByExample(@Param("record") SysDataParseInfoToBus record, @Param("example") SysDataParseInfoToBusExample example);
}