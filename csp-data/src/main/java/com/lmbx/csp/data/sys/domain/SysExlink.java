package com.lmbx.csp.data.sys.domain;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.lmbx.csp.data.core.CustomVirtualBeanPropertyWriter;

import java.util.Date;

@JsonAppend(props = {
        @JsonAppend.Prop(name = "createName", value = CustomVirtualBeanPropertyWriter.class),
        @JsonAppend.Prop(name = "updateName", value = CustomVirtualBeanPropertyWriter.class)
})
public class SysExlink {
    private String id;

    private String url;

    private String width;

    private Short linkType;

    private String height;

    private String display;

    private String position;

    private Short allowfullscreen;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;

    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width == null ? null : width.trim();
    }

    public Short getLinkType() {
        return linkType;
    }

    public void setLinkType(Short linkType) {
        this.linkType = linkType;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height == null ? null : height.trim();
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display == null ? null : display.trim();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public Short getAllowfullscreen() {
        return allowfullscreen;
    }

    public void setAllowfullscreen(Short allowfullscreen) {
        this.allowfullscreen = allowfullscreen;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}