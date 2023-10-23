package org.ryan.domain.services;

import org.ryan.application.dto.CommentCreateDto;
import org.ryan.domain.entity.Comment;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CommentService {
    Mono<Comment> createComment(CommentCreateDto dto);

    Mono<Comment> updateComment(Comment comment);

    Mono<Void> deleteComment(String commentId);

    Mono<Comment> findCommentById(String commentId);

    Flux<Comment> findAllComments();

    Flux<Comment> findAllCommentsByOwnerId(Long ownerId);
}
