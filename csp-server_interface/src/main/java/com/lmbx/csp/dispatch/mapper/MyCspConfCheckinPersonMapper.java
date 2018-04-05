package com.lmbx.csp.dispatch.mapper;

import com.lmbx.csp.data.conf.domain.CspConfCheckinPerson;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * @author wanghongwei
 */
@Mapper
public interface MyCspConfCheckinPersonMapper {

    /**
     * 根据会议编码查询签到讲师来宾
     * 
     * @param confNo 会议编号
     * @return
     */

    List<CspConfCheckinPerson> selectCheckInPersonsByConfNo(String confNo);

}
