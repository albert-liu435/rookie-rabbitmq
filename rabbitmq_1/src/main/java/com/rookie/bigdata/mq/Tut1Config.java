package com.rookie.bigdata.mq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @ClassName Tut1Config
 * @Description Tut1Config
 * @Author liuxili
 * @Date 2020/1/15 17:19
 * @Version 1.0
 */
//@Profile({"tut1", "hello-world"})
@Configuration
public class Tut1Config {

    //声明一个队列
    @Bean
    public Queue hello() {
        return new Queue("hello");
    }

    //@Profile("receiver")
    @Bean
    public Tut1Receiver receiver() {
        //实例化消费者
        return new Tut1Receiver();
    }

    //@Profile("sender")
    @Bean
    public Tut1Sender sender() {
        //实例化生产者
        return new Tut1Sender();
    }

}
