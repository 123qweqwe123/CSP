package com.lmbx.csp.data.conf.domain;

import java.util.Date;

public class CspConfWorker {
    private String id;

    private String workerId;

    private Short workType;

    private String workDesc;

    private String dataVersion;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private String confId;

    private String workerConfNo;

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

    public Short getWorkType() {
        return workType;
    }

    public void setWorkType(Short workType) {
        this.workType = workType;
    }

    public String getWorkDesc() {
        return workDesc;
    }

    public void setWorkDesc(String workDesc) {
        this.workDesc = workDesc == null ? null : workDesc.trim();
    }

    public String getDataVersion() {
        return dataVersion;
    }

    public void setDataVersion(String dataVersion) {
        this.dataVersion = dataVersion == null ? null : dataVersion.trim();
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

    public String getConfId() {
        return confId;
    }

    public void setConfId(String confId) {
        this.confId = confId == null ? null : confId.trim();
    }

    public String getWorkerConfNo() {
        return workerConfNo;
    }

    public void setWorkerConfNo(String workerConfNo) {
        this.workerConfNo = workerConfNo == null ? null : workerConfNo.trim();
    }
}