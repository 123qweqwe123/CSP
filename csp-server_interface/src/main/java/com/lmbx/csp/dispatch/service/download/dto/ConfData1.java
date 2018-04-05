package com.lmbx.csp.dispatch.service.download.dto;

import java.util.ArrayList;
import java.util.List;

import com.lmbx.csp.data.conf.domain.CspConference;
import com.lmbx.csp.data.sys.domain.SysDataIssueVersion;
/**
 * 会议列表数据
 * @author 韩晓伟
 *
 */
public class ConfData1 {
    /**
     * 会议数据带数据包版本号
     */
    private List<ConfWithVersion> confs = new ArrayList<ConfWithVersion>();
    
    public List<ConfWithVersion> getConfs() {
        return confs;
    }
    public void setConfs(List<ConfWithVersion> confs) {
        this.confs = confs;
    }
    
    public static class ConfWithVersion{
        private CspConference conf;
        private List<SysDataIssueVersion> versions = new ArrayList<SysDataIssueVersion>();
        
        public CspConference getConf() {
            return conf;
        }
        public void setConf(CspConference conf) {
            this.conf = conf;
        }
        public List<SysDataIssueVersion> getVersions() {
            return versions;
        }
        public void setVersions(List<SysDataIssueVersion> versions) {
            this.versions = versions;
        }
    }
   
}
