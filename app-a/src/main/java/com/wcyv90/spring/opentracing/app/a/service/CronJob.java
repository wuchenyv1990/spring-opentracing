package com.wcyv90.spring.opentracing.app.a.service;

import com.wcyv90.spring.opentracing.app.a.amqp.MessageSenderA;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Component
@Slf4j
public class CronJob {

    private final MessageSenderA messageSenderA;

    private final BoundValueOperations<String, Object> valueOperations;

    public CronJob(MessageSenderA messageSenderA, @Qualifier("genericRedisTemplate") RedisTemplate<String, Object> redisTemplate) {
        this.messageSenderA = messageSenderA;
        this.valueOperations = redisTemplate.boundValueOps("message:transfer");
    }

    @Scheduled(cron = "0/10 * * * * ?")
    public void triggerAction() {
        String now = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")
                        .withZone(ZoneId.systemDefault()));
        String message = "now is " + now;
        valueOperations.set(now);
        log.info("Cronjob send message: {}.", message);
        messageSenderA.sendMessage(message);
    }

}
