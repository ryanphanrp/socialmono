package org.ryan.domain.repositories;

import org.ryan.domain.entity.Likes;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends ReactiveMongoRepository<Likes, String> {
}
