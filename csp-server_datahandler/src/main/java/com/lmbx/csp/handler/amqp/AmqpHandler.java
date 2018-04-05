package com.lmbx.csp.handler.amqp;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lmbx.csp.core.utils.SpringContextHolder;
import com.lmbx.csp.handler.data.vo.CheckinRecord;
import com.lmbx.csp.handler.data.vo.MainPerson;
import com.lmbx.csp.handler.data.vo.Register;
import com.lmbx.csp.handler.service.CheckinRecordTransctionImpl;
import com.lmbx.csp.handler.service.MainPersonTransctionImpl;
import com.lmbx.csp.handler.service.RegisterTransctionImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class AmqpHandler implements IAmqpHandler {
    Logger log = LoggerFactory.getLogger(AmqpHandler.class);
    private static ObjectMapper objectMapper = new ObjectMapper();
    private CheckinRecordTransctionImpl checkinRecordTransctionImpl;
    private RegisterTransctionImpl registerTransctionImpl;
    private MainPersonTransctionImpl mainPersonTransctionImpl;
    private String projectId;
    private String dataType;
    public AmqpHandler(String projectId, String dataType){
        this.projectId = projectId;
        this.dataType = dataType;
        checkinRecordTransctionImpl = SpringContextHolder.getBean(CheckinRecordTransctionImpl.class);
        registerTransctionImpl = SpringContextHolder.getBean(RegisterTransctionImpl.class);
        mainPersonTransctionImpl = SpringContextHolder.getBean(MainPersonTransctionImpl.class);
    }
    @Override
    public void handlerAmqpMsg(byte[] bytes, String dataType) {
        log.info("========>begin:");
        try {
            String jsonStr = new String(bytes,"UTF-8");
            StringBuffer json = new StringBuffer().append("[").append(jsonStr).append("]");
            if (dataType.equals("checkin")) {
                List<CheckinRecord> checkinRecords = objectMapper.readValue(json.toString(), new TypeReference<List<CheckinRecord>>(){});
                checkinRecordTransctionImpl.parseCheckinRecord(checkinRecords, projectId, "");
            } else if (dataType.equals("register")){
                List<Register> registers = objectMapper.readValue(json.toString(), new TypeReference<List<Register>>(){});
                registerTransctionImpl.parseRegister(registers, projectId, "");
            } else if (dataType.equals("person")) {
                List<MainPerson> mainPersons = objectMapper.readValue(json.toString(), new TypeReference<List<MainPerson>>(){});
                mainPersonTransctionImpl.parseMainPerson(mainPersons, projectId, "");
            }
        } catch (UnsupportedEncodingException e) {
            log.error("数据业务处理失败", e);
        } catch (JsonParseException e) {
            log.error("数据业务处理失败", e);
        } catch (JsonMappingException e) {
            log.error("数据业务处理失败", e);
        } catch (IOException e) {
            log.error("数据业务处理失败", e);
        } catch (Exception e) {
            log.error("数据业务处理失败", e);
        }

        log.info("========>end:");
    }
}
