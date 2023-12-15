package org.ryan.application.controller;

import org.ryan.application.dto.request.FollowUserDto;
import org.ryan.application.dto.response.UserFollowInfoDto;
import org.ryan.dto.ResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/users/follow")
public interface UserFollowAPI {

  @GetMapping("/{userId}")
  ResponseDto<UserFollowInfoDto> getUserFollow(@PathVariable Long userId);

  @PostMapping
  ResponseDto<Long> follow(@RequestBody FollowUserDto dto);
}
