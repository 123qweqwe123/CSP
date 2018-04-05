package com.lmbx.csp.web.sys.controller;

import com.lmbx.csp.web.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Description:
 * 
 * <pre>
 *     用户操作，包含加载用户数据/登录/退出
 * </pre>
 * 
 * Author: huangrupeng Create: 17/5/15 下午8:57
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 加载用户数据，如果返回空，则路由到登录界面
     *
     * @return
     */
    @RequestMapping
    public Map<String, Object> user(HttpServletRequest request) {
        return userService.getUser();
    }

    @PostMapping("/login")
    public void login(String username, String password) {
        userService.login(username, password);
    }

}
