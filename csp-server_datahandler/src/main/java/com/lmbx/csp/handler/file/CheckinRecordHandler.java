package com.lmbx.csp.handler.file;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.lmbx.csp.handler.data.vo.CheckinRecord;
import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lmbx.csp.core.utils.SpringContextHolder;
import com.lmbx.csp.handler.service.CheckinRecordTransctionImpl;

/**
 * 签到数据处理
 * @author yrj
 * 2017-11
 */
public class CheckinRecordHandler extends AbstractFileHandler {
    private  CheckinRecordTransctionImpl checkinRecordTransctionImpl;
    private static ObjectMapper objectMapper = new ObjectMapper();
    private String insertWay;

    public CheckinRecordHandler(String projectId, String codeKey, String fileName, String fileId) {
        super(projectId,codeKey, fileName, fileId);
        checkinRecordTransctionImpl = SpringContextHolder.getBean(CheckinRecordTransctionImpl.class);
    }
    @Override
    protected int handlerFileInner(byte[] output1) {
        String terminalId = getTerminalId();
        List<CheckinRecord> checkinRecords = new ArrayList<>();
        try {
            String jsonStr = new String(output1,"UTF-8");
            //result = dataFormat(jsonStr);
            //json字符串转为List
            checkinRecords = objectMapper.readValue(jsonStr, new TypeReference<List<CheckinRecord>>(){});
            for (CheckinRecord record : checkinRecords) {
                record.setTerminalId(terminalId);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int count = 0;
        try {
            // 调用解析数据的方法,返回签到数据的数量
            CheckinRecordTransctionImpl.HandlerCheckinRecordResult handlerResult = checkinRecordTransctionImpl.parseCheckinRecord(checkinRecords, projectId, getInfoId());
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

    public static void main (String[] args) {
        String filePath = "C:\\test\\record1205.json";
        File filename = new File(filePath);
        String jsonStr = null;
        //List<CheckinRecord> records = new ArrayList<>();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            jsonStr = FileUtils.readFileToString(filename ,"UTF-8");
            List list = objectMapper.readValue(jsonStr, List.class);
//            for (int i = 0; i < list.size(); i++) {
//                // "OPERATOR_TYPE": 1,
//                CspConfCheckinRecord record = new CspConfCheckinRecord();
//                record.setId((String) ((LinkedHashMap) list.get(0)).get("ID"));
//                record.setVisitorId((String) ((LinkedHashMap) list.get(0)).get("VISITOR_ID"));
//                record.setConfId((String) ((LinkedHashMap) list.get(0)).get("CONF_ID"));
//                record.setConfPlaceId((String) ((LinkedHashMap) list.get(0)).get("CONF_PLACE_ID"));
//                record.setVisitorConfNo((String) ((LinkedHashMap) list.get(0)).get("VISITOR_CONF_NO"));
//                record.setRemark((String) ((LinkedHashMap) list.get(0)).get("REMARK"));
//                record.setCheckinBy((String) ((LinkedHashMap) list.get(0)).get("CHECKIN_BY"));
//                record.setCheckinTime(sf.parse((String) ((LinkedHashMap) list.get(0)).get("CHECKIN_TIME")));
//                record.setCreateBy((String) ((LinkedHashMap) list.get(0)).get("CREATE_BY"));
//                record.setCreateTime(sf.parse((String) ((LinkedHashMap) list.get(0)).get("CREATE_TIME")));
//                record.setUpdateBy((String) ((LinkedHashMap) list.get(0)).get("UPDATE_BY"));
//                record.setUpdateTime(sf.parse((String) ((LinkedHashMap) list.get(0)).get("UPDATE_TIME")));
//                record.setSrcId((String) ((LinkedHashMap) list.get(0)).get("SRC_ID"));
//                record.setCheckinId((String) ((LinkedHashMap) list.get(0)).get("CHECKIN_ID"));
//                //CHECNIN_SOURCE
//                records.add(record);
//            }
            //objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            //records = objectMapper.readValue(jsonStr, new TypeReference<List<CheckinRecord>>(){});
            System.out.println("jtest:" + jsonStr);
            System.out.println(list.get(0));
        } catch (IOException e) {
            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
        }
    }
}
