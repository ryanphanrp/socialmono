package org.ryan.application.controller;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.ryan.application.dto.request.FollowUserDto;
import org.ryan.application.dto.response.UserFollowInfoDto;
import org.ryan.domain.service.UserFollowService;
import org.ryan.dto.ResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users/follow")
public class UserFollowController {

  @NonNull
  final UserFollowService userFollowService;

  @GetMapping("/{userId}")
  public ResponseDto<UserFollowInfoDto> getUserFollow(@PathVariable Long userId) {
    return ResponseDto.ok(userFollowService.getUserFollowDetail(userId));
  }

  @PostMapping
  public ResponseDto<Long> follow(@RequestBody FollowUserDto dto) {
    return ResponseDto.ok(userFollowService.followUser(dto));
  }
}
