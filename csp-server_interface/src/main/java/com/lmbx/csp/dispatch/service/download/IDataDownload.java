package com.lmbx.csp.dispatch.service.download;

import com.lmbx.csp.dispatch.service.download.dto.VersionDTO;

import java.util.Map;

/**
 * @author wanghongwei
 */
public interface IDataDownload {

    /**
     * 获取最新版本号
     * 
     * @param vervionDto
     * @return
     */
    String getVersion(VersionDTO vervionDto);

    /**
     * 获取所有数据
     * @param vervionDto
     * @return
     */
    byte[] getData(VersionDTO vervionDto);


    /**
     * 获取服务器的数据
     *
     * @param vervionDto
     * @return
     */

     Map<String, Object> fileByteFromFS(VersionDTO vervionDto);

}
