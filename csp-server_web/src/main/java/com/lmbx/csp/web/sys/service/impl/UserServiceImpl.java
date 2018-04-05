package com.lmbx.csp.web.sys.service.impl;

import com.google.common.collect.Maps;
import com.lmbx.csp.core.constant.AppConsts;
import com.lmbx.csp.core.mvc.listener.MyHttpSessionListener;
import com.lmbx.csp.core.shiro.domain.ShiroUser;
import com.lmbx.csp.core.utils.PasswordUtils;
import com.lmbx.csp.core.utils.Securitys;
import com.lmbx.csp.core.utils.Utils;
import com.lmbx.csp.data.main.domain.CspMainPerson;
import com.lmbx.csp.data.main.domain.CspMainPersonExample;
import com.lmbx.csp.data.main.mapper.CspMainPersonMapper;
import com.lmbx.csp.data.sys.domain.*;
import com.lmbx.csp.data.sys.mapper.*;
import com.lmbx.csp.web.sys.mapper.MySysMapper;
import com.lmbx.csp.web.sys.service.UserService;
import com.lmbx.csp.web.sys.vo.MenuVO;
import com.lmbx.csp.web.sys.vo.UserVO;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Description:
 * 
 * <pre>
 * </pre>
 * 
 * @author: huangrupeng Create: 17/5/16 下午7:27
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private SysAccountMapper        sysAccountMapper;

    @Autowired
    private SysLoginLogMapper       sysLoginLogMapper;

    @Autowired
    private SysAccountRoleMapper    accountRoleMapper;

    @Autowired
    private SysRolePermissionMapper rolePermissionMapper;

    @Autowired
    private SysRoleMapper           roleMapper;

    @Autowired
    private SysPermissionMapper     permissionMapper;

    @Autowired
    private SysMenuMapper           menuMapper;

    @Autowired
    MySysMapper                     mySysMapper;

    @Autowired
    private CspMainPersonMapper     cspMainPersonMapper;

    @Override
    public Map<String, Object> getUser() {
        Map<String, Object> res = Maps.newHashMap();
        res.put("user", UserVO.buildUser());
        if (Securitys.isAuthenticatedOrRemembered() && Securitys.getMenus() == null) {
            setShiroUserExtraInfo(Securitys.getUser());
        }
        res.put("menu", Securitys.getMenus());
        return res;
    }

    /**
     * 登录操作，返回 shiroUser
     *
     * @return
     */
    @Override
    public ShiroUser login(String username, String password) {
        mySysMapper.selectRoleByFilter(null);
        SysAccount account = loginWithNameOrEmailOrTel(username);
        if (account != null) {
            String currPassword = PasswordUtils.getEncodePassWord(password, PasswordUtils.decodeHex(account.getSalt()));
            if (!account.getPassword().equals(currPassword)) {
                throw new AuthenticationException("密码错误");
            }
        } else {
            throw new UnknownAccountException("账号不存在");
        }
        ShiroUser shiroUser = new ShiroUser();
        shiroUser.setAccountId(account.getId());
        shiroUser.setAdmin(account.getIsAdmin() == 1);
        shiroUser.setName(account.getName());
        shiroUser.setLoginName(account.getLoginName());
        setShiroUserExtraInfo(shiroUser);
        return shiroUser;
    }

    /**
     * 通过多种方式登录
     * 
     * @param loginName
     * @return
     */
    private SysAccount loginWithNameOrEmailOrTel(String loginName) {
        // cspMainPersonMapper
        boolean isLoginTel = loginName.matches("^1[3|4|5|7|8][0-9]{9}$");
        System.out.println(isLoginTel);
        boolean isLoginEmail = loginName.matches("^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$");
        if (isLoginTel) {
            CspMainPersonExample personExample = new CspMainPersonExample();
            personExample.createCriteria().andTelEqualTo(loginName);
            List<CspMainPerson> persons = cspMainPersonMapper.selectByExample(personExample);
            if (persons.size() == 1) {
                SysAccountExample accountExample = new SysAccountExample();
                accountExample.createCriteria().andUserIdEqualTo(persons.get(0).getId());
                List<SysAccount> accounts = sysAccountMapper.selectByExample(accountExample);
                if (accounts.size() == 1) {
                    return accounts.get(0);
                }
            }
        } else if (isLoginEmail) {
            CspMainPersonExample personExample = new CspMainPersonExample();
            personExample.createCriteria().andEmailEqualTo(loginName);
            List<CspMainPerson> persons = cspMainPersonMapper.selectByExample(personExample);
            if (persons.size() == 1) {
                SysAccountExample accountExample = new SysAccountExample();
                accountExample.createCriteria().andUserIdEqualTo(persons.get(0).getId());
                List<SysAccount> accounts = sysAccountMapper.selectByExample(accountExample);
                if (accounts.size() == 1) {
                    return accounts.get(0);
                }
            }
        } else {
            SysAccountExample exp = new SysAccountExample();
            exp.createCriteria().andLoginNameEqualTo(loginName);
            List<SysAccount> accounts = sysAccountMapper.selectByExample(exp);
            if (accounts.size() == 1) {
                return accounts.get(0);
            }
        }
        return null;
    }

    @Override
    public void setShiroUserExtraInfo(ShiroUser shiroUser) {
        List<SysRole> roles;
        List<SysPermission> permissions;
        List<SysMenu> menus;
        SysMenuExample menuExample = new SysMenuExample();
        menuExample.setOrderByClause("MENU_LEVEL, SEQUENCE asc");
        if (shiroUser.isAdmin()) {
            roles = roleMapper.selectByExample(null);
            permissions = permissionMapper.selectByExample(null);
            menus = menuMapper.selectByExample(menuExample);
        } else {
            SysAccountRoleExample sysAccountRoleExample = new SysAccountRoleExample();
            sysAccountRoleExample.createCriteria().andAccountIdEqualTo(shiroUser.getAccountId());
            List<SysAccountRoleKey> sysAccountRoleKeys = accountRoleMapper.selectByExample(sysAccountRoleExample);
            SysRoleExample roleExample = new SysRoleExample();
            List<String> roleIds = sysAccountRoleKeys.stream().map(SysAccountRoleKey::getRoleId).collect(Collectors.toList());
            roleExample.createCriteria().andIdIn(roleIds);
            if (roleIds.size() == 0) {
                throw new AuthenticationException("该账号无登录系统权限!");
            }
            roles = roleMapper.selectByExample(roleExample);

            SysRolePermissionExample rolePermissionExample = new SysRolePermissionExample();
            rolePermissionExample.createCriteria().andRoleIdIn(roleIds);
            List<SysRolePermissionKey> rolePermissionKeys = rolePermissionMapper.selectByExample(rolePermissionExample);
            List<String> permissionIds = rolePermissionKeys.stream().map(SysRolePermissionKey::getPermissionsId).collect(Collectors.toList());
            SysPermissionExample sysPermissionExample = new SysPermissionExample();
            sysPermissionExample.createCriteria().andIdIn(permissionIds);
            permissions = permissionMapper.selectByExample(sysPermissionExample);

            menuExample.createCriteria().andPermissionIn(permissionIds);
            menus = menuMapper.selectByExample(menuExample);
        }
        List<String> rolesStr = roles.stream().map(SysRole::getName).collect(Collectors.toList());
        List<String> permissionsStr = permissions.stream().map(SysPermission::getCode).collect(Collectors.toList());

        shiroUser.setRoles(rolesStr);
        shiroUser.setPermissions(permissionsStr);
        shiroUser.setMenus(menus.stream().map(menu -> {
            MenuVO menuVO = new MenuVO();
            BeanUtils.copyProperties(menu, menuVO);
            return menuVO;
        }).collect(Collectors.toList()));
    }

    @Override
    public void createLoginLog(ServletRequest request) {
        if (request instanceof HttpServletRequest) {
            HttpServletRequest req = (HttpServletRequest) request;
            SysLoginLog loginLog = new SysLoginLog();
            loginLog.setId(Utils.generateUUID());
            loginLog.setIsOnline(AppConsts.TRUE);
            loginLog.setLoginTime(new Date());
            loginLog.setIsSuccess(AppConsts.TRUE);
            loginLog.setMachineIp(request.getRemoteAddr());
            loginLog.setMachineName(request.getRemoteHost());
            loginLog.setUserAgent(req.getHeader("User-Agent"));// 浏览器类型
            loginLog.setAccountId(Securitys.getAccountId());
            sysLoginLogMapper.insertSelective(loginLog);

            MyHttpSessionListener.bindSessionIdWithLoginLogId(req.getSession().getId(), loginLog.getId());
        }
    }

    @Override
    public void createLogoutLog(String loginLogId) {
        SysLoginLog logoutLog = new SysLoginLog();
        logoutLog.setIsOnline(AppConsts.FALSE);
        logoutLog.setId(loginLogId);
        logoutLog.setLogoutTime(new Date());
        sysLoginLogMapper.updateByPrimaryKeySelective(logoutLog);
    }

}
