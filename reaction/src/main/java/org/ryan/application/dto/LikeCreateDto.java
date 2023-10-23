package org.ryan.application.dto;

import org.ryan.domain.entity.Likes;

import java.util.UUID;

public record LikeCreateDto(String commentId, Long postId, Long ownerId) {
    public Likes toEntity() {
        Likes like = new Likes();
        like.setLikeId(UUID.randomUUID().toString());
        like.setCommentId(commentId);
        like.setOwnerId(ownerId);
        like.setPostId(postId);
        return like;
    }
}
