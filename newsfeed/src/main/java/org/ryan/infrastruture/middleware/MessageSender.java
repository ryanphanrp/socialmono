package org.ryan.infrastruture.middleware;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageSender {
    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.queue.service.name}")
    private String queueName;

    public void sendMessage(Object event) {
        log.info("Sending message: {}", event);
        rabbitTemplate.convertAndSend(queueName, event);
    }
}
