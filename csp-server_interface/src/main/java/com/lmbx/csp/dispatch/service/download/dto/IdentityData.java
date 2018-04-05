package com.lmbx.csp.dispatch.service.download.dto;

import java.util.List;

/**
 * 证件数据
 * 
 * @author wanghongwei
 */
public class IdentityData {

    private String           conferenceId;
    private String           conferenceNo;
    private String           conferenceName;
    private List<BottomFile> bottomFile;
    private List<LayOutFile> layoutFile;

    public IdentityData(){
    }

    public IdentityData(String conferenceId, String conferenceNo, String conferenceName, List<BottomFile> bottomFile,
                        List<LayOutFile> layoutFile){
        this.conferenceId = conferenceId;
        this.conferenceNo = conferenceNo;
        this.conferenceName = conferenceName;
        this.bottomFile = bottomFile;
        this.layoutFile = layoutFile;
    }

    @Override
    public String toString() {
        return "IdentityData{" + "conferenceId='" + conferenceId + '\'' + ", conferenceNo='" + conferenceNo + '\''
               + ", conferenceName='" + conferenceName + '\'' + ", bottomFile=" + bottomFile + ", layoutFile="
               + layoutFile + '}';
    }

    public String getConferenceId() {
        return conferenceId;
    }

    public void setConferenceId(String conferenceId) {
        this.conferenceId = conferenceId;
    }

    public String getConferenceNo() {
        return conferenceNo;
    }

    public void setConferenceNo(String conferenceNo) {
        this.conferenceNo = conferenceNo;
    }

    public String getConferenceName() {
        return conferenceName;
    }

    public void setConferenceName(String conferenceName) {
        this.conferenceName = conferenceName;
    }

    public List<BottomFile> getBottomFile() {
        return bottomFile;
    }

    public void setBottomFile(List<BottomFile> bottomFile) {
        this.bottomFile = bottomFile;
    }

    public List<LayOutFile> getLayoutFile() {
        return layoutFile;
    }

    public void setLayoutFile(List<LayOutFile> layoutFile) {
        this.layoutFile = layoutFile;
    }
}
