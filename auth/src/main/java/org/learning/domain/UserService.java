package org.learning.domain;

import lombok.RequiredArgsConstructor;
import org.learning.application.dto.UserDto;
import org.learning.infrastructure.middleware.MessageHandler;
import org.learning.infrastructure.middleware.MessageSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final MessageHandler messageHandler;
    private final MessageSender messageSender;

    public UserDto getUser(String username) {
        messageSender.sendMessage(username);
        return messageHandler.receiveMessage();
    }
}
