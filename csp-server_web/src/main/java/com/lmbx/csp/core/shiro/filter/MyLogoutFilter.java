package com.lmbx.csp.core.shiro.filter;

import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Description:
 * 
 * <pre>
 *     重载默认 logout filter， 添加退出日志.
 * </pre>
 * 
 * @author javahuang
 * @since 17/9/19 下午6:59
 */
public class MyLogoutFilter extends LogoutFilter {

    private Logger log = LoggerFactory.getLogger(MyLogoutFilter.class);

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);
        // try/catch added for SHIRO-298:
        try {
            subject.logout();
        } catch (SessionException ise) {
            log.debug("Encountered session exception during logout.  This can generally safely be ignored.", ise);
        }
        return false;
    }
}
