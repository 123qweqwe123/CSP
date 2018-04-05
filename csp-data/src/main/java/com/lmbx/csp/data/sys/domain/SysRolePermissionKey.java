package com.lmbx.csp.data.sys.domain;

public class SysRolePermissionKey {
    private String roleId;

    private String permissionsId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getPermissionsId() {
        return permissionsId;
    }

    public void setPermissionsId(String permissionsId) {
        this.permissionsId = permissionsId == null ? null : permissionsId.trim();
    }
}