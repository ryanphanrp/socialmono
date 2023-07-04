package org.ryan.application.controller;

import lombok.AllArgsConstructor;
import org.ryan.application.dto.UserCreateDto;
import org.ryan.application.dto.UserDetailDto;
import org.ryan.domain.service.UserService;
import org.ryan.dto.ResponseDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseDto<List<UserDetailDto>> users() {
        return ResponseDto.ok(userService.getUsers());
    }

    @GetMapping("/{username}")
    public ResponseDto<UserDetailDto> userDetail(@PathVariable String username) {
        return ResponseDto.ok(userService.getUser(username));
    }

    @PostMapping
    public ResponseDto<Long> createUser(@RequestBody UserCreateDto dto) {
        return ResponseDto.ok(userService.createUser(dto));
    }

    @PostMapping("/{username}/activate")
    public ResponseDto<Object> activateUser(@PathVariable String username) {
        userService.activateUserBy(username);
        return ResponseDto.ok();
    }
}
