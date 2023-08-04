package org.ryan.infrastructure.middleware;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageSender {
    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.queue.newsfeed.backend.name}")
    private String queueNewsfeed;

    @Value("${rabbitmq.queue.auth.backend.name}")
    private String queueAuth;

    public MessageSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(Object event) {
        log.info("Sending message: {}", event);
        rabbitTemplate.convertAndSend(queueNewsfeed, event);
    }

    public void sendAuthMessage(Object event) {
        log.info("Sending message: {}", event);
        rabbitTemplate.convertAndSend(queueAuth, event);
    }
}
