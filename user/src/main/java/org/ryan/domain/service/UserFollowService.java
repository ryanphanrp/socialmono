package org.ryan.domain.service;

import lombok.RequiredArgsConstructor;
import org.ryan.application.dto.request.FollowUserDto;
import org.ryan.application.dto.response.UserFollowInfoDto;
import org.ryan.domain.dao.UserFollowDao;
import org.ryan.domain.dao.UserFollowRepository;
import org.ryan.domain.entity.UserFollow;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFollowService {

  private final UserFollowRepository userFollowRepository;
  private final UserFollowDao userFollowDao;

  @Cacheable(cacheNames = "user_follow", key = "#userId")
  public UserFollowInfoDto getUserFollowDetail(Long userId) {
    return UserFollowInfoDto.of(userFollowDao.getUserFollowDetail(userId));
  }

  public Long followUser(FollowUserDto dto) {
    UserFollow user = UserFollow.builder()
                                .fromUserId(dto.from())
                                .toUserId(dto.to())
                                .build();
    userFollowRepository.save(user);
    return user.getUserFollowId();
  }
}
