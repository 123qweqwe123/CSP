package com.lmbx.csp.data.conf.mapper;

import com.lmbx.csp.data.conf.domain.CspConfPlace;
import com.lmbx.csp.data.conf.domain.CspConfPlaceExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CspConfPlaceMapper {
    long countByExample(CspConfPlaceExample example);

    int deleteByExample(CspConfPlaceExample example);

    int deleteByPrimaryKey(String id);

    int insert(CspConfPlace record);

    int insertSelective(CspConfPlace record);

    List<CspConfPlace> selectByExample(CspConfPlaceExample example);

    CspConfPlace selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CspConfPlace record, @Param("example") CspConfPlaceExample example);

    int updateByExample(@Param("record") CspConfPlace record, @Param("example") CspConfPlaceExample example);

    int updateByPrimaryKeySelective(CspConfPlace record);

    int updateByPrimaryKey(CspConfPlace record);
}