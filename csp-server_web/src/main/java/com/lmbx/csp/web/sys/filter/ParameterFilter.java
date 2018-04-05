package com.lmbx.csp.web.sys.filter;

import com.lmbx.csp.core.mybatis.domain.PageFilter;

/**
 * Description:
 * 
 * <pre>
 * </pre>
 *
 * @author javahuang
 * @since 2017/11/28 下午7:11
 */
public class ParameterFilter extends PageFilter{

    private String typeCode;

    private String typeName;

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
