package com.lmbx.csp.web.sys.mapper;

import com.lmbx.csp.data.sys.domain.*;
import com.lmbx.csp.web.sys.filter.*;
import com.lmbx.csp.web.sys.vo.SysAccountVO;
import com.lmbx.csp.web.sys.vo.SysRoleVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Description:
 * 
 * <pre>
 *     自定义 mapper 文件
 * </pre>
 *
 * @author javahuang
 * @since 2017/11/25 下午3:18
 */
@Mapper
public interface MySysMapper {

    List<SysAccountVO> selectAccountByFilter(AccountFilter filter);

    List<SysAccount> selectRoleUsers(@Param("roleName") String roleName);

    List<SysLoginLog> selectLoginLogByFilter(LoginLogFilter filter);

    List<SysMenu> selectMenuByFilter(MenuFilter filter);

    List<SysRoleVO> selectRoleByFilter(RoleFilter filter);

    List<SysParameter> selectParameterByFilter(ParameterFilter filter);

    List<?> selectExlinkByFilter(ExlinkFilter filter);

    List<SysParatype> selectParaTypeByFilter(ParameterFilter filter);
}
