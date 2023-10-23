package org.ryan.domain.services;

import org.ryan.application.dto.LikeCreateDto;
import org.ryan.domain.entity.Likes;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface LikeService {
    Mono<Likes> createLike(LikeCreateDto like);
    Flux<Likes> findAllLikes();
    Mono<Void> deleteLike(String likeId);
    Mono<Likes> findLikeById(String likeId);
}
