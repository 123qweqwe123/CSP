package com.lmbx.core;

import com.lmbx.amqp.MessageConsumer;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description:
 * Author: huang.cz
 * Create: 2017/12/19
 */

@Configuration
public class RabbitMqConfig {

    private static final String EXCHANGE = "csp";
    private static final String ROUTINGKEY = "#";

    @Value("${queueName}")
    private String queueName;

    @Bean
    public Exchange myExchange() {
        return ExchangeBuilder.topicExchange(EXCHANGE).durable(true).build();
    }

    @Bean
    public Queue myQueue() {
        return QueueBuilder.durable(queueName).build();
    }

    @Bean
    public Binding myExchangeBinding() {
        return BindingBuilder.bind(myQueue()).to(myExchange()).with(ROUTINGKEY).noargs();
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setExposeListenerChannel(true);
        container.setMaxConcurrentConsumers(1);
        container.setConcurrentConsumers(1);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL); //设置确认模式手工确认
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    MessageConsumer messageConsumer() {
        return new MessageConsumer();
    }

    @Bean
    MessageListenerAdapter listenerAdapter(MessageConsumer messageConsumer) {
        return new MessageListenerAdapter(messageConsumer,"onMessage");
    }
}
