package org.ryan.infrastructure.middleware;

import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ryan.constant.RabbitMessage;
import org.ryan.constant.ResponseCode;
import org.ryan.dto.RpcResponse;
import org.ryan.dto.shared.UserRPCDto;
import org.ryan.exception.SocialMonoException;
import org.ryan.exception.customize.CustomNotFoundException;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageSender {

  private final RabbitTemplate rabbitTemplate;

  public UserRPCDto getUserDetailRPC(String request) {
    log.info("Sending message - user detail: {}", request);
    CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
    RpcResponse<UserRPCDto> response = rabbitTemplate.convertSendAndReceiveAsType(
        RabbitMessage.DIRECT_EXCHANGE,
        RabbitMessage.AUTH_ROUTING_KEY,
        request,
        correlationData,
        new ParameterizedTypeReference<>() {
        }
    );
    return Optional.ofNullable(response)
                   .map(user -> {
                     if (user.isError()) {
                       throw new CustomNotFoundException(user.message());
                     }
                     return user.body();
                   }).orElseThrow(() -> new SocialMonoException(ResponseCode.BAD_REQUEST));
  }

  public Long sendCreateUser(Object request) {
    log.info("Sending message - create user: {}", request);
    Long response = (Long) rabbitTemplate.convertSendAndReceive(
        RabbitMessage.DIRECT_EXCHANGE,
        RabbitMessage.REGISTER_ROUTING_KEY,
        request
    );
    return Optional.ofNullable(response)
                   .orElseThrow(() -> new SocialMonoException(ResponseCode.BAD_REQUEST));
  }
}
