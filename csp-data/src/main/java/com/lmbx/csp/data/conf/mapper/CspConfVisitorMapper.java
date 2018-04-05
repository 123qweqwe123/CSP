package com.lmbx.csp.data.conf.mapper;

import com.lmbx.csp.data.conf.domain.CspConfVisitor;
import com.lmbx.csp.data.conf.domain.CspConfVisitorExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CspConfVisitorMapper {
    long countByExample(CspConfVisitorExample example);

    int deleteByExample(CspConfVisitorExample example);

    int deleteByPrimaryKey(String id);

    int insert(CspConfVisitor record);

    int insertSelective(CspConfVisitor record);

    List<CspConfVisitor> selectByExample(CspConfVisitorExample example);

    CspConfVisitor selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CspConfVisitor record, @Param("example") CspConfVisitorExample example);

    int updateByExample(@Param("record") CspConfVisitor record, @Param("example") CspConfVisitorExample example);

    int updateByPrimaryKeySelective(CspConfVisitor record);

    int updateByPrimaryKey(CspConfVisitor record);
}