package org.ryan.domain.services;

import org.ryan.application.dto.LikeCreateDto;
import org.ryan.domain.entity.Likes;
import org.ryan.domain.repositories.LikeRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class LikeServiceImpl implements LikeService {
    LikeRepository repository;

    @Override
    public Mono<Likes> createLike(LikeCreateDto like) {
        return repository.save(like.toEntity());
    }

    @Override
    public Flux<Likes> findAllLikes() {
        return repository.findAll();
    }

    @Override
    public Mono<Void> deleteLike(String likeId) {
        return repository.deleteById(likeId);
    }

    @Override
    public Mono<Likes> findLikeById(String likeId) {
        return repository.findById(likeId);
    }
}
