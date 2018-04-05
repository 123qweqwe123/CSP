package com.lmbx.csp.data.conf.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class CspConfCert {

    private String id;

    private String layoutfileId;

    private String bottomfileId;

    private String confId;

    private Short  identityType;

    private String createBy;

    @DateTimeFormat(pattern = "yyy-MM-dd HH:mm:ss")
    private Date   createTime;

    private String updateBy;

    @DateTimeFormat(pattern = "yyy-MM-dd HH:mm:ss")
    private Date   updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getLayoutfileId() {
        return layoutfileId;
    }

    public void setLayoutfileId(String layoutfileId) {
        this.layoutfileId = layoutfileId == null ? null : layoutfileId.trim();
    }

    public String getBottomfileId() {
        return bottomfileId;
    }

    public void setBottomfileId(String bottomfileId) {
        this.bottomfileId = bottomfileId == null ? null : bottomfileId.trim();
    }

    public String getConfId() {
        return confId;
    }

    public void setConfId(String confId) {
        this.confId = confId == null ? null : confId.trim();
    }

    public Short getIdentityType() {
        return identityType;
    }

    public void setIdentityType(Short identityType) {
        this.identityType = identityType;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
