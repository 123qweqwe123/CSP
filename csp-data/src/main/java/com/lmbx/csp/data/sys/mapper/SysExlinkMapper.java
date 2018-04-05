package com.lmbx.csp.data.sys.mapper;

import com.lmbx.csp.data.sys.domain.SysExlink;
import com.lmbx.csp.data.sys.domain.SysExlinkExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysExlinkMapper {
    long countByExample(SysExlinkExample example);

    int deleteByExample(SysExlinkExample example);

    int deleteByPrimaryKey(String id);

    int insert(SysExlink record);

    int insertSelective(SysExlink record);

    List<SysExlink> selectByExample(SysExlinkExample example);

    SysExlink selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysExlink record, @Param("example") SysExlinkExample example);

    int updateByExample(@Param("record") SysExlink record, @Param("example") SysExlinkExample example);

    int updateByPrimaryKeySelective(SysExlink record);

    int updateByPrimaryKey(SysExlink record);
}