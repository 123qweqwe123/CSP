package com.lmbx.csp.dispatch.mapper;

import com.lmbx.csp.data.conf.domain.CspConfCheckinWorker;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * @author wanghongwei
 */
@Mapper
public interface MyCspConfCheckinWorkerMapper {

    /**
     * 根据会议编码查询签到工作人员
     * 
     * @param confNo 会议编号
     * @return
     */

    List<CspConfCheckinWorker> selectCheckInWorkersByConfNo(String confNo);

}
