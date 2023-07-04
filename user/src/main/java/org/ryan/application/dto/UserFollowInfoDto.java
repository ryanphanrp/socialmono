package org.ryan.application.dto;

import org.ryan.domain.entity.pojo.UserFollowDetail;

public record UserFollowInfoDto(Long following, Long follower) {
    public static UserFollowInfoDto of(Long following, Long follower) {
        return new UserFollowInfoDto(following, follower);
    }

    public static UserFollowInfoDto of(UserFollowDetail detail) {
        return of(detail.getFollowing(), detail.getFollowers());
    }
}
