package org.ryan.domain.service;

import lombok.RequiredArgsConstructor;
import org.ryan.application.dto.FollowUserDto;
import org.ryan.application.dto.UserFollowInfoDto;
import org.ryan.domain.dao.UserFollowDao;
import org.ryan.domain.dao.UserFollowRepository;
import org.ryan.domain.entity.UserFollow;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFollowService {

    private final UserFollowRepository userFollowRepository;
    private final UserFollowDao userFollowDao;

    public UserFollowInfoDto getFollow(Long userId) {
        Long following = userFollowRepository.countUserFollowsByFromUserId(userId);
        Long follower = userFollowRepository.countUserFollowsByToUserId(userId);
        return UserFollowInfoDto.of(following, follower);
    }

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
