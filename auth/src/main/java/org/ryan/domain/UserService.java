package org.ryan.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ryan.application.dto.UserDetailDto;
import org.ryan.application.dto.UserDto;
import org.ryan.infrastructure.middleware.MessageSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final MessageSender messageSender;

    public UserDto getUser(String username) {
        UserDetailDto user = messageSender.getUserDetailRPC(username);
        return UserDto.of(user);
    }
}
