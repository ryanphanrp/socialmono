package org.ryan.domain.dao;

import org.ryan.domain.entity.pojo.UserFollowDetail;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFollowDao {

    UserFollowDetail getUserFollowDetail(Long userId);
}
