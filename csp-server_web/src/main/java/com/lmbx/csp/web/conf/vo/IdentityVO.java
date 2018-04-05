package com.lmbx.csp.web.conf.vo;

import javax.validation.constraints.NotNull;

/**
 * Description:
 * <pre>
 * </pre>
 *
 * @author javahuang
 * @since 2018/1/4 下午1:28
 */
public class IdentityVO {

    String id;
    private String confNo;
    @NotNull(message = "会议编号不能为空")
    private String confId;
    private Short identityType;
    private String layoutfileId;
    private String layoutAttrName;
    private String layoutQuerySql;
    private String layoutQueryParameter;
    private String bottomfileId;
    private String bottomAttrName;
    private String bottomQuerySql;
    private String bottomQueryParameter;

    public String getConfNo() {
        return confNo;
    }

    public void setConfNo(String confNo) {
        this.confNo = confNo;
    }

    public Short getIdentityType() {
        return identityType;
    }

    public void setIdentityType(Short identityType) {
        this.identityType = identityType;
    }

    public String getLayoutfileId() {
        return layoutfileId;
    }

    public void setLayoutfileId(String layoutfileId) {
        this.layoutfileId = layoutfileId;
    }

    public String getLayoutAttrName() {
        return layoutAttrName;
    }

    public void setLayoutAttrName(String layoutAttrName) {
        this.layoutAttrName = layoutAttrName;
    }

    public String getLayoutQuerySql() {
        return layoutQuerySql;
    }

    public void setLayoutQuerySql(String layoutQuerySql) {
        this.layoutQuerySql = layoutQuerySql;
    }

    public String getLayoutQueryParameter() {
        return layoutQueryParameter;
    }

    public void setLayoutQueryParameter(String layoutQueryParameter) {
        this.layoutQueryParameter = layoutQueryParameter;
    }

    public String getBottomfileId() {
        return bottomfileId;
    }

    public void setBottomfileId(String bottomfileId) {
        this.bottomfileId = bottomfileId;
    }

    public String getBottomAttrName() {
        return bottomAttrName;
    }

    public void setBottomAttrName(String bottomAttrName) {
        this.bottomAttrName = bottomAttrName;
    }

    public String getBottomQuerySql() {
        return bottomQuerySql;
    }

    public void setBottomQuerySql(String bottomQuerySql) {
        this.bottomQuerySql = bottomQuerySql;
    }

    public String getBottomQueryParameter() {
        return bottomQueryParameter;
    }

    public void setBottomQueryParameter(String bottomQueryParameter) {
        this.bottomQueryParameter = bottomQueryParameter;
    }

    public String getConfId() {
        return confId;
    }

    public void setConfId(String confId) {
        this.confId = confId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "IdentityVO{" +
                "id='" + id + '\'' +
                ", confNo='" + confNo + '\'' +
                ", confId='" + confId + '\'' +
                ", identityType=" + identityType +
                ", layoutfileId='" + layoutfileId + '\'' +
                ", layoutAttrName='" + layoutAttrName + '\'' +
                ", layoutQuerySql='" + layoutQuerySql + '\'' +
                ", layoutQueryParameter='" + layoutQueryParameter + '\'' +
                ", bottomfileId='" + bottomfileId + '\'' +
                ", bottomAttrName='" + bottomAttrName + '\'' +
                ", bottomQuerySql='" + bottomQuerySql + '\'' +
                ", bottomQueryParameter='" + bottomQueryParameter + '\'' +
                '}';
    }
}
