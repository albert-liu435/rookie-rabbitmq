package com.rookie.bigdata.sample;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rookie.bigdata.until.ConnectionUtil;

/**
 * @ClassName TopicEmitLog
 * @Description TopicEmitLog
 * @Author
 * @Date 2020/1/16 10:39
 * @Version 1.0
 */
public class TopicEmitLog {
    private static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] args) throws Exception {
        //获取连接
        Connection connection = ConnectionUtil.getConnection("localhost", 5672, "/", "guest", "guest");

        Channel channel = connection.createChannel();

        //创建队列
        //channel.queueDeclare("direct_loge",true,false,false,null);
        //声明交换机，
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");

        String message="hello";
        //发送消息

        //发送消息
        channel.basicPublish(EXCHANGE_NAME, "topics.log", null, message.getBytes("utf-8"));

        channel.close();
        connection.close();

    }
}
