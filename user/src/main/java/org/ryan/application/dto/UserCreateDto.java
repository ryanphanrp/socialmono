package org.ryan.application.dto;

import org.ryan.constant.ResponseCode;
import org.ryan.domain.entity.User;
import org.ryan.exception.SocialMonoException;
import org.ryan.infrastructure.UsernameValidator;

import static org.ryan.utils.PasswordUtil.generatePassword;

public record UserCreateDto(String username, String email) {
    public User toEntity() {
        // TODO: must be use annotation in Spring Constraint Validation
        if (!UsernameValidator.isValid(username)) throw new SocialMonoException(ResponseCode.BAD_REQUEST);
        return User.builder()
                .withUsername(username)
                .withEmail(email)
                .withPassword(generatePassword())
                .build();
    }
}
