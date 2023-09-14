package org.ryan.domain.service;

import lombok.RequiredArgsConstructor;
import org.ryan.application.dto.request.UserCreateDto;
import org.ryan.application.dto.response.UserDetailDto;
import org.ryan.domain.constant.UserStatus;
import org.ryan.domain.dao.UserDao;
import org.ryan.domain.entity.User;
import org.ryan.exception.customize.CustomNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDao userDao;

    public UserDetailDto getUser(String username) {
        User user = userDao.findUserByUsername(username)
                .orElseThrow(CustomNotFoundException::new);
        return UserDetailDto.of(user);
    }

    @Transactional
    public Long createUser(UserCreateDto dto) {
        User user = userDao.save(dto.toEntity());
        return user.getUserId();
    }

    public List<UserDetailDto> getUsers() {
        return userDao.findAll()
                .stream()
                .map(UserDetailDto::of)
                .toList();
    }

    @Transactional
    public void activateUserBy(String username) {
        User user = userDao.findUserByUsername(username)
                .orElseThrow(CustomNotFoundException::new);
        user.setStatus(UserStatus.ACTIVE);
        userDao.save(user);
    }

    public boolean validateUserId(String username, Long userId) {
        UserDetailDto userDetailDto = getUser(username);
        return Objects.equals(userDetailDto.userId(), userId);
    }
}
