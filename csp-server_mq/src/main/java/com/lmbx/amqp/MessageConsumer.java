package com.lmbx.amqp;

import com.lmbx.csp.handler.DataHandlerHelper;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Description: 消费者
 * Author: huang.cz
 * Create: 2017/12/19
 */
@Component
public class MessageConsumer implements ChannelAwareMessageListener {

    Logger logger = LoggerFactory.getLogger(MessageConsumer.class);
    @Value("${queueName}")
    private String queueName;

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        String routerKey = message.getMessageProperties().getReceivedRoutingKey();
        String consumerQueue = message.getMessageProperties().getConsumerQueue();
        String dataType = null;
        if(routerKey.contains("person")) {
            dataType = "person";
        } else if(routerKey.contains("register")) {
            dataType = "register";
        } else if(routerKey.contains("checkin")) {
            dataType = "checkin";
        }
        byte[] body = message.getBody();

        try {
            if (dataType != null) {
                logger.info("接收来自{}队列的{}消息: {}",consumerQueue, dataType, new String(body));
                handleAmqpMsg("009" , dataType , body);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        } finally {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);//确认消息成功消费
        }
    }

    /**
     * 处理实时数据
     * @param projectId
     * @param dataType
     * @param msg
     */
    private void handleAmqpMsg(String projectId, String dataType, byte[] msg){
        try {
            DataHandlerHelper handlerHelper = new DataHandlerHelper(projectId, dataType);
            handlerHelper.handlerAmqpMsg(msg, dataType);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("{}消息处理失败", dataType);
        }
    }
}
