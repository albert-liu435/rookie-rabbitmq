package com.rookie.bigdata.sample;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;
import com.rookie.bigdata.until.ConnectionUtil;

/**
 * @ClassName Recv
 * @Description Recv
 * @Author
 * @Date 2020/1/14 9:35
 * @Version 1.0
 * <p>
 * RabbitMQ的工作模式
 * 1 生产者将消息交个交换机
 * 2 交换机交给绑定的队列
 * 3 队列由多个消费者同时监听,只有其中一个能够获取这一条消息,形成了资源的争抢,谁的资源空闲大,争抢到的可能越大;
 */
public class ManWorkRecv {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception {
        //获取连接
        Connection connection = ConnectionUtil.getConnection("localhost", 5672, "/", "guest", "guest");

        //声明通道
        Channel channel = connection.createChannel();

        //声明队列队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);


        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");

            System.out.println(" [x] Received '" + message + "'");
            try {
                doWork(message);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(" [x] Done");
                //手动确认消息已经成功投递
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            }
        };
        boolean autoAck = false; //设置消息手动确认
        channel.basicConsume(QUEUE_NAME, autoAck, deliverCallback, consumerTag -> {
        });

    }

    private static void doWork(String task) throws InterruptedException {
        for (char ch : task.toCharArray()) {
            if (ch == '.') Thread.sleep(1000);
        }
    }
}
