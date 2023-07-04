package org.ryan.application.dto;

import org.ryan.domain.Post;

public record PostCreateDto(String content, Long userId) {
    public Post toEntity() {
        return Post.builder()
                .withContent(content)
                .withUserId(userId)
                .build();
    }
}
