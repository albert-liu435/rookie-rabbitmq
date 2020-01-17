package com.rookie.bigdata.until;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName ConnectionUtil
 * @Description ConnectionUtil
 * @Author
 * @Date 2020/1/6 18:29
 * @Version 1.0
 */
public class ConnectionUtil {

    public static Connection getConnection(String host, int port, String vHost, String userName, String passWord) throws IOException, TimeoutException {

        //定义连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();


        //设置服务器地址
        connectionFactory.setHost(host);

        //设置端口号
        connectionFactory.setPort(port);

        //设置主机，用户名，密码
        connectionFactory.setVirtualHost(vHost);
        connectionFactory.setUsername(userName);
        connectionFactory.setPassword(passWord);


        //返回连接
        return connectionFactory.newConnection();


    }
}
