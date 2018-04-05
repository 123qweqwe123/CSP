package com.lmbx.csp.data.conf.mapper;

import com.lmbx.csp.data.conf.domain.CspConfRegister;
import com.lmbx.csp.data.conf.domain.CspConfRegisterExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CspConfRegisterMapper {
    long countByExample(CspConfRegisterExample example);

    int deleteByExample(CspConfRegisterExample example);

    int deleteByPrimaryKey(String id);

    int insert(CspConfRegister record);

    int insertSelective(CspConfRegister record);

    List<CspConfRegister> selectByExample(CspConfRegisterExample example);

    CspConfRegister selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CspConfRegister record, @Param("example") CspConfRegisterExample example);

    int updateByExample(@Param("record") CspConfRegister record, @Param("example") CspConfRegisterExample example);

    int updateByPrimaryKeySelective(CspConfRegister record);

    int updateByPrimaryKey(CspConfRegister record);
}