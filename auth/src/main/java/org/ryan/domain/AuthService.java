package org.ryan.domain;

import lombok.AllArgsConstructor;
import org.ryan.application.dto.LoginInfoDto;
import org.ryan.application.dto.RefreshTokenDto;
import org.ryan.application.dto.UserCreateDto;
import org.ryan.application.dto.UserLoginDto;
import org.ryan.infrastructure.middleware.MessageSender;
import org.ryan.security.TokenProvider;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private final AuthUserService authUserService;
    private final TokenProvider tokenProvider;
    private final MessageSender messageSender;

    public LoginInfoDto login(UserLoginDto dto) {
        AuthUser authUser = authUserService.loadUserByUsername(dto.username());
        return toLoginInfoDto(authUser);
    }

    public Long register(UserCreateDto dto) {
        return messageSender.sendCreateUser(dto);
    }

    public LoginInfoDto refresh(RefreshTokenDto dto) {
        var authUser = getAuthentication(dto.refreshToken());
        return toLoginInfoDto(authUser);
    }

    private AuthUser getAuthentication(String refreshToken) {
        var email = tokenProvider.getUsernameFromToken(refreshToken);
        return authUserService.loadUserByUsername(email);
    }

    private LoginInfoDto toLoginInfoDto(AuthUser authUser) {
        var accessToken = tokenProvider.createToken(authUser);
        var refreshToken = tokenProvider.createRefreshToken(authUser);
        return LoginInfoDto.of(accessToken, refreshToken);
    }
}
