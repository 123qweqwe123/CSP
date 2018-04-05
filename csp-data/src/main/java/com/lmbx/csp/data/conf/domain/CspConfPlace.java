package com.lmbx.csp.data.conf.domain;

import java.util.Date;

public class CspConfPlace {
    private String id;

    private String confId;

    private String confHost;

    private String confTopic;

    private String confDescription;

    private Date startTime;

    private Date endTime;

    private String confPlace;

    private String dataVersion;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private String placeNo;

    private String placeName;

    private String remark;

    private String placeAddress;

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

    public String getConfHost() {
        return confHost;
    }

    public void setConfHost(String confHost) {
        this.confHost = confHost == null ? null : confHost.trim();
    }

    public String getConfTopic() {
        return confTopic;
    }

    public void setConfTopic(String confTopic) {
        this.confTopic = confTopic == null ? null : confTopic.trim();
    }

    public String getConfDescription() {
        return confDescription;
    }

    public void setConfDescription(String confDescription) {
        this.confDescription = confDescription == null ? null : confDescription.trim();
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

    public String getConfPlace() {
        return confPlace;
    }

    public void setConfPlace(String confPlace) {
        this.confPlace = confPlace == null ? null : confPlace.trim();
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

    public String getPlaceNo() {
        return placeNo;
    }

    public void setPlaceNo(String placeNo) {
        this.placeNo = placeNo == null ? null : placeNo.trim();
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName == null ? null : placeName.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getPlaceAddress() {
        return placeAddress;
    }

    public void setPlaceAddress(String placeAddress) {
        this.placeAddress = placeAddress == null ? null : placeAddress.trim();
    }
}