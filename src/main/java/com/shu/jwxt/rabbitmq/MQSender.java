package com.shu.jwxt.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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
        String msg = "hello";
        amqpTemplate.convertAndSend(MQConfig.QUEUE,msg);
    }
}
