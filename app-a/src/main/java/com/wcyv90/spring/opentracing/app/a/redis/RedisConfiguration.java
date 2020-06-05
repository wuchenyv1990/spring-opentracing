package com.wcyv90.spring.opentracing.app.a.redis;

import com.wcyv90.spring.opentracing.JacksonConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;


@Configuration
public class RedisConfiguration {

    @Bean
    public RedisTemplate<String, Object> genericRedisTemplate(
            RedisConnectionFactory redisConnectionFactory,
            RedisSerializer<Object> genericRedisSerializer
    ) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(RedisSerializer.string());
        template.setValueSerializer(genericRedisSerializer);
        return template;
    }

    @Bean
    public RedisSerializer<Object> genericRedisSerializer() {
        return new GenericJackson2JsonRedisSerializer(JacksonConfig.commonObjectMapper());
    }

}
