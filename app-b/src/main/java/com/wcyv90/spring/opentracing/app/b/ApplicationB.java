package com.wcyv90.spring.opentracing.app.b;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableRabbit
@MapperScan("com.wcyv90.spring.opentracing.app.b.mapper")
public class ApplicationB {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationB.class, args);
    }

}
