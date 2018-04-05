package com.lmbx.csp.data.conf.mapper;

import com.lmbx.csp.data.conf.domain.CspConference;
import com.lmbx.csp.data.conf.domain.CspConferenceExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CspConferenceMapper {
    long countByExample(CspConferenceExample example);

    int deleteByExample(CspConferenceExample example);

    int deleteByPrimaryKey(String id);

    int insert(CspConference record);

    int insertSelective(CspConference record);

    List<CspConference> selectByExample(CspConferenceExample example);

    CspConference selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CspConference record, @Param("example") CspConferenceExample example);

    int updateByExample(@Param("record") CspConference record, @Param("example") CspConferenceExample example);

    int updateByPrimaryKeySelective(CspConference record);

    int updateByPrimaryKey(CspConference record);
}