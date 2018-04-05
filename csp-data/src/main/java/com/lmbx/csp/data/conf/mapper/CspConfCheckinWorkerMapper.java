package com.lmbx.csp.data.conf.mapper;

import com.lmbx.csp.data.conf.domain.CspConfCheckinWorker;
import com.lmbx.csp.data.conf.domain.CspConfCheckinWorkerExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CspConfCheckinWorkerMapper {
    long countByExample(CspConfCheckinWorkerExample example);

    int deleteByExample(CspConfCheckinWorkerExample example);

    int deleteByPrimaryKey(String id);

    int insert(CspConfCheckinWorker record);

    int insertSelective(CspConfCheckinWorker record);

    List<CspConfCheckinWorker> selectByExample(CspConfCheckinWorkerExample example);

    CspConfCheckinWorker selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CspConfCheckinWorker record, @Param("example") CspConfCheckinWorkerExample example);

    int updateByExample(@Param("record") CspConfCheckinWorker record, @Param("example") CspConfCheckinWorkerExample example);

    int updateByPrimaryKeySelective(CspConfCheckinWorker record);

    int updateByPrimaryKey(CspConfCheckinWorker record);
}