package com.lmbx.csp.dispatch.service.download.dto;

import java.util.List;

/**
 * @author wanghongwei
 */
public class BottomFile {

    private String          id;
    private Short           fileType;
    private String          templateFileId;
    private List<ParamData> dataMap;
    private String          fileData;

    public BottomFile(){
    }

    public BottomFile(String id, Short fileType, String templateFileId, List<ParamData> dataMap, String fileData){
        this.id = id;
        this.fileType = fileType;
        this.templateFileId = templateFileId;
        this.dataMap = dataMap;
        this.fileData = fileData;
    }

    @Override
    public String toString() {
        return "BottomFile{" + "id='" + id + '\'' + ", fileType=" + fileType + ", templateFileId='" + templateFileId
               + '\'' + ", dataMap=" + dataMap + ", fileData='" + fileData + '\'' + '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Short getFileType() {
        return fileType;
    }

    public void setFileType(Short fileType) {
        this.fileType = fileType;
    }

    public String getTemplateFileId() {
        return templateFileId;
    }

    public void setTemplateFileId(String templateFileId) {
        this.templateFileId = templateFileId;
    }

    public List<ParamData> getDataMap() {
        return dataMap;
    }

    public void setDataMap(List<ParamData> dataMap) {
        this.dataMap = dataMap;
    }

    public String getFileData() {
        return fileData;
    }

    public void setFileData(String fileData) {
        this.fileData = fileData;
    }
}
