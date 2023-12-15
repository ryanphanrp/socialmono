package org.ryan.application.dto.response;

import java.io.Serializable;
import org.ryan.domain.entity.User;

public record UserDetailDto(
    Long userId, String username, String email, String status
) implements Serializable {

  public static UserDetailDto of(User user) {
    return new UserDetailDto(
        user.getUserId(),
        user.getUsername(),
        user.getEmail(),
        user.getStatus()
            .toString()
    );
  }
}
