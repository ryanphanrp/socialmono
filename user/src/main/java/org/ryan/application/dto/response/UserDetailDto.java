package org.ryan.application.dto.response;

import org.ryan.domain.entity.User;

import java.io.Serializable;

public record UserDetailDto(Long userId, String username, String email, String status) implements Serializable {
    public static UserDetailDto of(User user) {
        return new UserDetailDto(user.getUserId(),
                user.getUsername(),
                user.getEmail(),
                user.getStatus().toString());
    }
}