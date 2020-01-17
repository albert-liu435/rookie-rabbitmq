package com.rookie.bigdata.mq;


import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName ReciverProcessing
 * @Description ReciverProcessing
 * @Author
 * @Date 2020/1/16 14:25
 * @Version 1.0
 */
@Configuration
public class ReciverProcessing {

    //Direct模式
    public static final String DIRECT_EXCHANGE = "direct_exchange";
    public static final String DIRECT_ROUTINGKEY = "direct_rountingkey";
    public static final String DIRECT_QUEUE = "direct_queue";

    @Autowired
    private ConnectionFactory connectionFactory;

    //发布订阅模式
    public static final String FANOUT_EXCHANGE = "fanout_exchange";
    public static final String FANOUT_QUEUE1 = "fanout_queue1";
    public static final String FANOUT_QUEUE2 = "fanout_queue2";


    /**
     * 实例化 DirectExchange
     *
     * @return
     */
    @Bean
    public DirectExchange directExchange() {
        /**
         * Exchange名称
         * Exchange持久化
         * Exchange 是否自动删除
         */
        return new DirectExchange(DIRECT_EXCHANGE, true, false);
    }

    /**
     * 创建广播模式交换机
     *
     * @return
     */
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE, true, false);
    }


    @Bean
    public Queue directQueue() {
        /**
         * 队列名称
         * 队列是否持久化
         */
        return new Queue(DIRECT_QUEUE, true); //队列持久

    }




    @Bean
    public Binding directBinding() {
        /**
         * 交换器与队列绑定
         */
        return BindingBuilder.bind(directQueue()).to(directExchange()).with(DIRECT_ROUTINGKEY);
    }




    @Bean
    public SimpleMessageListenerContainer DirectMessageContainer() {

        //创建队列监听容器
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer(connectionFactory);
        //监听的队列，可多个
        simpleMessageListenerContainer.setQueues(directQueue());
        simpleMessageListenerContainer.setExposeListenerChannel(true);
        //最大消费者数量
        simpleMessageListenerContainer.setMaxConcurrentConsumers(10);
        //设置并发数
        simpleMessageListenerContainer.setConcurrentConsumers(3);
        //一次拉取消息的数量
        simpleMessageListenerContainer.setPrefetchCount(1);
        //确认模式
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setMessageListener(new ChannelAwareMessageListener() {


            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                String body = new String(message.getBody());
                //TODO 这里开始处理消息，处理消息成功以后可以发送确认消息成功
                System.out.println("当前线程 " + Thread.currentThread().getName() + " 收到的消息为： " + body);
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false); //确认消息成功消费
                //channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,true);
            }
        });
        return simpleMessageListenerContainer;


    }

    /**
     * 队列1
     *
     * @return
     */
    @Bean
    public Queue fanoutQueue1() {
        return new Queue(FANOUT_QUEUE1, true);
    }

    /**
     * 队列2
     *
     * @return
     */
    @Bean
    public Queue fanoutQueue2() {
        return new Queue(FANOUT_QUEUE2, true);
    }

    /**
     * 队列1绑定
     *
     * @return
     */
    @Bean
    public Binding fanoutBinding1() {
        return BindingBuilder.bind(fanoutQueue1()).to(fanoutExchange());
    }

    /**
     * 队列2绑定
     *
     * @return
     */
    @Bean
    public Binding fanoutBinding2() {
        return BindingBuilder.bind(fanoutQueue2()).to(fanoutExchange());
    }
    /**
     * 监听队列进行消费,这里相当于一个消费者监听了两个队列，在实际开发应用中，发布订阅模式监听两个队列，收到的消息是一样的，所以这样做是没有什么意义的
     * 可以设置两个消费者，每个消费者只监听其中的一个队列，收到消息后根据需要来进行不同的处理
     *
     * @return
     */

    @Bean
    public SimpleMessageListenerContainer fanoutMessageContainer() {

        //创建队列监听容器
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer(connectionFactory);
        //监听的队列，可多个
        simpleMessageListenerContainer.setQueues(fanoutQueue1(), fanoutQueue2());
        simpleMessageListenerContainer.setExposeListenerChannel(true);
        //最大消费者数量
        simpleMessageListenerContainer.setMaxConcurrentConsumers(10);
        //设置并发数
        simpleMessageListenerContainer.setConcurrentConsumers(3);
        //一次拉取消息的数量
        simpleMessageListenerContainer.setPrefetchCount(1);
        //确认模式
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setMessageListener(new ChannelAwareMessageListener() {


            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                String body = new String(message.getBody());
                //TODO 这里开始处理消息，处理消息成功以后可以发送确认消息成功
                System.out.println("当前线程 " + Thread.currentThread().getName() + " 收到的消息为： " + body);
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false); //确认消息成功消费
                //channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,true);
            }
        });
        return simpleMessageListenerContainer;


    }

}
