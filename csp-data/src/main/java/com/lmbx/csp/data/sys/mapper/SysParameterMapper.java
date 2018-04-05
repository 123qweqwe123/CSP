package com.lmbx.csp.data.sys.mapper;

import com.lmbx.csp.data.sys.domain.SysParameter;
import com.lmbx.csp.data.sys.domain.SysParameterExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysParameterMapper {
    long countByExample(SysParameterExample example);

    int deleteByExample(SysParameterExample example);

    int deleteByPrimaryKey(String id);

    int insert(SysParameter record);

    int insertSelective(SysParameter record);

    List<SysParameter> selectByExample(SysParameterExample example);

    SysParameter selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysParameter record, @Param("example") SysParameterExample example);

    int updateByExample(@Param("record") SysParameter record, @Param("example") SysParameterExample example);

    int updateByPrimaryKeySelective(SysParameter record);

    int updateByPrimaryKey(SysParameter record);
}