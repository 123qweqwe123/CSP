package com.lmbx.csp.data.sys.mapper;

import com.lmbx.csp.data.sys.domain.SysDataParseFile;
import com.lmbx.csp.data.sys.domain.SysDataParseFileExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysDataParseFileMapper {
    long countByExample(SysDataParseFileExample example);

    int deleteByExample(SysDataParseFileExample example);

    int deleteByPrimaryKey(String id);

    int insert(SysDataParseFile record);

    int insertSelective(SysDataParseFile record);

    List<SysDataParseFile> selectByExample(SysDataParseFileExample example);

    SysDataParseFile selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysDataParseFile record, @Param("example") SysDataParseFileExample example);

    int updateByExample(@Param("record") SysDataParseFile record, @Param("example") SysDataParseFileExample example);

    int updateByPrimaryKeySelective(SysDataParseFile record);

    int updateByPrimaryKey(SysDataParseFile record);
}