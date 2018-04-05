package com.lmbx.csp.core.shiro.domain;

import com.lmbx.csp.web.sys.vo.MenuVO;

import java.io.Serializable;
import java.util.List;

/**
 * Description:
 * 
 * <pre>
 *     定义了 rememberCookie， ShiroUser 将被序列化到 cookie，cookie 大小有限制，序列化的结果不能太大
 * </pre>
 *
 * @author javahuang
 * @since 17/9/19 下午6:59
 */
public class ShiroUser implements Serializable {

    private String                 accountId;
    private String                 loginName;
    private String                 name;
    /**
     * 是否系统级管理员
     */
    private boolean                isAdmin;
    private transient List<String> roleIdList;
    private transient List<MenuVO> menus;
    private transient List<String> roles;
    private transient List<String> permissions;

    public ShiroUser(){

    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public List<String> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<String> roleIdList) {
        this.roleIdList = roleIdList;
    }

    public List<MenuVO> getMenus() {
        return menus;
    }

    public void setMenus(List<MenuVO> menus) {
        this.menus = menus;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }
}
