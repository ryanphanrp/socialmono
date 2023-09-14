package org.ryan.application.dto.response;

import org.ryan.domain.entity.User;

public record UserDto(Long userId, String username) {
    public static UserDto of(User user) {
        return new UserDto(user.getUserId(), user.getUsername());
    }
}
