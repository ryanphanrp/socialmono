package org.ryan.application.dto;

import org.ryan.domain.entity.Comment;

import java.util.UUID;

public record CommentCreateDto(Long postId, Long ownerId, String content) {
    public Comment toEntity() {
        Comment comment = new Comment();
        comment.setCommentId(UUID.randomUUID().toString());
        comment.setContent(content);
        comment.setOwnerId(ownerId);
        comment.setPostId(postId);
        return comment;
    }
}
