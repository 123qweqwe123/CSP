package com.lmbx.csp.handler.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lmbx.csp.core.utils.SpringContextHolder;
import com.lmbx.csp.handler.data.vo.Register;
import com.lmbx.csp.handler.service.RegisterTransctionImpl;

/**
 * 登记数据处理
 * @author yrj
 * 2017-11
 */
public class RegisterHandler extends AbstractFileHandler {
    private RegisterTransctionImpl registerTransctionImpl;
    private static ObjectMapper objectMapper = new ObjectMapper();
    private String insertWay;

    public RegisterHandler(String projectId, String codeKey, String fileName, String fileId) {
        super(projectId,codeKey, fileName, fileId);
        registerTransctionImpl = SpringContextHolder.getBean(RegisterTransctionImpl.class);
    }
    @Override
    protected int handlerFileInner(byte[] output1) {
        String terminalId = getTerminalId();
        List<Register> records = new ArrayList<>();
        try {
            String jsonStr = new String(output1,"UTF-8");
            records = objectMapper.readValue(jsonStr, new TypeReference<List<Register>>(){});
            for (Register register : records) {
                register.setTerminalId(terminalId);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int count = 0;
        try {
            // 调用解析数据的方法,返回登记数据的数量
            RegisterTransctionImpl.HandlerRegisterResult handlerResult = registerTransctionImpl.parseRegister(records, projectId, getInfoId());
            if (handlerResult != null) {
                count = handlerResult.getInsertCount();
                if(handlerResult.isExist()){
                    insertWay = "update";
                }else{
                    insertWay = "insert";
                }
            }
        } catch (Exception e) {
            log.error("10#数据业务处理失败",e);
            throw new RuntimeException("10#数据业务处理失败：" + e.getMessage());
        }
        return count;
    }

    @Override
    protected String getInsertWay() {
        return this.insertWay;
    }

    public static void main(String[] args){
        String filePath = "E:\\test\\cspConfRegister.json";
        File filename = new File(filePath);
        String jsonStr = null;
        try {
            jsonStr = FileUtils.readFileToString(filename ,"UTF-8");
            List<Register> records = objectMapper.readValue(jsonStr, new TypeReference<List<Register>>(){});
            System.out.println("test:" + records.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
