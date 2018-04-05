package com.lmbx.csp.dispatch.service.impl;

import com.google.common.collect.Maps;
import com.lmbx.csp.constant.DictTypeConsts;
import com.lmbx.csp.data.sys.domain.SysDataIssueLog;
import com.lmbx.csp.data.sys.mapper.SysDataIssueLogMapper;
import com.lmbx.csp.dispatch.service.CspDownloadService;
import com.lmbx.csp.dispatch.service.download.IDataDownload;
import com.lmbx.csp.dispatch.service.download.dto.VersionDTO;
import com.lmbx.csp.dispatch.service.download.impl.*;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * @author whw 2017-11-28
 */
@Service
public class CspDownloadServiceImpl implements CspDownloadService {

    private static final Logger          log = LoggerFactory.getLogger(CspDownloadServiceImpl.class);

    @Autowired
    private Dict0DataDownLoadImpl        dict0;

    @Autowired
    private Dict1DataDownLoadImpl        dict1;

    @Autowired
    private Dict2DataDownLoadImpl        dict2;

    @Autowired
    private Dict3DataDownLoadImpl        dict3;

    @Autowired
    private Dict4DataDownLoadImpl        dict4;

    @Autowired
    private Dict5ataDownLoadImpl         dict5;

    @Autowired
    private Dict98PcDataDownLoadImpl     dict98;

    @Autowired
    private Dict99MobileDataDownLoadImpl dict99;

    @Autowired
    private SysDataIssueLogMapper        sysDataIssueLogMapper;

    @Override
    public String checkVersion(HttpServletRequest request) {
        IDataDownload dataDownLoad = null;
        String dictType = request.getParameter("dictType");
        try {
            if (DictTypeConsts.PARA_TYPE.equals(dictType)) {
                dataDownLoad = dict0;
            } else if (DictTypeConsts.CONFERENCELIST_TYPE.equals(dictType)) {
                dataDownLoad = dict1;
            } else if (DictTypeConsts.CONFERENCEBASEINFO_TYPE.equals(dictType)) {
                dataDownLoad = dict2;
            } else if (DictTypeConsts.CONFERENCEREG_TYPE.equals(dictType)) {
                dataDownLoad = dict3;
            } else if (DictTypeConsts.CONFERENCECHECK_TYPE.equals(dictType)) {
                dataDownLoad = dict4;
            } else if (DictTypeConsts.CONFERENCEIDENTITY_TYPE.equals(dictType)) {
                dataDownLoad = dict5;
            } else if (DictTypeConsts.CONFERENCEPC_TYPE.equals(dictType)) {
                dataDownLoad = dict98;
            } else if (DictTypeConsts.CONFERENCEMOBILE_TYPE.equals(dictType)) {
                dataDownLoad = dict99;
            }
            VersionDTO vervionDto = new VersionDTO();
            vervionDto.setConfNo(request.getParameter("confNo"));
            vervionDto.setProjectId(request.getParameter("projectId"));
            vervionDto.setDictType(dictType);
            return dataDownLoad.getVersion(vervionDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<Resource> download(HttpServletRequest request, HttpServletResponse response) {
        IDataDownload dataDownLoad = null;
        String dictType = request.getParameter("dictType");
        String confNo = request.getParameter("confNo");
        if (DictTypeConsts.PARA_TYPE.equals(dictType)) {
            dataDownLoad = dict0;
        } else if (DictTypeConsts.CONFERENCELIST_TYPE.equals(dictType)) {
            dataDownLoad = dict1;
        } else if (DictTypeConsts.CONFERENCEBASEINFO_TYPE.equals(dictType)) {
            dataDownLoad = dict2;
        } else if (DictTypeConsts.CONFERENCEREG_TYPE.equals(dictType)) {
            dataDownLoad = dict3;
        } else if (DictTypeConsts.CONFERENCECHECK_TYPE.equals(dictType)) {
            dataDownLoad = dict4;
        } else if (DictTypeConsts.CONFERENCEIDENTITY_TYPE.equals(dictType)) {
            dataDownLoad = dict5;
        }
        VersionDTO vervionDto = new VersionDTO();
        vervionDto.setDictType(dictType);
        vervionDto.setConfNo(confNo);
        ByteArrayInputStream bis = null;
        BufferedOutputStream bos = null;
        long fileLength = 0L;
        try {
            if (DictTypeConsts.CONFERENCEMOBILE_TYPE.equals(dictType)
                || DictTypeConsts.CONFERENCEPC_TYPE.equals(dictType)) {
                ResponseEntity<Resource> mobileData = getMobileData(vervionDto, response);
                return mobileData;
            } else {
                response.setContentType("application/x-msdownload;");
                response.setHeader("Content-disposition", "attachment; filename=conference.zip");
                byte[] bytes = dataDownLoad.getData(vervionDto);
                if (bytes != null) {
                    fileLength = (long) bytes.length;
                    response.setHeader("Content-Length", String.valueOf(fileLength));
                    bis = new ByteArrayInputStream(bytes);
                    bos = new BufferedOutputStream(response.getOutputStream());
                    byte[] buff = new byte[2048];
                    int bytesRead;
                    while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                        bos.write(buff, 0, bytesRead);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        this.recordVersionLog(request);
        return null;
    }

    private ResponseEntity<Resource> getMobileData(VersionDTO vervionDto, HttpServletResponse response) {

        Map<String, Object> fileWithMetaData = dict98.fileByteFromFS(vervionDto);
        byte[] fileByteArr = (byte[]) fileWithMetaData.get("body");
        ByteArrayResource resource = new ByteArrayResource(fileByteArr);
        NameValuePair[] metaData = (NameValuePair[]) fileWithMetaData.get("meta");
        String fileName = "";

        try {
            if (metaData != null) {
                for (NameValuePair pair : metaData) {
                    if ("name".equals(pair.getName())) {
                        fileName = pair.getValue();
                        break;
                    } else if ("filename".equals(pair.getName())) {
                        fileName = pair.getValue();
                        break;
                    }
                }
            }
            // 设置编码,防止中文文件名乱码

            fileName = URLEncoder.encode(fileName, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().header("Content-Disposition", "attachment; filename=\"" + fileName
                                                                 + "\"").contentType(MediaType.parseMediaType("application/octet-stream")).contentLength(fileByteArr.length).body(resource);
    }

    private void recordVersionLog(HttpServletRequest request) {
        SysDataIssueLog record = new SysDataIssueLog();
        record.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        record.setDictType(request.getParameter("dictType") == null ? "" : request.getParameter("dictType"));
        record.setDictVersion(request.getParameter("dictVersion") == null ? "" : request.getParameter("dictVersion"));
        record.setDownloadDate(new Date());
        record.setLccCode(request.getParameter("confNo") == null ? "" : request.getParameter("confNo"));
        record.setProjectId("009");
        record.setSysVersion(request.getParameter("sysVersion") == null ? "" : request.getParameter("sysVersion"));
        this.sysDataIssueLogMapper.insert(record);
    }
}
