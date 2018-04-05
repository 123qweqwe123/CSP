package com.lmbx.csp.handler.data.mapper;

import com.lmbx.csp.data.conf.domain.CspConfCheckinRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface CheckinRecordBMapper {
    int insertBatch(List<CspConfCheckinRecord> records);
    List<Map<String, Object>> getMaxDate(@Param("vcNo") String vcNo,@Param("checkInId") String checkInId);
}
