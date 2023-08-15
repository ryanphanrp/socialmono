package org.learning.application.dto;

public record UserDto(Long userId, String username, String email, String password) {
    public static UserDto of(UserDetailDto dto) {
        return new UserDto(dto.userId(), dto.username(), dto.email(), dto.password());
    }
}
