package com.rookie.bigdata.sample;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import com.rookie.bigdata.until.ConnectionUtil;

/**
 * @ClassName Send
 * @Description Send
 * @Author liuxili
 * @Date 2020/1/13 10:14
 * @Version 1.0
 * <p>
 * RabbitMQ的工作模式
 * 1 生产者将消息交个交换机
 * 2 交换机交给绑定的队列
 * 3 队列由多个消费者同时监听,只有其中一个能够获取这一条消息,形成了资源的争抢,谁的资源空闲大,争抢到的可能越大;
 */
public class WorkSend {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception {
        //获取连接
        Connection connection = ConnectionUtil.getConnection("localhost", 5672, "/", "guest", "guest");
        //声明通道
        Channel channel = connection.createChannel();
        //创建队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        channel.basicPublish("", "hello", null, "First.".getBytes());
        channel.basicPublish("", "hello", null, "Secode..".getBytes());
        channel.basicPublish("", "hello", null, "Third....".getBytes());
        channel.basicPublish("", "hello", null, "Fourth....".getBytes());
        channel.basicPublish("", "hello", null, "Fifth.....".getBytes());
        //channel.basicPublish("", "hello", MessageProperties.PERSISTENT_TEXT_PLAIN, "Fifth.....".getBytes());

       channel.basicQos(1);
        //6、关闭通道和连接
        channel.close();
        connection.close();
    }
}
