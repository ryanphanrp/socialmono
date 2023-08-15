package org.learning.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.learning.application.dto.UserDetailDto;
import org.learning.application.dto.UserDto;
import org.learning.infrastructure.middleware.MessageSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final MessageSender messageSender;

    public UserDto getUser(String username) {
        UserDetailDto dto = messageSender.getUserDetailRPC(username);
        return UserDto.of(dto);
    }
}
