package com.rookie.bigdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @ClassName RabbitMQApplication
 * @Description RabbitMQApplication
 * @Author
 * @Date 2020/1/16 14:22
 * @Version 1.0
 */
@SpringBootApplication
@EnableScheduling
public class RabbitMQApplication {

    public static void main(String[] args) {

        SpringApplication.run(RabbitMQApplication.class, args);

    }
}
