package com.rookie.bigdata.sample;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rookie.bigdata.until.ConnectionUtil;

/**
 * @ClassName Send
 * @Description Send
 * @Author liuxili
 * @Date 2020/1/13 10:14
 * @Version 1.0
 *
 * RabbitMQ的简单模式
 *1个生产者将消息交给默认的交换机(AMQP default)
 * 2 交换机获取消息后交给绑定这个生产者的队列(关系是通过队列名称完成)
 * 3 监听当前队列的消费者获取消息,执行消费逻辑
 *
 */
public class Send {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception{
        //获取连接
        Connection connection = ConnectionUtil.getConnection("localhost", 5672, "/", "guest", "guest");

        //声明通道
        Channel channel = connection.createChannel();

        //创建队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        String message="Hello World";
        //发布消息
        channel.basicPublish("",QUEUE_NAME,null,message.getBytes());


        System.out.println(" [x] Sent '" + message + "'");

        //6、关闭通道和连接
        channel.close();
        connection.close();


    }


}
