package org.ryan.application.dto.response;

import org.ryan.dto.shared.UserRPCDto;

public record UserDto(Long userId, String username, String email, String password) {
    public static UserDto of(UserRPCDto dto) {
        return new UserDto(dto.userId(), dto.username(), dto.email(), dto.password());
    }
}
