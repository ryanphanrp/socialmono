package org.ryan.application.controller;

import java.util.List;
import lombok.AllArgsConstructor;
import org.ryan.application.dto.response.UserDetailDto;
import org.ryan.domain.service.UserService;
import org.ryan.dto.ResponseDto;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController implements UserAPI {

  final UserService userService;

  public ResponseDto<List<UserDetailDto>> users() {
    return ResponseDto.ok(userService.getUsers());
  }

  public ResponseDto<UserDetailDto> userDetail(String username) {
    return ResponseDto.ok(userService.getUser(username));
  }

  public ResponseDto<Object> activateUser(String username) {
    userService.activateUserBy(username);
    return ResponseDto.ok();
  }
}
