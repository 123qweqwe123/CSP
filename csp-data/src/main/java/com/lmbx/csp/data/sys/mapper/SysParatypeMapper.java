package com.lmbx.csp.data.sys.mapper;

import com.lmbx.csp.data.sys.domain.SysParatype;
import com.lmbx.csp.data.sys.domain.SysParatypeExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysParatypeMapper {
    long countByExample(SysParatypeExample example);

    int deleteByExample(SysParatypeExample example);

    int deleteByPrimaryKey(String id);

    int insert(SysParatype record);

    int insertSelective(SysParatype record);

    List<SysParatype> selectByExample(SysParatypeExample example);

    SysParatype selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysParatype record, @Param("example") SysParatypeExample example);

    int updateByExample(@Param("record") SysParatype record, @Param("example") SysParatypeExample example);

    int updateByPrimaryKeySelective(SysParatype record);

    int updateByPrimaryKey(SysParatype record);
}