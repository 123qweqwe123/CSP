package com.lmbx.csp.data.conf.mapper;

import com.lmbx.csp.data.conf.domain.CspConfCourse;
import com.lmbx.csp.data.conf.domain.CspConfCourseExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CspConfCourseMapper {
    long countByExample(CspConfCourseExample example);

    int deleteByExample(CspConfCourseExample example);

    int deleteByPrimaryKey(String id);

    int insert(CspConfCourse record);

    int insertSelective(CspConfCourse record);

    List<CspConfCourse> selectByExample(CspConfCourseExample example);

    CspConfCourse selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CspConfCourse record, @Param("example") CspConfCourseExample example);

    int updateByExample(@Param("record") CspConfCourse record, @Param("example") CspConfCourseExample example);

    int updateByPrimaryKeySelective(CspConfCourse record);

    int updateByPrimaryKey(CspConfCourse record);
}