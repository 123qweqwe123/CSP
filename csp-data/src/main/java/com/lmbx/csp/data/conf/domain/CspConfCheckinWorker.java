package com.lmbx.csp.data.conf.domain;

import java.util.Date;

public class CspConfCheckinWorker {
    private String id;

    private String workerId;

    private Short isPrimary;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;

    private String checkinId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getWorkerId() {
        return workerId;
    }

    public void setWorkerId(String workerId) {
        this.workerId = workerId == null ? null : workerId.trim();
    }

    public Short getIsPrimary() {
        return isPrimary;
    }

    public void setIsPrimary(Short isPrimary) {
        this.isPrimary = isPrimary;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public String getCheckinId() {
        return checkinId;
    }

    public void setCheckinId(String checkinId) {
        this.checkinId = checkinId == null ? null : checkinId.trim();
    }
}