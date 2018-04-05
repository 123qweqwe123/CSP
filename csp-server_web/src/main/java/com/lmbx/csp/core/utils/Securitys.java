package com.lmbx.csp.core.utils;

import com.lmbx.csp.core.shiro.domain.ShiroUser;
import com.lmbx.csp.web.sys.vo.MenuVO;
import org.apache.shiro.SecurityUtils;

import java.util.List;

/**
 * Description:
 * 
 * <pre>
 *     shiro 工具类，操作当前的 Subject
 * </pre>
 * 
 * @author javahuang
 * @since 17/9/19 下午6:59
 */
public class Securitys extends SecurityUtils {

    public static ShiroUser getUser() {
        if (isAuthenticatedOrRemembered()) {
            return (ShiroUser) getSubject().getPrincipal();
        }
        return new ShiroUser();
    }

    public static boolean isAuthenticatedOrRemembered() {
        return getSubject().isAuthenticated() || getSubject().isRemembered();
    }

    public static List<MenuVO> getMenus() {
        return getUser().getMenus();
    }

    public static String getName() {
        return getUser().getName();
    }

    public static String getAccountId() {
        return getUser().getAccountId();
    }

    public static boolean isAdmin() {
        return getUser().isAdmin();
    }

}
