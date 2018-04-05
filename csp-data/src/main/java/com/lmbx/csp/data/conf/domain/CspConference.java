package com.lmbx.csp.data.conf.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class CspConference {
    private String id;

    private String confNo;

    private Short confType;

    private String confForm;

    private String confHost;

    private String confName;

    private String confOrganiser;

    private String confCoOrganiser;

    private String confPic;

    private String confTopic;

    private String confDescription;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    private String confPlace;

    private String remark;

    private String dataVersion;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private Short status;

    private Integer expectPerson;

    private String place;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getConfNo() {
        return confNo;
    }

    public void setConfNo(String confNo) {
        this.confNo = confNo == null ? null : confNo.trim();
    }

    public Short getConfType() {
        return confType;
    }

    public void setConfType(Short confType) {
        this.confType = confType;
    }

    public String getConfForm() {
        return confForm;
    }

    public void setConfForm(String confForm) {
        this.confForm = confForm == null ? null : confForm.trim();
    }

    public String getConfHost() {
        return confHost;
    }

    public void setConfHost(String confHost) {
        this.confHost = confHost == null ? null : confHost.trim();
    }

    public String getConfName() {
        return confName;
    }

    public void setConfName(String confName) {
        this.confName = confName == null ? null : confName.trim();
    }

    public String getConfOrganiser() {
        return confOrganiser;
    }

    public void setConfOrganiser(String confOrganiser) {
        this.confOrganiser = confOrganiser == null ? null : confOrganiser.trim();
    }

    public String getConfCoOrganiser() {
        return confCoOrganiser;
    }

    public void setConfCoOrganiser(String confCoOrganiser) {
        this.confCoOrganiser = confCoOrganiser == null ? null : confCoOrganiser.trim();
    }

    public String getConfPic() {
        return confPic;
    }

    public void setConfPic(String confPic) {
        this.confPic = confPic == null ? null : confPic.trim();
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

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Integer getExpectPerson() {
        return expectPerson;
    }

    public void setExpectPerson(Integer expectPerson) {
        this.expectPerson = expectPerson;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place == null ? null : place.trim();
    }
}