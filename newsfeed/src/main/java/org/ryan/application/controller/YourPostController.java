package org.ryan.application.controller;

import org.ryan.constant.GlobalConstant;
import org.ryan.dto.ResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/your")
public class YourPostController {
    @GetMapping
    public ResponseDto<String> yourPost(@RequestHeader(GlobalConstant.USER_HEADER) String username) {
        String message = "Hello " + username;
        return ResponseDto.ok(message);
    }
}
