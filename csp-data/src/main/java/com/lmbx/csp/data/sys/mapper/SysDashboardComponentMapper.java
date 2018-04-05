package com.lmbx.csp.data.sys.mapper;

import com.lmbx.csp.data.sys.domain.SysDashboardComponent;
import com.lmbx.csp.data.sys.domain.SysDashboardComponentExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysDashboardComponentMapper {
    long countByExample(SysDashboardComponentExample example);

    int deleteByExample(SysDashboardComponentExample example);

    int deleteByPrimaryKey(String id);

    int insert(SysDashboardComponent record);

    int insertSelective(SysDashboardComponent record);

    List<SysDashboardComponent> selectByExample(SysDashboardComponentExample example);

    SysDashboardComponent selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysDashboardComponent record, @Param("example") SysDashboardComponentExample example);

    int updateByExample(@Param("record") SysDashboardComponent record, @Param("example") SysDashboardComponentExample example);

    int updateByPrimaryKeySelective(SysDashboardComponent record);

    int updateByPrimaryKey(SysDashboardComponent record);
}