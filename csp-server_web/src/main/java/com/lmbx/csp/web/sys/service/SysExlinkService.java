package com.lmbx.csp.web.sys.service;

import com.lmbx.csp.data.sys.domain.SysExlink;
import com.lmbx.csp.web.sys.filter.ExlinkFilter;

import java.util.List;

/**
 * Description:
 * <pre>
 * </pre>
 *
 * @author javahuang
 * @since 2017/11/29 下午2:07
 */
public interface SysExlinkService {
    void createExlink(SysExlink exlink);

    List<?> queryExlinks(ExlinkFilter filter);

    void updateExlink(SysExlink exlink);

    void deleteExlink(String id);
}
