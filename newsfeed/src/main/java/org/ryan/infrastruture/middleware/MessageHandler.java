package org.ryan.infrastruture.middleware;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ryan.application.dto.UserDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageHandler {
    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.queue.backend.name}")
    private String queueName;

    public UserDto receiveMessage() {
        log.info("Receiving message");
        return rabbitTemplate.receiveAndConvert(queueName, new ParameterizedTypeReference<UserDto>() {
        });
    }
}
