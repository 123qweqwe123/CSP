package com.lmbx.csp.web.conf.vo;

import java.util.List;

/**
 * Description:
 * <pre>
 * </pre>
 *
 * @author javahuang
 * @since 2017/12/27 上午11:20
 */
public class ConfCoursePlaceVO {
    private String value;
    private String label;
    private List<ConfCoursePlaceVO> children;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<ConfCoursePlaceVO> getChildren() {
        return children;
    }

    public void setChildren(List<ConfCoursePlaceVO> children) {
        this.children = children;
    }
}
