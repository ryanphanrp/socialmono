package org.ryan.application.controller;

import lombok.AllArgsConstructor;
import org.ryan.application.dto.PostCreateDto;
import org.ryan.application.dto.PostDto;
import org.ryan.domain.PostService;
import org.ryan.dto.ResponseDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping
    public ResponseDto<Long> createPost(@RequestBody PostCreateDto dto) {
        return ResponseDto.ok(postService.createPost(dto));
    }
}
