package com.lmbx.csp.web.common.qo;

/**
 * Description:
 * 
 * <pre>
 *     用于自动补全下拉框 mybatis 拼接查询 sql
 * </pre>
 * 
 * Author: huangrupeng Create: 17/6/8 下午2:48
 */
public class AutoCompleteQO {

    private String valueField    = "ID";       // 下拉框对应取值字段

    private String textField     = "NAME";     // 下拉框对应显示字段

    private String helpCodeField = "HELP_CODE";

    private String tableName;

    private String q;                          // 查询输入

    public AutoCompleteQO(String tableName){
        this.tableName = tableName;
    }

    public AutoCompleteQO(String valueField, String textField, String tableName){
        this.valueField = valueField;
        this.textField = textField;
        this.tableName = tableName;
    }

    public AutoCompleteQO(String valueField, String textField, String helpCodeField, String tableName){
        this.valueField = valueField;
        this.textField = textField;
        this.helpCodeField = helpCodeField;
        this.tableName = tableName;
    }

    public String getValueField() {
        return valueField;
    }

    public void setValueField(String valueField) {
        this.valueField = valueField;
    }

    public String getTextField() {
        return textField;
    }

    public void setTextField(String textField) {
        this.textField = textField;
    }

    public String getHelpCodeField() {
        return helpCodeField;
    }

    public void setHelpCodeField(String helpCodeField) {
        this.helpCodeField = helpCodeField;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getQ() {
        return q;
    }

    public AutoCompleteQO setQ(String q) {
        this.q = q;
        return this;
    }
}
