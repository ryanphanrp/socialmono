package org.ryan.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.ryan.constant.ResponseCode;
import org.ryan.dto.ResponseDto;
import org.ryan.exception.customize.CustomNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.GONE)
    @ExceptionHandler({SocialMonoException.class})
    public ResponseDto<Object> globalAppHandler(HttpServletRequest req, SocialMonoException exp) {
        log.error("[GlobalAppException]: {} - {}", req.getRequestURI(), exp.getMessage());
        return ResponseDto.error(exp.getResponseCode());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({CustomNotFoundException.class})
    public ResponseDto<Object> handleNotFound(HttpServletRequest req, CustomNotFoundException exp) {
        log.error("[NotFoundException]: {} - {}", req.getRequestURI(), exp.getMessage());
        return ResponseDto.error(exp.getResponseCode());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class, SQLException.class})
    public ResponseDto<Object> handleUncaughtException(Exception exp) {
        log.error("[InternalServerException]: {}", exp.getMessage());
        return ResponseDto.error(ResponseCode.INTERNAL_ERROR, exp.getMessage());
    }
}
