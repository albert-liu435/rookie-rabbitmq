package com.rookie.bigdata.sample;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rookie.bigdata.until.ConnectionUtil;

/**
 * @ClassName PSEmitLog
 * @Description PSEmitLog
 * @Author
 * @Date 2020/1/15 16:03
 * @Version 1.0
 */
public class DirectEmitLog {

    private static final String EXCHANGE_NAME = "direct_logs";

    public static void main(String[] args) throws Exception {
        //获取连接
        Connection connection = ConnectionUtil.getConnection("localhost", 5672, "/", "guest", "guest");

        Channel channel = connection.createChannel();

        //创建队列
        channel.queueDeclare("direct_loge",false,false,false,null);
        //声明交换机，
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");

        String message="hello";
        //发送消息
//        for (int i = 0; i < 10; i++) {
//            String message = " message" + i;
//            System.out.println("[send]：" + message);
            //发送消息
            channel.basicPublish(EXCHANGE_NAME, "err", null, message.getBytes("utf-8"));

        //}
        channel.close();
        connection.close();

    }
}
