package org.ryan.application.controller;

import org.ryan.dto.ResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class HomeController {

  @GetMapping
  public ResponseDto<String> home() {
    return ResponseDto.ok("Hello world! - Auth Service");
  }
}
