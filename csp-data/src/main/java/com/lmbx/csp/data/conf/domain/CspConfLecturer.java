package com.lmbx.csp.data.conf.domain;

import java.util.Date;

public class CspConfLecturer {
    private String id;

    private String confLecturerId;

    private String confId;

    private String confPlaceId;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private String lecturerConfNo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getConfLecturerId() {
        return confLecturerId;
    }

    public void setConfLecturerId(String confLecturerId) {
        this.confLecturerId = confLecturerId == null ? null : confLecturerId.trim();
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

    public String getLecturerConfNo() {
        return lecturerConfNo;
    }

    public void setLecturerConfNo(String lecturerConfNo) {
        this.lecturerConfNo = lecturerConfNo == null ? null : lecturerConfNo.trim();
    }
}