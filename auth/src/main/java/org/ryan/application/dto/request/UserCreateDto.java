package org.ryan.application.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UserCreateDto(
        @Size(min = 3, message = "Username must be at least 3 characters")
        String username,
        @Email(message = "Invalid email")
        String email,
        @Size(min = 8, message = "Password must be at least 8 characters")
        String password) {
}
