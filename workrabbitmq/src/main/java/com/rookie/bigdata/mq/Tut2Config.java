package com.rookie.bigdata.mq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName Tut2Config
 * @Description Tut2Config
 * @Author liuxili
 * @Date 2020/1/16 11:42
 * @Version 1.0
 */
@Configuration
public class Tut2Config {

    @Bean
    public Queue hello() {
        return new Queue("hello");
    }

    /**
     * 这里是启动两个消费者
     */
    private static class ReceiverConfig{
        @Bean
        public Tut2Receiver receiver1(){
            return new Tut2Receiver(1);
        }

        @Bean
        public Tut2Receiver receiver2(){
            return new Tut2Receiver(2);
        }
    }

    @Bean
    public Tut2Sender sender() {
        return new Tut2Sender();
    }


}
