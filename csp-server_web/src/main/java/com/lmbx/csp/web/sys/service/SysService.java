package com.lmbx.csp.web.sys.service;

import java.util.List;
import java.util.Map;

import com.lmbx.csp.data.sys.domain.SysAccount;
import com.lmbx.csp.data.sys.domain.SysMenu;
import com.lmbx.csp.data.sys.domain.SysPermission;
import com.lmbx.csp.data.sys.domain.SysRole;
import com.lmbx.csp.web.sys.filter.AccountFilter;
import com.lmbx.csp.web.sys.filter.RoleFilter;
import com.lmbx.csp.web.sys.qo.TreeSortQO;
import com.lmbx.csp.web.sys.vo.SysAccountVO;

/**
 * Description:
 * 
 * <pre>
 * </pre>
 *
 * @author javahuang
 * @since 2017/11/24 上午10:11
 */
public interface SysService {

    /**
     * 账户管理
     */
    List<SysAccountVO> selectByFilter(AccountFilter filter);

    List<SysAccount> selectRoleUsers(String roleName);

    void createAccount(SysAccount account, String[] roleIds);

    void updateAccount(SysAccount account, String[] roleIds);

    void deleteAccount(String id);

    void selectAccountByLoginName(String q);

    /**
     * 菜单管理
     */
    Map<String, Object> queryMenus();

    void createMenu(SysMenu menu);

    void updateMenu(SysMenu menu);

    void deleteMenu(String id);

    void menuNameValidation(String q);

    List<?> queryPermissions();

    void deletePermission(String id);

    void updatePermission(SysPermission permission);

    void createPermission(SysPermission permission);

    void sortPermission(TreeSortQO sortQO);

    void permissionNameValidation(String q);

    void sortMenu(TreeSortQO sortQO);

    List<?> queryRoles(RoleFilter roleFilter);

    void createRole(SysRole role, String[] permissions);

    void updateRole(SysRole role, String[] permissions);

    void deleteRole(String id);

    void selectRoleByRoleName(String q);

    void createAccountPerson(SysAccountVO account, String[] roleIds);

    void updateAccountPerson(SysAccountVO account, String[] roleIds);
}
