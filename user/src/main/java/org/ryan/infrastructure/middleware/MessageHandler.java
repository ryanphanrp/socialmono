package org.ryan.infrastructure.middleware;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ryan.application.dto.request.UserCreateDto;
import org.ryan.application.dto.response.UserDetailDto;
import org.ryan.application.dto.response.UserDto;
import org.ryan.constant.RabbitMessage;
import org.ryan.constant.ResponseCode;
import org.ryan.domain.dao.UserDao;
import org.ryan.domain.entity.User;
import org.ryan.domain.service.UserService;
import org.ryan.dto.RpcResponse;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@AllArgsConstructor
public class MessageHandler {
    private final UserService userService;
    private final UserDao userDao;

    @RabbitListener(queues = RabbitMessage.AUTH_REQUEST)
    public RpcResponse<UserDetailDto> receiveAuth(String message) {
        log.info("Received - Auth: {}", message);
        Optional<User> userOpt = userDao.findUserByUsername(message);
        return userOpt.map(user -> RpcResponse.ok(UserDetailDto.of(user)))
                      .orElseGet(() -> RpcResponse.error(ResponseCode.NOT_FOUND));
    }

    @RabbitListener(queues = RabbitMessage.USER_REQUEST)
    public RpcResponse<UserDto> receiveNewsfeed(String message) {
        log.info("Received - Auth: {}", message);
        Optional<User> userOpt = userDao.findUserByUsername(message);
        return userOpt.map(user -> RpcResponse.ok(UserDto.of(user)))
                .orElseGet(() -> RpcResponse.error(ResponseCode.NOT_FOUND));
    }

    @RabbitListener(queues = RabbitMessage.REGISTER_REQUEST)
    public Long receiveRegister(UserCreateDto request) {
        log.info("Receive - Register: {}", request);
        return userService.createUser(request);
    }
}
