package com.lmbx.csp.data.conf.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class CspConfCheckin {
    private String id;

    private String confId;

    private Short checkinType;

    @DateTimeFormat(pattern = "yyy-MM-dd HH:mm")
    private Date startTime;

    @DateTimeFormat(pattern = "yyy-MM-dd HH:mm")
    private Date endTime;

    private Short status;

    private String remark;

    private String dataVersion;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private String courseId;

    private String checkinNo;

    private String checkinName;

    private String personType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getConfId() {
        return confId;
    }

    public void setConfId(String confId) {
        this.confId = confId == null ? null : confId.trim();
    }

    public Short getCheckinType() {
        return checkinType;
    }

    public void setCheckinType(Short checkinType) {
        this.checkinType = checkinType;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId == null ? null : courseId.trim();
    }

    public String getCheckinNo() {
        return checkinNo;
    }

    public void setCheckinNo(String checkinNo) {
        this.checkinNo = checkinNo == null ? null : checkinNo.trim();
    }

    public String getCheckinName() {
        return checkinName;
    }

    public void setCheckinName(String checkinName) {
        this.checkinName = checkinName == null ? null : checkinName.trim();
    }

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType == null ? null : personType.trim();
    }
}