package com.lmbx.csp.dispatch.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lmbx.csp.dispatch.service.CspDownloadService;

/**
 * @author wanghongwei
 * @since 2017/12/01
 */
@RestController
public class CspDownloadController {

    @Autowired
    private CspDownloadService cspDownloadService;

    /**
     * 会议版本接口
     * 
     * @param request
     * @return
     */
    @GetMapping("/checkVersion")
    public String checkDcitionaryVersion(HttpServletRequest request, HttpServletResponse response) {
        return cspDownloadService.checkVersion(request);
    }

    /**
     * 会议版本数下载
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @GetMapping("/download")
    public ResponseEntity<Resource> downloadDictionaries(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return cspDownloadService.download(request, response);

    }

}
