package com.lmbx.csp.data.sys.domain;

public class SysDataParseInfoToBus {
    private String infoId;

    private String busId;

    public String getInfoId() {
        return infoId;
    }

    public void setInfoId(String infoId) {
        this.infoId = infoId == null ? null : infoId.trim();
    }

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId == null ? null : busId.trim();
    }
}