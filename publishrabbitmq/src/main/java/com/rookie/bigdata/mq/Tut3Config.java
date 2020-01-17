package com.rookie.bigdata.mq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @ClassName Tut3Config
 * @Description Tut3Config
 * @Author
 * @Date 2020/1/16 13:28
 * @Version 1.0
 */
@Configuration
public class Tut3Config {

    /**
     * 交换器
     * @return
     */
    @Bean
    public FanoutExchange fanout() {
        return new FanoutExchange("tut.fanout");
    }

    //@Profile("receiver")
    private static class ReceiverConfig {

        /**
         * 队列1
         * @return
         */
        @Bean
        public Queue autoDeleteQueue1() {
            return new AnonymousQueue();
        }

        /**
         * 队列2
         * @return
         */
        @Bean
        public Queue autoDeleteQueue2() {
            return new AnonymousQueue();
        }

        /**
         * 绑定队列
         * @param fanout
         * @param autoDeleteQueue1
         * @return
         */
        @Bean
        public Binding binding1(FanoutExchange fanout,
                                Queue autoDeleteQueue1) {
            return BindingBuilder.bind(autoDeleteQueue1).to(fanout);
        }

        @Bean
        public Binding binding2(FanoutExchange fanout,
                                Queue autoDeleteQueue2) {
            return BindingBuilder.bind(autoDeleteQueue2).to(fanout);
        }

        @Bean
        public Tut3Receiver receiver() {
            return new Tut3Receiver();
        }
    }

    //@Profile("sender")
    @Bean
    public Tut3Sender sender() {
        return new Tut3Sender();
    }
}
