package com.lmbx.csp.web.dataversion.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lmbx.csp.data.sys.domain.SysDataIssueVersion;
import com.lmbx.csp.data.sys.domain.SysDataIssueVersionLog;
import com.lmbx.csp.web.dataversion.filter.DataVersionFilter;
import com.lmbx.csp.web.dataversion.service.DataVersionService;
import com.lmbx.csp.web.dataversion.service.DictVersionLogService;

/**
 * TODO
 * 
 * @author qianyanan 2017年12月26日
 */
@RestController
@RequestMapping("/dataversion")
public class DataversionController {

    @Autowired
    private DataVersionService    dataVersionService;
    @Autowired
    private DictVersionLogService dictVersionLogService;

    @GetMapping
    public List<?> querySysDataIssueVersion(DataVersionFilter dataVersionFilter) {
        return dataVersionService.querySysDataIssueVersion(dataVersionFilter);
    }

    @PatchMapping
    public void updateSysDataIssueVersion(SysDataIssueVersion sysDataIssueVersion) {
        dataVersionService.updateSysDataIssueVersion(sysDataIssueVersion);
    }

    /**
     * 查询补丁包的历史版本
     * 
     * @param log
     * @return
     */
    @GetMapping("/currentVersionLogs")
    public List<?> queryCurrentSysDataIssueVersionLog() {
        return dictVersionLogService.querySysDataIssueVersionLog();
    }

    @GetMapping("/versionLogs")
    public List<?> querySysDataIssueVersionLog(SysDataIssueVersionLog sysDataIssueVersionLog) {
        return dictVersionLogService.querySysDataIssueVersionLog(sysDataIssueVersionLog);
    }

    @PostMapping("/versionLogs")
    public void createSysDataIssueVersionLog(SysDataIssueVersionLog sysDataIssueVersionLog) {
        dictVersionLogService.createSysDataIssueVersionLog(sysDataIssueVersionLog);
    }
}
