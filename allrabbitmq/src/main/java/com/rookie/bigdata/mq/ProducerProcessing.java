package com.rookie.bigdata.mq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @ClassName ProducerProcessing
 * @Description ProducerProcessing
 * @Author
 * @Date 2020/1/16 15:22
 * @Version 1.0
 */
@Component
public class ProducerProcessing {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void directSend() {

        rabbitTemplate.convertAndSend(ReciverProcessing.DIRECT_EXCHANGE, ReciverProcessing.DIRECT_ROUTINGKEY, "订单信息");
        System.out.println("消息投递完成");
    }


   // @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void fanoutSend() {

        rabbitTemplate.convertAndSend(ReciverProcessing.FANOUT_EXCHANGE, "", "订单信息");
        System.out.println("消息投递完成");
    }



}
