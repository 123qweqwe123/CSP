package com.lmbx.csp.data.conf.mapper;

import com.lmbx.csp.data.conf.domain.CspConfCheckin;
import com.lmbx.csp.data.conf.domain.CspConfCheckinExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CspConfCheckinMapper {
    long countByExample(CspConfCheckinExample example);

    int deleteByExample(CspConfCheckinExample example);

    int deleteByPrimaryKey(String id);

    int insert(CspConfCheckin record);

    int insertSelective(CspConfCheckin record);

    List<CspConfCheckin> selectByExample(CspConfCheckinExample example);

    CspConfCheckin selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CspConfCheckin record, @Param("example") CspConfCheckinExample example);

    int updateByExample(@Param("record") CspConfCheckin record, @Param("example") CspConfCheckinExample example);

    int updateByPrimaryKeySelective(CspConfCheckin record);

    int updateByPrimaryKey(CspConfCheckin record);
}