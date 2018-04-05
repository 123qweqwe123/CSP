package com.lmbx.csp.data.conf.mapper;

import com.lmbx.csp.data.conf.domain.CspConfWorker;
import com.lmbx.csp.data.conf.domain.CspConfWorkerExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CspConfWorkerMapper {
    long countByExample(CspConfWorkerExample example);

    int deleteByExample(CspConfWorkerExample example);

    int deleteByPrimaryKey(String id);

    int insert(CspConfWorker record);

    int insertSelective(CspConfWorker record);

    List<CspConfWorker> selectByExample(CspConfWorkerExample example);

    CspConfWorker selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CspConfWorker record, @Param("example") CspConfWorkerExample example);

    int updateByExample(@Param("record") CspConfWorker record, @Param("example") CspConfWorkerExample example);

    int updateByPrimaryKeySelective(CspConfWorker record);

    int updateByPrimaryKey(CspConfWorker record);
}