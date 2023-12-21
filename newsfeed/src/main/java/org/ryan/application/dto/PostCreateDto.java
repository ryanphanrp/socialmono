package org.ryan.application.dto;

import org.ryan.domain.Post;

public record PostCreateDto(String content) {

  public Post createPost(Long userId) {
    return Post.builder()
        .withContent(content)
        .withUserId(userId)
        .build();
  }
}
