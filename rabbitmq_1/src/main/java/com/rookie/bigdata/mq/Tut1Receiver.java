package com.rookie.bigdata.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

/**
 * @ClassName Tut1Receiver
 * @Description Tut1Receiver
 * @Author
 * @Date 2020/1/15 17:22
 * @Version 1.0
 */

//@RabbitListener可以标注在类上面，当使用在类上面的时候，需要配合@RabbitHandler注解一起使用，
// @RabbitListener标注在类上面表示当有收到消息的时候，就交给带有@RabbitHandler的方法处理，具体找哪个方法处理，需要跟进MessageConverter转换后的java对象。
@RabbitListener(queues = "hello")
public class Tut1Receiver {


    @RabbitHandler
    public void receive(String message) {
        System.out.println(" [x] Received '" + message + "'");
    }
}
