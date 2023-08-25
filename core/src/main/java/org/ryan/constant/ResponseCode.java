package org.ryan.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ResponseCode {
    OK(HttpStatus.OK, 200, "Success"),
    CREATED(HttpStatus.CREATED, 201, "Created"),
    NO_CONTENT(HttpStatus.NO_CONTENT, 204, "No Content"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, -400, "Bad Request"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, -401, "Unauthorized"),
    FORBIDDEN(HttpStatus.FORBIDDEN, -403, "Forbidden"),
    NOT_FOUND(HttpStatus.NOT_FOUND, -404, "Resource Not found"),
    CONFLICT(HttpStatus.CONFLICT, -409, "Conflict"),
    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, -500, "Internal Server Error");
    private final HttpStatus httpStatus;
    private final Integer code;
    private final String message;
}
