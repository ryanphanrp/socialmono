package org.ryan.infrastructure.middleware;

import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ryan.application.dto.request.UserCreateDto;
import org.ryan.application.dto.response.UserDto;
import org.ryan.constant.RabbitMessage;
import org.ryan.constant.CoreResponseCode;
import org.ryan.domain.dao.UserDao;
import org.ryan.domain.entity.User;
import org.ryan.domain.service.UserService;
import org.ryan.dto.RpcResponse;
import org.ryan.dto.shared.UserRPCDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class MessageHandler {

  private final UserService userService;
  private final UserDao userDao;

  @RabbitListener(queues = RabbitMessage.AUTH_REQUEST)
  public RpcResponse<UserRPCDto> receiveAuth(String message) {
    log.info("Received - Auth: {}", message);
    Optional<User> userOpt = userDao.findUserByUsername(message);
    return userOpt.map(user -> RpcResponse.ok(fromUser(user)))
                  .orElseGet(() -> RpcResponse.error(CoreResponseCode.NOT_FOUND));
  }

  @RabbitListener(queues = RabbitMessage.USER_REQUEST)
  public RpcResponse<UserDto> receiveNewsfeed(Long message) {
    log.info("Received - User: {}", message);
    Optional<User> userOpt = userDao.findUserByUserId(message);
    return userOpt.map(user -> RpcResponse.ok(UserDto.of(user)))
                  .orElseGet(() -> RpcResponse.error(CoreResponseCode.NOT_FOUND));
  }

  @RabbitListener(queues = RabbitMessage.REGISTER_REQUEST)
  public Long receiveRegister(UserCreateDto request) {
    log.info("Received - Register: {}", request);
    return userService.createUser(request);
  }

  private UserRPCDto fromUser(User user) {
    return UserRPCDto.from(
        user.getUserId(),
        user.getUsername(),
        user.getEmail(),
        String.valueOf(user.getStatus()),
        user.getPassword()
    );
  }
}
