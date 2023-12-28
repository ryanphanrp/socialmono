package org.ryan.application.dto.request;

import org.ryan.constant.CoreResponseCode;
import org.ryan.domain.entity.User;
import org.ryan.exception.customize.MonoBadRequestException;
import org.ryan.infrastructure.validator.UsernameValidator;

public record UserCreateDto(String username, String email, String password) {

  public User toEntity() {
    // TODO: must be use annotation in Spring Constraint Validation
    if (!UsernameValidator.isValid(username)) {
      throw new MonoBadRequestException(CoreResponseCode.BAD_REQUEST);
    }
    return User.builder()
        .withUsername(username)
        .withEmail(email)
        .withPassword(password)
        .build();
  }
}
