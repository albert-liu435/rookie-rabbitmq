package com.rookie.bigdata.sample;

import com.rabbitmq.client.*;
import com.rookie.bigdata.until.ConnectionUtil;

import java.io.IOException;

/**
 * @ClassName Recv
 * @Description Recv
 * @Author liuxili
 * @Date 2020/1/14 9:35
 * @Version 1.0
 * RabbitMQ的简单模式
 */
public class Recv {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception{
        //获取连接
        Connection connection = ConnectionUtil.getConnection("localhost", 5672, "/", "guest", "guest");

        //声明通道
        Channel channel = connection.createChannel();

        //声明队列队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

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
