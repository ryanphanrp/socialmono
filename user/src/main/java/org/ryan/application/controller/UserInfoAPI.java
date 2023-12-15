package org.ryan.application.controller;

import org.ryan.application.dto.request.UserInfoCreateDto;
import org.ryan.constant.GlobalConstant;
import org.ryan.domain.entity.pojo.UserDetail;
import org.ryan.dto.ResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/users/info")
public interface UserInfoAPI {

  @PostMapping
  ResponseDto<Object> createUserInfo(@RequestHeader(GlobalConstant.USER_HEADER) String username,
      @RequestBody UserInfoCreateDto dto);

  @GetMapping("/{username}")
  ResponseDto<UserDetail> getUserDetail(@PathVariable String username);
}
