package com.lmbx.csp.web.conf.filter;

import com.lmbx.csp.core.mybatis.domain.PageFilter;

/**
 * Description:
 * <pre>
 * </pre>
 *
 * @author javahuang
 * @since 2017/12/1 下午3:00
 */
public class ConferencePersonFilter extends PageFilter{

    private String confId;
    private String type;
    // 查询条件
    private String str;

    public String getConfId() {
        return confId;
    }

    public void setConfId(String confId) {
        this.confId = confId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
