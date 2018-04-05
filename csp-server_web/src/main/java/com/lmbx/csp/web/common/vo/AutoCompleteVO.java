package com.lmbx.csp.web.common.vo;

/**
 * Description:
 * 
 * <pre>
 * 前端输入框自动补全
 * </pre>
 * 
 * @author javahuang
 * @since 2017/11/24 上午10:11
 */
public class AutoCompleteVO {

    private String value;
    private String text;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
