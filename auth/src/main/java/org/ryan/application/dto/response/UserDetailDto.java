package org.ryan.application.dto.response;

import java.io.Serializable;

public record UserDetailDto(
    Long userId,
    String username,
    String email,
    String status,
    String password
) implements Serializable {

}
