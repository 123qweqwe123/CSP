package com.lmbx.csp.dispatch.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lmbx.csp.core.utils.EncodeUtils;
import com.lmbx.csp.core.utils.Identities;
import com.lmbx.csp.data.sys.domain.SysDataParseZip;
import com.lmbx.csp.data.sys.mapper.SysDataParseZipMapper;
import com.lmbx.csp.dispatch.service.CspUploadService;
import com.lmbx.csp.handler.DataHandlerHelper;


@Controller
@RequestMapping("upload")
public class CspUploadController extends Observable{
    private static final Logger log = LoggerFactory.getLogger(CspUploadController.class);
    private static ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private CspUploadService cspUploadService;
    @Autowired
    private SysDataParseZipMapper sysDataParseZipMapper;


    @Value("${zipFile.basePath}")
    private String rootDir;


    /**
     * 获取ftp服务器的信息
     */
    @RequestMapping("/ftpInfo")
    @ResponseBody
    public String getFtpInfo(@RequestParam("confNo") String confNo, @RequestParam("projectId") String projectId) {
        log.debug("Http interface getFtpInfo,  params -------------------> ");
        log.debug("confNo\t:" + confNo);
        log.debug("projectId\t:" + projectId);
        Map<String, Object> ftpMap = cspUploadService.getFtpInfo(confNo, projectId);

        try {
            return objectMapper.writeValueAsString(ftpMap);
            //return CryptoUtil.encrypt(confNo, objectMapper.writeValueAsString(ftpMap));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    @RequestMapping("/uploadData")
    @ResponseBody
    public String uploadData(@RequestParam("fileName") String fileName, @RequestParam("savePath") String savePath,
                             @RequestParam("file") MultipartFile file){

        Map<String, Object> result = new HashMap<String, Object>();
        String filePath =  rootDir + savePath;
        File dirPath = new File(filePath);
        if (!dirPath.exists()) {
            dirPath.mkdirs();
        }

        File newFile = new File(filePath + File.separator + fileName);
        try {
            file.transferTo(newFile);
            result.put("suc", true);
            result.put("msg", "文件上传成功");
        } catch (IOException e) {
            e.printStackTrace();
            result.put("suc", false);
            result.put("msg", e.getMessage());
        }
        try {
            return objectMapper.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 上传结果通知接口
     *
     * @param kName
     *            密钥
     * @param kValue
     *            密文
     */
    @SuppressWarnings("unchecked")
    @PostMapping("/uploadResult")
    @ResponseBody
    public List<Map<String, String>> getUploadResult(@RequestParam("kName") String kName,
                                                     @RequestParam("kValue") String kValue) {
        String projectId = null;
        List<Map<String,Object>> fileList1 = null;
        String confNo = null;
        String zipPath = null;
        String fileType = null;
        String terminalId = null;
        //======================================================检验参数是否有效 begin
        try{
            //kValue暂未加密
//            String json;
//            try {
//                json = CryptoUtil.decrypt(kName, kValue);
//            } catch (Exception e1) {
//                throw new RuntimeException("01#参数解密失败");
//            }
//            Map<String, Object> map = objectMapper.readValue(json, HashMap.class);
            Map<String, Object> map = objectMapper.readValue(kValue, HashMap.class);
            projectId = map.get("projectId") + "";
            fileList1 = (List<Map<String,Object>>)map.get("fileList");
            confNo = map.get("confNo") + "";
            zipPath = map.get("ftpPath") + "";
            fileType = map.get("fileType") + "";
            terminalId = map.get("terminalId") + "";


            // 判断参数是否都存在
            if (projectId.isEmpty() || projectId.equals("null")) {
                throw new RuntimeException("01#工程id不存在");
            } else if (confNo.isEmpty() || confNo.equals("null")) {
                throw new RuntimeException("02#会议id不存在");
            } else if (zipPath.isEmpty() || zipPath.equals("null")) {
                throw new RuntimeException("03#zip路径不存在");
            } else if (fileList1 == null || fileList1.isEmpty()) {
                throw new RuntimeException("04#zip文件fileList为空");
            }
        }catch(Exception e){
            List<Map<String, String>> result = new ArrayList<Map<String, String>>();
            Map<String, String> ret = new HashMap<String, String>();
            ret.put("zipfile", "11");
            ret.put("filename", "1");
            ret.put("msg", e.getMessage());
            ret.put("result", "2");
            result.add(ret);
            return result;
        }
        //======================================================检验参数是否有效 end

        String result1 = "1";
        String zipName1 = "temp.zip";
        try{
            handleZipFile(projectId, confNo, fileType, terminalId, zipPath, fileList1);//数据处理
        }catch(Exception e){
            e.printStackTrace();
            result1 = "2";
        }
        List<Map<String, String>> result = new ArrayList<Map<String, String>>();
        Map<String, String> ret = new HashMap<String, String>();
        ret.put("zipfile", zipName1);
        ret.put("filename", zipName1);
        ret.put("result", result1);
        result.add(ret);
        return result;
    }

    /**
     * 遍历文件列表 ，处理每个zip文件
     * @param projectId
     * @param lcc
     * @param fileType
     * @param terminalId
     * @param zipPath
     * @param fileList1
     */
    private void handleZipFile(String projectId, String lcc, String fileType, String terminalId, String zipPath, List<Map<String,Object>> fileList1){
        fileList1.forEach((zipMap) -> {
            String zipName = zipMap.get("filename") + "";
            String md5 = zipMap.get("md5") + "";
            SysDataParseZip record = new SysDataParseZip();
            record.setId(Identities.uuid());
            record.setProjectId(projectId);
            record.setTerminalId(terminalId);//终端编号
            record.setLccCode(lcc);
            record.setUploadMode((short) 3);
            record.setZipPath(zipPath);
            record.setZipName(zipName);
            record.setDataType(fileType);//数据类型
            record.setDataFormat("json");//数据格式 json
            record.setCreateDate(new Date());
            record.setCreateBy("sys");
            record.setHandleState((short) 2);
            sysDataParseZipMapper.insertSelective(record);

            File file = new File(rootDir + zipPath + zipName);
            log.info("文件路径：" + file.getPath());
            try{
                // 判断文件是否存在
                if (!file.exists()) {
                    throw new RuntimeException("04#文件不存在");
                }

                //暂时不用验证
//                String md5ByFile = EncodeUtils.getMd5ByFile(file);
//                if (!md5ByFile.equals(md5)) {
//                    throw new RuntimeException("05#文件已损坏");
//                }

            }catch(Exception e){
                String message = e.getMessage();
                record.setErrorCode(message.substring(0, 2));
                record.setErrorMsg(message);
                record.setHandleState((short) 3);
                record.setHandleDate(new Date());
                sysDataParseZipMapper.updateByPrimaryKey(record);
                throw e;
            }

            new Thread(() -> {
                try{
                    DataHandlerHelper handlerHelper = new DataHandlerHelper(projectId,"420500", fileType);
                    handlerHelper.handlerZipFile(file, record.getId());
                } catch (Exception e) {
                    String message = e.getMessage();
                    record.setErrorCode(message.substring(0, 2));
                    record.setErrorMsg(message);
                    e.printStackTrace();
                    throw new RuntimeException("有文件处理失败");
                }finally{
                    record.setHandleState(record.getErrorMsg() == null ? (short) 1 : (short) 3);
                    record.setHandleDate(new Date());
                    sysDataParseZipMapper.updateByPrimaryKey(record);
                }
            }).start();

        });
    }

    @SuppressWarnings("unchecked")
    @GetMapping("/getKvalue")
    @ResponseBody
    public List<Map<String, String>> getKvalue() {
        try {
            String ftpPath = "C:\\test\\009\\00001\\20171204";
            String filename = "20171222164712.zip";
            Map<String, Object> fileInfo = new HashMap<>();
            List fileList = new ArrayList<>();
            Map<String, Object> fileInfoS = new HashMap<>();
            File uploadFile = new File(ftpPath + File.separator + filename);
            String md5ByFile = EncodeUtils.getMd5ByFile(uploadFile);
            fileInfoS.put("filename", filename);
            fileInfoS.put("md5", md5ByFile);
            fileList.add(fileInfoS);
            fileInfo.put("projectId", "009");
            fileInfo.put("fileList", fileList);
            fileInfo.put("confNo", "M_00001");
            fileInfo.put("ftpPath", "/009/00001/20171204/");
            fileInfo.put("fileType", "checkin");
            //fileInfo.put("fileType", "register");
            fileInfo.put("terminalId", "test_src");
            String fileInfo1 = objectMapper.writeValueAsString(fileInfo);
            return getUploadResult("420500", fileInfo1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
