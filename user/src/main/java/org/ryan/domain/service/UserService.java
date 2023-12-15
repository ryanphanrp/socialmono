package org.ryan.domain.service;

import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.ryan.application.dto.request.UserCreateDto;
import org.ryan.application.dto.response.UserDetailDto;
import org.ryan.domain.constant.UserStatus;
import org.ryan.domain.dao.UserDao;
import org.ryan.domain.entity.User;
import org.ryan.exception.customize.CustomNotFoundException;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserDao userDao;

  @Cacheable(cacheNames = "user", key = "#username")
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

  @Cacheable("users")
  public List<UserDetailDto> getUsers() {
    return userDao.findAll().stream().map(UserDetailDto::of).toList();
  }

  @Transactional
  @CachePut(cacheNames = "users_update", key = "#username")
  public void activateUserBy(String username) {
    User user = userDao.findUserByUsername(username)
                       .orElseThrow(CustomNotFoundException::new);
    user.setStatus(UserStatus.ACTIVE);
    userDao.save(user);
  }

  @Cacheable(cacheNames = "is_validated", key = "#username")
  public boolean validateUserId(String username, Long userId) {
    UserDetailDto userDetailDto = getUser(username);
    return Objects.equals(userDetailDto.userId(), userId);
  }
}
