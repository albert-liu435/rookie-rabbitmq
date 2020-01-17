package com.rookie.bigdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @ClassName RabbitAmqpTutorialsApplication
 * @Description RabbitAmqpTutorialsApplication
 * @Author
 * @Date 2020/1/15 17:25
 * @Version 1.0
 */

@SpringBootApplication
@EnableScheduling
public class RabbitAmqpTutorialsApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(RabbitAmqpTutorialsApplication.class, args);
    }
}
