package com.lmbx.csp.data.conf.mapper;

import com.lmbx.csp.data.conf.domain.CspConfFile;
import com.lmbx.csp.data.conf.domain.CspConfFileExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CspConfFileMapper {
    long countByExample(CspConfFileExample example);

    int deleteByExample(CspConfFileExample example);

    int deleteByPrimaryKey(String id);

    int insert(CspConfFile record);

    int insertSelective(CspConfFile record);

    List<CspConfFile> selectByExample(CspConfFileExample example);

    CspConfFile selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CspConfFile record, @Param("example") CspConfFileExample example);

    int updateByExample(@Param("record") CspConfFile record, @Param("example") CspConfFileExample example);

    int updateByPrimaryKeySelective(CspConfFile record);

    int updateByPrimaryKey(CspConfFile record);
}