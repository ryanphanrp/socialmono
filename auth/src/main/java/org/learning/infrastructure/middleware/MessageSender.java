package org.learning.infrastructure.middleware;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.learning.application.dto.UserDetailDto;
import org.ryan.constant.RabbitMessage;
import org.ryan.constant.ResponseCode;
import org.ryan.exception.SocialMonoException;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageSender {
    private final RabbitTemplate rabbitTemplate;

    public UserDetailDto getUserDetailRPC(String request) {
        log.info("Sending message - user detail: {}", request);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        UserDetailDto response = rabbitTemplate.convertSendAndReceiveAsType(RabbitMessage.DIRECT_EXCHANGE, RabbitMessage.AUTH_ROUTING_KEY, request, correlationData, new ParameterizedTypeReference<>() {
        });
        if (Objects.isNull(response)) {
            throw new SocialMonoException(ResponseCode.NOT_FOUND);
        }
        return response;
    }

    public Long sendCreateUser(Object request) {
        log.info("Sending message - create user: {}", request);
        Long response = (Long) rabbitTemplate.convertSendAndReceive(RabbitMessage.DIRECT_EXCHANGE, RabbitMessage.REGISTER_ROUTING_KEY, request);
        log.info("Response: {}", response);
        if (Objects.isNull(response)) {
            throw new SocialMonoException(ResponseCode.BAD_REQUEST);
        }
        return response;
    }
}
