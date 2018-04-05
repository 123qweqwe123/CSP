package com.lmbx.csp.web.common.controller;

import com.lmbx.csp.web.common.service.CommonService;
import org.apache.commons.lang3.StringUtils;
import org.csource.common.NameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Description:
 * 
 * <pre>
 *     统一文件管理 上传/下载
 * </pre>
 * 
 * @author javahuang
 * @since 2017/11/24 上午10:11
 */
@RestController
@RequestMapping("/common/file")
public class FileController {

    @Autowired
    private CommonService commonService;

    /**
     * 上传文件
     *
     * @param file
     * @return 文件的 ID
     */
    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) {
        return commonService.upload(file);
    }

    @GetMapping("/preview")
    public ResponseEntity<Resource> preview(String fileId) {
        byte[] fileByteArr = commonService.download(fileId);
        ByteArrayResource resource = new ByteArrayResource(fileByteArr);
        return ResponseEntity.ok().contentType(getContentType(fileId)).contentLength(fileByteArr.length).body(resource);
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> download(String fileId) throws UnsupportedEncodingException {
        Map<String, Object> fileWithMetaData = commonService.downloadWithMetaData(fileId);
        byte[] fileByteArr = (byte[]) fileWithMetaData.get("body");
        ByteArrayResource resource = new ByteArrayResource(fileByteArr);
        NameValuePair[] metaData = (NameValuePair[]) fileWithMetaData.get("meta");
        String fileName = "";
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
        return ResponseEntity.ok().header("Content-Disposition", "attachment; filename=\"" + fileName
                                                                 + "\"").contentType(MediaType.parseMediaType("application/octet-stream")).contentLength(fileByteArr.length).body(resource);
    }

    /**
     * 下载文件模板， 模板存在在 resources/
     * 
     * @param tempName
     * @return
     * @throws UnsupportedEncodingException
     */
    @GetMapping("/template")
    public ResponseEntity<Resource> downloadTemplate(String tempName) throws IOException {
        Resource resource = new ClassPathResource("template/" + tempName);
        String fileName = new String(tempName.getBytes(System.getProperty("file.encoding")), "ISO-8859-1");
        return ResponseEntity.ok().header("Content-Disposition", "attachment; filename=\"" + fileName
                                                                 + "\"").contentType(MediaType.parseMediaType("application/octet-stream")).contentLength(resource.contentLength()).body(resource);
    }

    private MediaType getContentType(String fileName) {
        if (StringUtils.endsWithIgnoreCase(fileName, "pdf")) {
            return MediaType.APPLICATION_PDF;
        } else {
            return MediaType.IMAGE_JPEG;
        }
    }
}
