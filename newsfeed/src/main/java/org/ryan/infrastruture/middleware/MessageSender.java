package org.ryan.infrastruture.middleware;

import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ryan.application.dto.UserDto;
import org.ryan.constant.RabbitMessage;
import org.ryan.constant.CoreResponseCode;
import org.ryan.dto.RpcResponse;
import org.ryan.exception.SocialMonoException;
import org.ryan.exception.customize.MonoBadRequestException;
import org.ryan.exception.customize.MonoNotFoundException;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageSender {

  private final RabbitTemplate rabbitTemplate;

  public UserDto getUserDto(Long userId) {
    log.info("Sending message - user detail: {}", userId);
    CorrelationData correlationData = new CorrelationData(UUID.randomUUID()
                                                              .toString());
    RpcResponse<UserDto> response = rabbitTemplate.convertSendAndReceiveAsType(
        RabbitMessage.DIRECT_EXCHANGE,
        RabbitMessage.USER_ROUTING_KEY,
        userId,
        correlationData,
        new ParameterizedTypeReference<>() {}
    );
    return Optional.ofNullable(response)
        .map(user -> {
          if (user.isError()) {
            throw new MonoNotFoundException(user.message());
          }
          return user.body();
        })
        .orElseThrow(() -> new MonoBadRequestException(CoreResponseCode.BAD_REQUEST));
  }
}
