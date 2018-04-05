package com.lmbx.csp.web.conf.filter;

import com.lmbx.csp.core.mybatis.domain.PageFilter;

/**
 * Description:
 * <pre>
 * </pre>
 *
 * @author javahuang
 * @since 2017/12/6 上午11:08
 */
public class ConferenceCheckinFilter extends PageFilter{
    private String confId;

    public String getConfId() {
        return confId;
    }

    public void setConfId(String confId) {
        this.confId = confId;
    }
}
