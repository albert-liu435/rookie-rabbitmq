package com.rookie.bigdata.consumer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rookie.bigdata.until.ConnectionUtil;

/**
 * @ClassName ConsumerMessage
 * @Description ConsumerMessage
 * @Author
 * @Date 2020/1/6 18:48
 * @Version 1.0
 */
public class ConsumerMessage {
    private final static String QUEUE_NAME = "MQ";

    public static void main(String[] args) throws Exception {
        //1、获取连接
        Connection connection = ConnectionUtil.getConnection("127.0.0.1", 5672, "/", "guest", "guest");
        //2、声明通道
        Channel channel = connection.createChannel();
        //3、声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //4、定义队列的消费者
        //Consumer consumer=new DefaultConsumer(channel);
        QueueingConsumer consumer = new QueueingConsumer(channel);
        //5、监听队列
        channel.basicConsume(QUEUE_NAME, true, consumer);
        //6、获取消息

        while (true) {


            //consumer.n
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" [x] Received '" + message + "'");
        }
    }

}