package com.lmbx.csp.handler.data.mapper;

import com.lmbx.csp.data.conf.domain.CspConfRegister;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RegisterBMapper {
    int insertBatch(List<CspConfRegister> records);
}