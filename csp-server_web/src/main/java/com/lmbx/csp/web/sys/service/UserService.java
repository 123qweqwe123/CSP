package com.lmbx.csp.web.sys.service;

import com.lmbx.csp.core.shiro.domain.ShiroUser;

import javax.servlet.ServletRequest;
import java.util.Map;

/**
 * Description:
 * 
 * <pre>
 * </pre>
 *
 * @author javahuang
 * @since 2017/11/24 上午10:11
 */
public interface UserService {

    Map<String, Object> getUser();

    /**
     * 登录校验
     *
     * @return
     */
    ShiroUser login(String username, String password);

    /**
     * 设置 shiroUser 的菜单、权限、角色信息
     *
     * @param shiroUser
     */
    void setShiroUserExtraInfo(ShiroUser shiroUser);

    /**
     * 添加退出日志
     *
     * @param loginLogId
     */
    void createLogoutLog(String loginLogId);

    /**
     * 添加登录日志
     *
     * @param request
     */
    void createLoginLog(ServletRequest request);
}
