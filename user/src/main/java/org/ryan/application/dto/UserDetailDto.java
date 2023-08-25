package org.ryan.application.dto;

import org.ryan.domain.entity.User;

import java.io.Serializable;

public record UserDetailDto(Long userId, String username, String email, String status, String password) implements Serializable {
    public static UserDetailDto of(User user) {
        return new UserDetailDto(user.getUserId(),
                user.getUsername(),
                user.getEmail(),
                user.getStatus().toString(),
                user.getPassword());
    }
}
