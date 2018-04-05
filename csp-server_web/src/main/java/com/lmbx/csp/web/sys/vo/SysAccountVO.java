package com.lmbx.csp.web.sys.vo;

import com.lmbx.csp.data.sys.domain.SysAccount;

/**
 * Description:
 * 
 * <pre>
 * 用于账户管理页面展示
 * </pre>
 * 
 * @author javahuang
 * @since 2017/11/24 上午10:11
 */
public class SysAccountVO extends SysAccount {

    private Short  gender;

    private String telNumber;

    private String email;

    private String roleNames;

    private String roleIds;

    public Short getGender() {
        return gender;
    }

    public void setGender(Short gender) {
        this.gender = gender;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(String roleNames) {
        this.roleNames = roleNames;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

}
