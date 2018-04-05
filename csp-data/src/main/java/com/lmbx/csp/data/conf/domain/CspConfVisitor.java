package com.lmbx.csp.data.conf.domain;

import java.util.Date;

public class CspConfVisitor {
    private String id;

    private String visitorId;

    private String confId;

    private String confPlaceId;

    private String visitorConfNo;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(String visitorId) {
        this.visitorId = visitorId == null ? null : visitorId.trim();
    }

    public String getConfId() {
        return confId;
    }

    public void setConfId(String confId) {
        this.confId = confId == null ? null : confId.trim();
    }

    public String getConfPlaceId() {
        return confPlaceId;
    }

    public void setConfPlaceId(String confPlaceId) {
        this.confPlaceId = confPlaceId == null ? null : confPlaceId.trim();
    }

    public String getVisitorConfNo() {
        return visitorConfNo;
    }

    public void setVisitorConfNo(String visitorConfNo) {
        this.visitorConfNo = visitorConfNo == null ? null : visitorConfNo.trim();
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