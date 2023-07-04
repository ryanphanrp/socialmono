package org.ryan.domain.dao;

import org.ryan.domain.entity.UserFollow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFollowRepository extends JpaRepository<UserFollow, Long> {

    Long countUserFollowsByFromUserId(@Param("userId") Long userId);

    Long countUserFollowsByToUserId(@Param("userId") Long userId);
}
