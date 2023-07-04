package org.ryan.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record PostDetailDto(
        Long postId,
        String content,
        Long createdAt,
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        Long updatedAt,
        UserDto user
) {
    public static PostDetailDto of(PostDto post, UserDto userDetail) {
        return new PostDetailDto(
                post.postId(),
                post.content(),
                post.getCreatedAt(),
                post.getUpdatedAt(),
                userDetail);
    }
}
