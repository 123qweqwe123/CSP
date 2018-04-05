package com.lmbx.csp.data.conf.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class CspConfCertAttribute {

    private String id;

    private String certId;

    private String attrName;

    private String querySql;

    private String queryParameter;

    private String createBy;

    @DateTimeFormat(pattern = "yyy-MM-dd HH:mm:ss")
    private Date   createTime;

    private String updateBy;
    @DateTimeFormat(pattern = "yyy-MM-dd HH:mm:ss")
    private Date   updateTime;

    private Short  fileType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCertId() {
        return certId;
    }

    public void setCertId(String certId) {
        this.certId = certId == null ? null : certId.trim();
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName == null ? null : attrName.trim();
    }

    public String getQuerySql() {
        return querySql;
    }

    public void setQuerySql(String querySql) {
        this.querySql = querySql == null ? null : querySql.trim();
    }

    public String getQueryParameter() {
        return queryParameter;
    }

    public void setQueryParameter(String queryParameter) {
        this.queryParameter = queryParameter == null ? null : queryParameter.trim();
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

    public Short getFileType() {
        return fileType;
    }

    public void setFileType(Short fileType) {
        this.fileType = fileType;
    }
}
