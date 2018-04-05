package com.lmbx.csp.web.conf.filter;

/**
 * Description:
 * <pre>
 * </pre>
 *
 * @author javahuang
 * @since 2017/12/18 上午10:57
 */
public class ConferenceEventFilter {

    private Short type;
    private String confId;

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public String getConfId() {
        return confId;
    }

    public void setConfId(String confId) {
        this.confId = confId;
    }
}
