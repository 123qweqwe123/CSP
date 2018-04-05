package com.lmbx.csp.handler.file;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lmbx.csp.core.utils.SpringContextHolder;
import com.lmbx.csp.handler.data.vo.MainPerson;
import com.lmbx.csp.handler.service.MainPersonTransctionImpl;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 人员信息处理
 * @author yrj
 * 2017-11
 */
public class MainPersonHandler extends AbstractFileHandler {
    private MainPersonTransctionImpl mainPersonTransctionImpl;
    private static ObjectMapper objectMapper = new ObjectMapper();
    private String insertWay;

    public MainPersonHandler(String projectId, String codeKey, String fileName, String fileId) {
        super(projectId,codeKey, fileName, fileId);
        mainPersonTransctionImpl = SpringContextHolder.getBean(MainPersonTransctionImpl.class);
    }
    @Override
    protected int handlerFileInner(byte[] output1) {
        String terminalId = getTerminalId();
        List<MainPerson> records = new ArrayList<>();
        try {
            String jsonStr = new String(output1,"UTF-8");
            records = objectMapper.readValue(jsonStr, new TypeReference<List<MainPerson>>(){});
            for (MainPerson person : records) {
                person.setTerminalId(terminalId);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int count = 0;
        try {
            // 调用解析数据的方法,返回人员数据的数量
            MainPersonTransctionImpl.HandlerMainPersonResult handlerResult = mainPersonTransctionImpl.parseMainPerson(records, projectId, getInfoId());
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
        String filePath = "E:\\test\\cspmainpersion.json";
        File filename = new File(filePath);
        String jsonStr = null;
        try {
            jsonStr = FileUtils.readFileToString(filename ,"UTF-8");
            List<MainPerson> records = objectMapper.readValue(jsonStr, new TypeReference<List<MainPerson>>(){});
            System.out.println("test:" + records.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
