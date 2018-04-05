package com.lmbx.csp.dispatch.service.download.dto;

/**
 * 
 * @author ADMIN
 *
 */
public class VersionDTO {
	private String confNo;
	private String projectId;
	private String sysVersion;
	private String dictVersion;
	private String dictType;
    public VersionDTO(){
        super();
        // TODO Auto-generated constructor stub
    }
    public VersionDTO(String confNo, String projectId, String sysVersion, String dictVersion, String dictType){
        super();
        this.confNo = confNo;
        this.projectId = projectId;
        this.sysVersion = sysVersion;
        this.dictVersion = dictVersion;
        this.dictType = dictType;
    }
    @Override
    public String toString() {
        return "VersionDTO [confNo=" + confNo + ", projectId=" + projectId + ", sysVersion=" + sysVersion
               + ", dictVersion=" + dictVersion + ", dictType=" + dictType + "]";
    }
    
    public String getConfNo() {
        return confNo;
    }
    
    public void setConfNo(String confNo) {
        this.confNo = confNo;
    }
    
    public String getProjectId() {
        return projectId;
    }
    
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
    
    public String getSysVersion() {
        return sysVersion;
    }
    
    public void setSysVersion(String sysVersion) {
        this.sysVersion = sysVersion;
    }
    
    public String getDictVersion() {
        return dictVersion;
    }
    
    public void setDictVersion(String dictVersion) {
        this.dictVersion = dictVersion;
    }
    
    public String getDictType() {
        return dictType;
    }
    
    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

}
