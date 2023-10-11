package org.ryan.dto.shared;

import java.io.Serializable;

public record UserRPCDto(Long userId, String username, String email, String status, String password) implements Serializable {
    public static UserRPCDto from(Long userId, String username, String email, String status, String password) {
        return new UserRPCDto(userId, username, email, status, password);
    }
}
