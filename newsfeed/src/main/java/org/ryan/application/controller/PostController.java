package org.ryan.application.controller;

import java.util.List;
import lombok.AllArgsConstructor;
import org.ryan.application.dto.PostCreateDto;
import org.ryan.application.dto.PostDetailDto;
import org.ryan.application.dto.PostDto;
import org.ryan.constant.GlobalConstant;
import org.ryan.domain.PostService;
import org.ryan.dto.ResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/post")
public class PostController {

  private final PostService postService;

  @GetMapping
  public ResponseDto<List<PostDto>> posts() {
    return ResponseDto.ok(postService.getPosts());
  }

  @GetMapping("/{postId}")
  public ResponseDto<PostDto> getPost(@PathVariable Long postId) {
    return ResponseDto.ok(postService.getPost(postId));
  }

  @GetMapping("/{postId}/detail")
  public ResponseDto<PostDetailDto> getDetailPost(@PathVariable Long postId) {
    return ResponseDto.ok(postService.getDetailPost(postId));
  }

  @PostMapping
  public ResponseDto<Long> createPost(
      @RequestHeader(GlobalConstant.USER_ID_HEADER) Long userId,
      @RequestBody PostCreateDto dto
  ) {
    return ResponseDto.ok(postService.createPost(dto, userId));
  }
}
