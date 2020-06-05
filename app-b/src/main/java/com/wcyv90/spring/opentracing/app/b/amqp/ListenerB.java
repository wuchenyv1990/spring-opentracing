package com.wcyv90.spring.opentracing.app.b.amqp;

import com.wcyv90.spring.opentracing.JsonMapper;
import com.wcyv90.spring.opentracing.app.b.dao.MessageRecordRepository;
import com.wcyv90.spring.opentracing.app.b.domain.MessageRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RabbitListener(
        containerFactory = "rabbitListenerContainerFactory",
        bindings = {@QueueBinding(
                value = @Queue(name = "listener-b1", durable = "true"),
                exchange = @Exchange(name = "appExchange", type = "fanout")
        )}
)

@Slf4j
public class ListenerB {

    private final BoundValueOperations<String, Object> valueOperations;
    private final MessageRecordRepository messageRecordRepository;

    public ListenerB(
            @Qualifier("genericRedisTemplate") RedisTemplate<String, Object> redisTemplate,
            MessageRecordRepository messageRecordRepository
    ) {
        this.valueOperations = redisTemplate.boundValueOps("message:transfer");
        this.messageRecordRepository = messageRecordRepository;
    }

    @RabbitHandler
    @Transactional
    public void onMessage(String message) {
        log.info("listener-b1 receive message: {}", message);
        Object transferredMsg = valueOperations.get();
        log.info("Get transferred: {}", transferredMsg);
        MessageRecord messageRecord = messageRecordRepository.saveOrUpdateMessage(
                "BReceiveTransferredMsg", transferredMsg.toString());
        log.info("Update to db: {}", JsonMapper.dumps(messageRecord));
    }

}
