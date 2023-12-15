package org.ryan.application.controller;

import java.util.List;
import org.ryan.application.dto.response.UserDetailDto;
import org.ryan.constant.GlobalConstant;
import org.ryan.dto.ResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/users")
public interface UserAPI {

  @GetMapping
  ResponseDto<List<UserDetailDto>> users();

  @GetMapping("/{username}")
  ResponseDto<UserDetailDto> userDetail(@PathVariable String username);

  @PostMapping("/activate")
  ResponseDto<Object> activateUser(@RequestHeader(GlobalConstant.USER_HEADER) String username);
}
