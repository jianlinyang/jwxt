package com.shu.jwxt.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

/**
 * @author yang
 * @date 2019/6/29 21:16
 */
@Component
@Slf4j
public class MQSender {
    private final AmqpTemplate amqpTemplate;

    public MQSender(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void send(Object message) {
        amqpTemplate.convertAndSend(MQConfig.QUEUE, message);
    }
}
