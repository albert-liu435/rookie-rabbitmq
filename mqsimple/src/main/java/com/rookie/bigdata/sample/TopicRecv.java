package com.rookie.bigdata.sample;

import com.rabbitmq.client.*;
import com.rookie.bigdata.until.ConnectionUtil;

import java.io.IOException;

/**
 * @ClassName Recv
 * @Description Recv
 * @Author
 * @Date 2020/1/14 9:35
 * @Version 1.0
 * RabbitMQ的简单模式
 */
public class TopicRecv {

    public static final String QUEUE_NAME = "topic_queues";

    public static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] args) throws Exception{
        //获取连接
        Connection connection = ConnectionUtil.getConnection("localhost", 5672, "/", "guest", "guest");

        //声明通道
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        //声明队列队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //4.绑定队列到交换器,指定路由key为topics
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "topics.#");

        //
        //channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "topic.*");

        DeliverCallback deliverCallback = new DeliverCallback(){
            @Override
            public void handle(String consumerTag, Delivery delivery) throws IOException {
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
            }
        };

        channel.basicConsume(QUEUE_NAME, true, deliverCallback, new CancelCallback(){
            @Override
            public void handle(String consumerTag) throws IOException {

            }
        });

    }
}
