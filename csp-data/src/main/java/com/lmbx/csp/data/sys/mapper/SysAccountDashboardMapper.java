package com.lmbx.csp.data.sys.mapper;

import com.lmbx.csp.data.sys.domain.SysAccountDashboard;
import com.lmbx.csp.data.sys.domain.SysAccountDashboardExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysAccountDashboardMapper {
    long countByExample(SysAccountDashboardExample example);

    int deleteByExample(SysAccountDashboardExample example);

    int deleteByPrimaryKey(String id);

    int insert(SysAccountDashboard record);

    int insertSelective(SysAccountDashboard record);

    List<SysAccountDashboard> selectByExample(SysAccountDashboardExample example);

    SysAccountDashboard selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysAccountDashboard record, @Param("example") SysAccountDashboardExample example);

    int updateByExample(@Param("record") SysAccountDashboard record, @Param("example") SysAccountDashboardExample example);

    int updateByPrimaryKeySelective(SysAccountDashboard record);

    int updateByPrimaryKey(SysAccountDashboard record);
}