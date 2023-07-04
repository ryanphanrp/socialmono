package org.ryan.domain.service;

import lombok.RequiredArgsConstructor;
import org.ryan.application.dto.UserInfoCreateDto;
import org.ryan.constant.ResponseCode;
import org.ryan.domain.dao.UserDetailDao;
import org.ryan.domain.dao.UserInfoDao;
import org.ryan.domain.entity.UserInfo;
import org.ryan.domain.entity.pojo.UserDetail;
import org.ryan.exception.SocialMonoException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserInfoService {

    private final UserInfoDao userInfoDao;
    private final UserDetailDao userDetailDao;
    private final UserService userService;

    public Long createUserInfo(String username, UserInfoCreateDto dto) {
        // TODO: need to validate by Spring Security
        if (!userService.validateUserId(username, dto.userId())) {
            throw new SocialMonoException(ResponseCode.FORBIDDEN);
        }
        UserInfo userInfo = dto.toEntity();
        return userInfoDao.save(userInfo).getUserInfoId();
    }

    public UserDetail getUserInfoDetail(String username) {
        return userDetailDao.findUserInfoByUsername(username)
                .orElseThrow(() -> new SocialMonoException(ResponseCode.NOT_FOUND));
    }
}