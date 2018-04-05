package com.lmbx.csp.core.mybatis.domain;

/**
 * Description:
 * 
 * <pre>
 * 分页过滤器
 * </pre>
 * 
 * @author javahuang
 * @since 17/9/19 下午6:59
 */
public class PageFilter {

    // 当前页码
    private int page     = 1;
    // 分页数量
    private int pageSize = 10;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

}
