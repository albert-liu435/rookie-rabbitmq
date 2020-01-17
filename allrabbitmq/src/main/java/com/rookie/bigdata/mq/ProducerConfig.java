package com.rookie.bigdata.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName ProducerProcessing
 * @Description ProducerProcessing
 * @Author
 * @Date 2020/1/16 14:37
 * @Version 1.0
 */
@Configuration
public class ProducerConfig {

    private static final Logger logger = LoggerFactory.getLogger(ProducerConfig.class);

    @Autowired
    private CachingConnectionFactory connectionFactory;

    @Bean
    public RabbitTemplate rabbitTemplate() {
        connectionFactory.setPublisherConfirms(true); //Use full publisher confirms, with correlation data and a callback for each message.
        connectionFactory.setPublisherReturns(true);
        connectionFactory.setConnectionTimeout(6000); //超时时间
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory); //采用默认模式创建模板
        rabbitTemplate.setMandatory(true); //强制
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                logger.info("消息发送成功:correlationData({}),ack({}),cause({})", correlationData, ack, cause);
            }
        });
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                //TODO 数据没有投递成功，可以在这里进行一些操作，比如讲消息保存到数据库中，等待下次进行投递
                logger.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}", exchange, routingKey, replyCode, replyText, message);
            }
        });
        return rabbitTemplate;
    }
}
