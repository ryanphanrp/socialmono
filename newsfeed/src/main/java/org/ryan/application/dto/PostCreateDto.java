package org.ryan.application.dto;

import jakarta.validation.constraints.NotBlank;
import org.ryan.domain.Post;

public record PostCreateDto(@NotBlank(message = "Content cannot be blank") String content) {
    public Post createPost(Long userId) {
        return Post.builder()
                .withContent(content)
                .withUserId(userId)
                .build();
    }
}
