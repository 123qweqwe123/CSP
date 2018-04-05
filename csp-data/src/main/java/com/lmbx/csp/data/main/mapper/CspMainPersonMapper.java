package com.lmbx.csp.data.main.mapper;

import com.lmbx.csp.data.main.domain.CspMainPerson;
import com.lmbx.csp.data.main.domain.CspMainPersonExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CspMainPersonMapper {
    long countByExample(CspMainPersonExample example);

    int deleteByExample(CspMainPersonExample example);

    int deleteByPrimaryKey(String id);

    int insert(CspMainPerson record);

    int insertSelective(CspMainPerson record);

    List<CspMainPerson> selectByExample(CspMainPersonExample example);

    CspMainPerson selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CspMainPerson record, @Param("example") CspMainPersonExample example);

    int updateByExample(@Param("record") CspMainPerson record, @Param("example") CspMainPersonExample example);

    int updateByPrimaryKeySelective(CspMainPerson record);

    int updateByPrimaryKey(CspMainPerson record);
}