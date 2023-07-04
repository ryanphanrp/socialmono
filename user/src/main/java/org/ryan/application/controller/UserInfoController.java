package org.ryan.application.controller;

import lombok.AllArgsConstructor;
import org.ryan.application.dto.UserInfoCreateDto;
import org.ryan.domain.entity.pojo.UserDetail;
import org.ryan.domain.service.UserInfoService;
import org.ryan.dto.ResponseDto;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users/info")
public class UserInfoController {
    private final UserInfoService userInfoService;

    @PostMapping("/{username}")
    public ResponseDto<Object> createUserInfo(@PathVariable String username, @RequestBody UserInfoCreateDto dto) {
        return ResponseDto.ok(userInfoService.createUserInfo(username, dto));
    }

    @GetMapping("/{username}")
    public ResponseDto<UserDetail> getUserDetail(@PathVariable String username) {
        return ResponseDto.ok(userInfoService.getUserInfoDetail(username));
    }
}
