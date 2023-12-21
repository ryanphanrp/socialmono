package org.ryan.domain;

import java.util.Objects;
import org.ryan.application.dto.response.UserDto;
import org.ryan.constant.ResponseCode;
import org.ryan.exception.SocialMonoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthUserService implements UserDetailsService {

  private final UserService userService;

  @Autowired
  public AuthUserService(UserService userService) {
    this.userService = userService;
  }

  @Override
  public AuthUser loadUserByUsername(String username) throws UsernameNotFoundException {
    UserDto userDto = userService.getUser(username);
    if (Objects.isNull(userDto)) {
      throw new SocialMonoException(ResponseCode.NOT_FOUND);
    }
    return AuthUser.of(userDto);
  }
}
