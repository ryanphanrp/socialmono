package org.ryan.application.controller;

import lombok.AllArgsConstructor;
import org.ryan.application.dto.request.UserCreateDto;
import org.ryan.application.dto.request.UserLoginDto;
import org.ryan.application.dto.response.LoginInfoDto;
import org.ryan.application.dto.response.RefreshTokenDto;
import org.ryan.domain.AuthService;
import org.ryan.dto.ResponseDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping("/login")
  public ResponseDto<LoginInfoDto> login(@RequestBody UserLoginDto dto) {
    return ResponseDto.ok(authService.login(dto));
  }

  @PostMapping("/register")
  public ResponseDto<Long> register(@RequestBody UserCreateDto dto) {
    return ResponseDto.ok(authService.register(dto));
  }

  @PostMapping("/refresh")
  public ResponseDto<LoginInfoDto> refresh(@RequestBody RefreshTokenDto dto) {
    return ResponseDto.ok(authService.refresh(dto));
  }
}
