package com.lmbx.csp.dispatch.mapper;

import com.lmbx.csp.data.main.domain.CspMainPerson;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * @author wanghongwei
 */
@Mapper
public interface MyCspMainPersonMapper {

    /**
     * 签到工作人员详细信息
     * 
     * @param confNo 会议编号
     * @return
     */

    List<CspMainPerson> selectCheckInWorkersByConfNo(String confNo);

    /**
     * 签到讲师来宾详细信息
     * 
     * @param confNo 会议编号
     * @return
     */

    List<CspMainPerson> selectCheckInPersonByConfNo(String confNo);

    /**
     * 登记来宾详细信息
     * 
     * @param confNo 会议编号
     * @return
     */

    List<CspMainPerson> selectRegiVistorsByConfNo(String confNo);

    /**
     * 登记讲师详细信息 会议编号
     * 
     * @param confNo
     * @return
     */

    List<CspMainPerson> selectRegiLecturersByConfNo(String confNo);
    

    /**
     * 基本工作人员详细信息
     * 
     * @param confNo 会议编号
     * @return
     */

    List<CspMainPerson> selectBaseWorkersByConfNo(String confNo);

}
