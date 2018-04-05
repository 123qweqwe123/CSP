package com.lmbx.csp.dispatch.mapper;

import com.lmbx.csp.data.sys.domain.SysAccount;

import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * @author wanghongwei
 */
@Mapper
public interface MySysAccountMapper {

    /**
     * 根据会议编码查询签到工作人员的账户信息
     * 
     * @param confNo 会议编号
     * @return
     */

    List<SysAccount> selectCheckInAccountByConfNo(String confNo);

    /**
     * 根据会议编码查询基本工作人员的账户信息
     * 
     * @param confNo 会议编号
     * @return
     */

    List<SysAccount> selectBaseAccountByConfNo(String confNo);

}
