package org.ryan.infrastructure.middleware;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ryan.domain.dao.UserDao;
import org.ryan.domain.dao.UserInfoDao;
import org.ryan.exception.SocialMonoException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class MessageHandler {
    private final UserDao userDao;
    private final UserInfoDao userInfoDao;
    private final MessageSender messageSender;

    @RabbitListener(queues = "${rabbitmq.queue.newsfeed.service.name}")
    public void receiveMessage(Long message) {
        log.info("Received message: {}", message);
        var userDto = userDao.findById(message).orElseThrow(SocialMonoException::new);
        messageSender.sendMessage(userDto);
    }

    @RabbitListener(queues = "${rabbitmq.queue.auth.service.name}")
    public void receiveAuthMessage(String username) {
        log.info("Auth - Received message: {}", username);
        var userDto = userDao.findUserByUsername(username).orElseThrow(SocialMonoException::new);
        messageSender.sendAuthMessage(userDto);
    }
}
