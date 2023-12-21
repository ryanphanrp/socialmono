package org.ryan.application.controller;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.ryan.application.dto.request.UserInfoCreateDto;
import org.ryan.constant.GlobalConstant;
import org.ryan.domain.entity.pojo.UserDetail;
import org.ryan.domain.service.UserInfoService;
import org.ryan.dto.ResponseDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserInfoController implements UserInfoAPI {

  @NonNull
  final UserInfoService userInfoService;

  public ResponseDto<Object> createUserInfo(
      @RequestHeader(GlobalConstant.USER_HEADER) String username,
      @RequestBody UserInfoCreateDto userInfoCreateDto
  ) {
    return ResponseDto.ok(userInfoService.createUserInfo(username, userInfoCreateDto));
  }

  public ResponseDto<UserDetail> getUserDetail(@PathVariable String username) {
    return ResponseDto.ok(userInfoService.getUserInfoDetail(username));
  }
}
