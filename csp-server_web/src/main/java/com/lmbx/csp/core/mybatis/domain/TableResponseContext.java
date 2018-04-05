package com.lmbx.csp.core.mybatis.domain;

/**
 * Description:
 * 
 * <pre>
 * </pre>
 * 
 * @author javahuang
 * @since 17/9/19 下午6:59
 */
public class TableResponseContext {

    protected static ThreadLocal<TableResponse> localResponse = new ThreadLocal<>();

    private TableResponseContext(){

    }

    public static <T> TableResponse<T> getTableResponse() {
        TableResponse<T> tableResponse = localResponse.get();
        if (tableResponse == null) {
            tableResponse = new TableResponse<>();
        }
        localResponse.remove();
        return tableResponse;
    }

}
