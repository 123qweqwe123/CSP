package com.lmbx.csp.data.sys.domain;

import java.util.Date;

public class SysAccountDashboard {
    private String id;

    private String accountId;

    private Date createTime;

    private String createBy;

    private Short x;

    private Short y;

    private Short w;

    private Short h;

    private String i;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
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

    public Short getX() {
        return x;
    }

    public void setX(Short x) {
        this.x = x;
    }

    public Short getY() {
        return y;
    }

    public void setY(Short y) {
        this.y = y;
    }

    public Short getW() {
        return w;
    }

    public void setW(Short w) {
        this.w = w;
    }

    public Short getH() {
        return h;
    }

    public void setH(Short h) {
        this.h = h;
    }

    public String getI() {
        return i;
    }

    public void setI(String i) {
        this.i = i == null ? null : i.trim();
    }
}