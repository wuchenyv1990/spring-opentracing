package com.wcyv90.spring.opentracing.app.a.amqp;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfiguation {

    public static final String APP_EXCHANGE = "appExchange";

    @Bean
    public Exchange appExchange() {
        return new FanoutExchange(APP_EXCHANGE);
    }

}
