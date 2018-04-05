package com.lmbx.csp.data.conf.mapper;

import com.lmbx.csp.data.conf.domain.CspConfCertAttribute;
import com.lmbx.csp.data.conf.domain.CspConfCertAttributeExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * @author  wang
 */
@Mapper
public interface CspConfCertAttributeMapper {
    long countByExample(CspConfCertAttributeExample example);

    int deleteByExample(CspConfCertAttributeExample example);

    int deleteByPrimaryKey(String id);

    int insert(CspConfCertAttribute record);

    int insertSelective(CspConfCertAttribute record);

    List<CspConfCertAttribute> selectByExample(CspConfCertAttributeExample example);

    CspConfCertAttribute selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CspConfCertAttribute record, @Param("example") CspConfCertAttributeExample example);

    int updateByExample(@Param("record") CspConfCertAttribute record, @Param("example") CspConfCertAttributeExample example);

    int updateByPrimaryKeySelective(CspConfCertAttribute record);

    int updateByPrimaryKey(CspConfCertAttribute record);
}