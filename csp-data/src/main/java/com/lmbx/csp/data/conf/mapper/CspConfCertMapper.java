package com.lmbx.csp.data.conf.mapper;

import com.lmbx.csp.data.conf.domain.CspConfCert;
import com.lmbx.csp.data.conf.domain.CspConfCertExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author  wang
 */
@Mapper
public interface CspConfCertMapper {

    long countByExample(CspConfCertExample example);

    int deleteByExample(CspConfCertExample example);

    int deleteByPrimaryKey(String id);

    int insert(CspConfCert record);

    int insertSelective(CspConfCert record);

    List<CspConfCert> selectByExample(CspConfCertExample example);

    CspConfCert selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CspConfCert record, @Param("example") CspConfCertExample example);

    int updateByExample(@Param("record") CspConfCert record, @Param("example") CspConfCertExample example);

    int updateByPrimaryKeySelective(CspConfCert record);

    int updateByPrimaryKey(CspConfCert record);
}
