package com.lmbx.csp.web.sys.filter;

import com.lmbx.csp.core.mybatis.domain.PageFilter;

/**
 * Description:
 * 
 * <pre>
 * </pre>
 * 
 * @author javahuang
 * @since 2017/11/24 上午10:11
 */
public class RoleFilter extends PageFilter {

    private String searchId;

    private String searchName;

    public String getSearchId() {
        return searchId;
    }

    public void setSearchId(String searchId) {
        this.searchId = searchId;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }
}
