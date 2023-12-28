package org.ryan.application.dto.request;

import java.util.Objects;
import org.ryan.constant.CoreResponseCode;
import org.ryan.exception.SocialMonoException;

public record FollowUserDto(Long from, Long to) {

  public FollowUserDto {
    // TODO: check current user with user need to follow
    if (Objects.equals(from, to)) {
      throw new SocialMonoException(CoreResponseCode.FORBIDDEN);
    }
  }
}
