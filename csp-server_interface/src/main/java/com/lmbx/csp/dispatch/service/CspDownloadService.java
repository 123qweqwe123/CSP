package com.lmbx.csp.dispatch.service;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wanghongwei 2017-12-01
 */
public interface CspDownloadService {
    
    /**
     * 会议版本接口
     * @param request
     * @return
     */
	String checkVersion(HttpServletRequest request);
	/**
	 * 会议版本数下载
	 * @param request
	 * @param response
	 * @return
	 */

	ResponseEntity<Resource> download(HttpServletRequest request, HttpServletResponse response);
  
}
