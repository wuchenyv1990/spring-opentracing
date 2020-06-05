package com.wcyv90.spring.opentracing.app.a.amqp;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.wcyv90.spring.opentracing.app.a.amqp.AmqpConfiguation.APP_EXCHANGE;

@Component
public class MessageSenderA {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend(APP_EXCHANGE, null, message);
    }
}
