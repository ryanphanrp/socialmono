package org.ryan.exception;

import lombok.Getter;
import org.ryan.constant.CoreResponseCode;
import org.ryan.constant.ResponseCode;
import org.springframework.http.HttpStatus;

@Getter
public class SocialMonoException extends RuntimeException {

  private final HttpStatus status;
  private final ResponseCode responseCode;

  public SocialMonoException(HttpStatus status, ResponseCode responseCode) {
    super(responseCode.getMessage());
    this.status = status;
    this.responseCode = responseCode;
  }

  public SocialMonoException(String message) {
    super(message);
    this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    this.responseCode = CoreResponseCode.INTERNAL_ERROR;
  }

  public SocialMonoException(ResponseCode responseCode) {
    super(responseCode.getMessage());
    this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    this.responseCode = responseCode;
  }

  public SocialMonoException(ResponseCode responseCode, String message, HttpStatus status) {
    super(message);
    this.responseCode = responseCode;
    this.status = status;
  }

  public SocialMonoException(ResponseCode responseCode, HttpStatus status) {
    super(responseCode.getMessage());
    this.status = status;
    this.responseCode = responseCode;
  }
}
