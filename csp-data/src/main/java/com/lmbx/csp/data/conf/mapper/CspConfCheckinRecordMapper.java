package com.lmbx.csp.data.conf.mapper;

import com.lmbx.csp.data.conf.domain.CspConfCheckinRecord;
import com.lmbx.csp.data.conf.domain.CspConfCheckinRecordExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CspConfCheckinRecordMapper {
    long countByExample(CspConfCheckinRecordExample example);

    int deleteByExample(CspConfCheckinRecordExample example);

    int deleteByPrimaryKey(String id);

    int insert(CspConfCheckinRecord record);

    int insertSelective(CspConfCheckinRecord record);

    List<CspConfCheckinRecord> selectByExample(CspConfCheckinRecordExample example);

    CspConfCheckinRecord selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CspConfCheckinRecord record, @Param("example") CspConfCheckinRecordExample example);

    int updateByExample(@Param("record") CspConfCheckinRecord record, @Param("example") CspConfCheckinRecordExample example);

    int updateByPrimaryKeySelective(CspConfCheckinRecord record);

    int updateByPrimaryKey(CspConfCheckinRecord record);
}