package org.ryan.infrastructure.middleware;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ryan.application.dto.UserCreateDto;
import org.ryan.application.dto.UserDetailDto;
import org.ryan.constant.RabbitMessage;
import org.ryan.constant.ResponseCode;
import org.ryan.domain.dao.UserDao;
import org.ryan.domain.service.UserService;
import org.ryan.exception.SocialMonoException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class MessageHandler {
    private final UserDao userDao;
    private final UserService userService;

    @RabbitListener(queues = RabbitMessage.AUTH_REQUEST)
    public UserDetailDto receiveAuth(String message) {
        log.info("Received - Auth: {}", message);
        return userService.getUser(message);
    }

    @RabbitListener(queues = RabbitMessage.REGISTER_REQUEST)
    public Long receiveRegister(UserCreateDto request) {
        log.info("Receive - Register: {}", request);
        return userService.createUser(request);
    }
}
