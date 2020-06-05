package com.wcyv90.spring.opentracing.app.a;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableFeignClients
@EnableScheduling
public class ApplicationA {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationA.class, args);
    }

}
