package org.ryan.application.controller;

import lombok.AllArgsConstructor;
import org.ryan.application.dto.UserDetailDto;
import org.ryan.constant.GlobalConstant;
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

    @PostMapping("/activate")
    public ResponseDto<Object> activateUser(@RequestHeader(GlobalConstant.USER_HEADER) String username) {
        userService.activateUserBy(username);
        return ResponseDto.ok();
    }
}
