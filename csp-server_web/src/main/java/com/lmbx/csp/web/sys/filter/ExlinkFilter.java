package com.lmbx.csp.web.sys.filter;

import com.lmbx.csp.core.mybatis.domain.PageFilter;

/**
 * Description:
 * 
 * <pre>
 * </pre>
 *
 * @author javahuang
 * @since 2017/11/29 下午2:08
 */
public class ExlinkFilter extends PageFilter {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
