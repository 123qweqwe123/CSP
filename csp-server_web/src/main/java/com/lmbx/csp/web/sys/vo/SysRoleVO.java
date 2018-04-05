package com.lmbx.csp.web.sys.vo;

import com.lmbx.csp.data.sys.domain.SysRole;

/**
 * Description:
 * 
 * <pre>
 * 添加角色对应的权限列表
 * </pre>
 * 
 * @author javahuang
 * @since 2017/11/24 上午10:11
 */
public class SysRoleVO extends SysRole {

    private String permissionNames;

    private String permissionIds;

    public String getPermissionNames() {
        return permissionNames;
    }

    public void setPermissionNames(String permissionNames) {
        this.permissionNames = permissionNames;
    }

    public String getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(String permissionIds) {
        this.permissionIds = permissionIds;
    }
}
