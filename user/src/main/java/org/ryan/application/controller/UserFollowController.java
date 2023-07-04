package org.ryan.application.controller;

import lombok.AllArgsConstructor;
import org.ryan.application.dto.FollowUserDto;
import org.ryan.application.dto.UserFollowInfoDto;
import org.ryan.domain.service.UserFollowService;
import org.ryan.dto.ResponseDto;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users/follow")
public class UserFollowController {

    private final UserFollowService userFollowService;

    @GetMapping("/{userId}")
    public ResponseDto<UserFollowInfoDto> getUserFollow(@PathVariable Long userId) {
        return ResponseDto.ok(userFollowService.getUserFollowDetail(userId));
    }

    @PostMapping
    public ResponseDto<Long> follow(@RequestBody FollowUserDto dto) {
        return ResponseDto.ok(userFollowService.followUser(dto));
    }
}
