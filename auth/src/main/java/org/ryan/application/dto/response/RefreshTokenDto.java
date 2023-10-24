package org.ryan.application.dto.response;

import jakarta.validation.constraints.NotBlank;

public record RefreshTokenDto(@NotBlank(message = "Refresh token cannot be blank") String refreshToken) {
}
