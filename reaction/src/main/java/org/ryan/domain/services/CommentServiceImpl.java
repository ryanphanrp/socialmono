package org.ryan.domain.services;

import lombok.AllArgsConstructor;
import org.ryan.application.dto.CommentCreateDto;
import org.ryan.domain.entity.Comment;
import org.ryan.domain.repositories.CommentRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    CommentRepository repository;

    @Override
    public Mono<Comment> createComment(CommentCreateDto dto) {
        return repository.save(dto.toEntity());
    }

    @Override
    public Mono<Comment> updateComment(Comment comment) {
        return repository.save(comment);
    }

    @Override
    public Mono<Void> deleteComment(String commentId) {
        return repository.deleteById(commentId);
    }

    @Override
    public Mono<Comment> findCommentById(String commentId) {
        return repository.findById(commentId);
    }

    @Override
    public Flux<Comment> findAllComments() {
        return repository.findAll();
    }

    @Override
    public Flux<Comment> findAllCommentsByOwnerId(Long ownerId) {
        return repository.findAllByOwnerId(ownerId);
    }
}
