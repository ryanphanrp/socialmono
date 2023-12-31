package org.ryan.application;

import org.ryan.dto.ResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class HomeController {

  @GetMapping
  public ResponseDto<String> home() {
    return ResponseDto.ok("Hello world! - User Service");
  }
}
