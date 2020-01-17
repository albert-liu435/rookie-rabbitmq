package com.rookie.bigdata.produce;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rookie.bigdata.until.ConnectionUtil;

/**
 * @ClassName ProducerMessage
 * @Description ProducerMessage
 * @Author liuxili
 * @Date 2020/1/6 18:36
 * @Version 1.0
 */
public class ProducerMessage {

    private final static String QUEUE_NAME = "MQ";

    public static void main(String[] args) throws Exception {

        //1、获取连接
        Connection connection = ConnectionUtil.getConnection("127.0.0.1", 5672, "/", "guest", "guest");
        //2、声明通道
        Channel channel = connection.createChannel();
        //3、创建队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //4、定义消息内容
        String message = "hello MQ";
        //发布消息
        for (int i = 0; i < 100; i++) {
            channel.basicPublish("", QUEUE_NAME, null, (message+i).getBytes());
            System.out.println("[x] Sent'" + message+i + "'");
        }
        //6、关闭通道和连接
        channel.close();
        connection.close();

    }

}
