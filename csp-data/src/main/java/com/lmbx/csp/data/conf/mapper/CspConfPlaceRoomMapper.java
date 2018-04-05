package com.lmbx.csp.data.conf.mapper;

import com.lmbx.csp.data.conf.domain.CspConfPlaceRoom;
import com.lmbx.csp.data.conf.domain.CspConfPlaceRoomExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CspConfPlaceRoomMapper {
    long countByExample(CspConfPlaceRoomExample example);

    int deleteByExample(CspConfPlaceRoomExample example);

    int deleteByPrimaryKey(String id);

    int insert(CspConfPlaceRoom record);

    int insertSelective(CspConfPlaceRoom record);

    List<CspConfPlaceRoom> selectByExample(CspConfPlaceRoomExample example);

    CspConfPlaceRoom selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CspConfPlaceRoom record, @Param("example") CspConfPlaceRoomExample example);

    int updateByExample(@Param("record") CspConfPlaceRoom record, @Param("example") CspConfPlaceRoomExample example);

    int updateByPrimaryKeySelective(CspConfPlaceRoom record);

    int updateByPrimaryKey(CspConfPlaceRoom record);
}