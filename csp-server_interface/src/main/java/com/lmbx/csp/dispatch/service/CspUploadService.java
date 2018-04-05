package com.lmbx.csp.dispatch.service;

import java.util.Map;

public interface CspUploadService {

    Map<String, Object> getFtpInfo(String confNo, String projectId);
}
