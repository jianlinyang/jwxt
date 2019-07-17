package com.shu.jwxt.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author yang
 * @date 2019/6/29 21:16
 */
@Service
@Slf4j
public class MQReceiver {

    @RabbitListener(queues = MQConfig.QUEUE)//指明监听的是哪一个queue
    public void receive(String message) {
        log.info("receive message:" + message);
    }
}
