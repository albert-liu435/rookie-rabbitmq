package com.rookie.bigdata;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @ClassName RabbitAmqpTutorialsApplication
 * @Description RabbitAmqpTutorialsApplication
 * @Author liuxili
 * @Date 2020/1/15 17:25
 * @Version 1.0
 */

@SpringBootApplication
@EnableScheduling
public class RabbitAmqpTutorialsApplication {

//    @Profile("usage_message")
//    @Bean
//    public CommandLineRunner usage() {
//        return args -> {
//            System.out.println("This app uses Spring Profiles to control its behavior.\n");
//                    System.out.println("Sample usage: java -jar rabbit-tutorials.jar --spring.profiles.active=hello-world,sender");
//        };
//    }
//
//    @Profile("!usage_message")
//    @Bean
//    public CommandLineRunner tutorial() {
//        return new RabbitAmqpTutorialsRunner();
//    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(RabbitAmqpTutorialsApplication.class, args);
    }
}
