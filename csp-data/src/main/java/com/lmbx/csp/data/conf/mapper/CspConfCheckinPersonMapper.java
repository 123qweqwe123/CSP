package com.lmbx.csp.data.conf.mapper;

import com.lmbx.csp.data.conf.domain.CspConfCheckinPerson;
import com.lmbx.csp.data.conf.domain.CspConfCheckinPersonExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CspConfCheckinPersonMapper {
    long countByExample(CspConfCheckinPersonExample example);

    int deleteByExample(CspConfCheckinPersonExample example);

    int deleteByPrimaryKey(String id);

    int insert(CspConfCheckinPerson record);

    int insertSelective(CspConfCheckinPerson record);

    List<CspConfCheckinPerson> selectByExample(CspConfCheckinPersonExample example);

    CspConfCheckinPerson selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CspConfCheckinPerson record, @Param("example") CspConfCheckinPersonExample example);

    int updateByExample(@Param("record") CspConfCheckinPerson record, @Param("example") CspConfCheckinPersonExample example);

    int updateByPrimaryKeySelective(CspConfCheckinPerson record);

    int updateByPrimaryKey(CspConfCheckinPerson record);
}