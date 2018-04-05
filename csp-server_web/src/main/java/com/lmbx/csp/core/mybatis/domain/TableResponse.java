package com.lmbx.csp.core.mybatis.domain;

import java.util.List;

/**
 * Description:
 * 
 * <pre>
 * 封装的页面表格数据
 * </pre>
 * 
 * @author javahuang
 * @since 17/9/19 下午6:59
 */
public class TableResponse<T> {

    private long    total;
    private List<T> list;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public TableResponse setList(List<T> list) {
        this.list = list;
        return this;
    }
}
