//package com.rookie.bigdata.mq;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.ConfigurableApplicationContext;
//
///**
// * @ClassName RabbitAmqpTutorialsRunner
// * @Description RabbitAmqpTutorialsRunner
// * @Author liuxili
// * @Date 2020/1/15 17:26
// * @Version 1.0
// */
//public class RabbitAmqpTutorialsRunner implements CommandLineRunner {
//
//    @Value("${tutorial.client.duration:0}")
//    private int duration;
//
//    @Autowired
//    private ConfigurableApplicationContext ctx;
//
//    @Override
//    public void run(String... arg0) throws Exception {
//        System.out.println("Ready ... running for " + duration + "ms");
//        Thread.sleep(duration);
//        ctx.close();
//    }
//}