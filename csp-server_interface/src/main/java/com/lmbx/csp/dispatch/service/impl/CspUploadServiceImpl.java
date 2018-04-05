package com.lmbx.csp.dispatch.service.impl;

import com.lmbx.csp.dispatch.service.CspUploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class CspUploadServiceImpl implements CspUploadService {

    @Value("${ftpIp}")
    private String ftpIp;

    /**
     * 通过会议编号 获取上传
     * @param confNo
     * @param projectId
     * @return
     */
    @Override
    public Map<String, Object> getFtpInfo(String confNo, String projectId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ftpIp", ftpIp);
        map.put("ftpPort", "");
        map.put("ftpUser", "");
        map.put("ftpPassword", "");
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        map.put("ftpPath", "/" + projectId + "/" + confNo + "/" + sf.format(new Date()));
        return map;
    }
}
