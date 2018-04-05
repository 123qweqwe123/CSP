package com.lmbx.csp.data.conf.mapper;

import com.lmbx.csp.data.conf.domain.CspConfLecturer;
import com.lmbx.csp.data.conf.domain.CspConfLecturerExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CspConfLecturerMapper {
    long countByExample(CspConfLecturerExample example);

    int deleteByExample(CspConfLecturerExample example);

    int deleteByPrimaryKey(String id);

    int insert(CspConfLecturer record);

    int insertSelective(CspConfLecturer record);

    List<CspConfLecturer> selectByExample(CspConfLecturerExample example);

    CspConfLecturer selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CspConfLecturer record, @Param("example") CspConfLecturerExample example);

    int updateByExample(@Param("record") CspConfLecturer record, @Param("example") CspConfLecturerExample example);

    int updateByPrimaryKeySelective(CspConfLecturer record);

    int updateByPrimaryKey(CspConfLecturer record);
}