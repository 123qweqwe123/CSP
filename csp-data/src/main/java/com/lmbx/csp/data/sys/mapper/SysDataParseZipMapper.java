package com.lmbx.csp.data.sys.mapper;

import com.lmbx.csp.data.sys.domain.SysDataParseZip;
import com.lmbx.csp.data.sys.domain.SysDataParseZipExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysDataParseZipMapper {
    long countByExample(SysDataParseZipExample example);

    int deleteByExample(SysDataParseZipExample example);

    int deleteByPrimaryKey(String id);

    int insert(SysDataParseZip record);

    int insertSelective(SysDataParseZip record);

    List<SysDataParseZip> selectByExample(SysDataParseZipExample example);

    SysDataParseZip selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysDataParseZip record, @Param("example") SysDataParseZipExample example);

    int updateByExample(@Param("record") SysDataParseZip record, @Param("example") SysDataParseZipExample example);

    int updateByPrimaryKeySelective(SysDataParseZip record);

    int updateByPrimaryKey(SysDataParseZip record);
}