package org.ryan.exception.customize;

import org.ryan.constant.CoreResponseCode;
import org.ryan.constant.ResponseCode;
import org.ryan.exception.SocialMonoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MonoBadRequestException extends SocialMonoException {

  public MonoBadRequestException() {
    super(CoreResponseCode.BAD_REQUEST);
  }

  public MonoBadRequestException(String message) {
    super(CoreResponseCode.NOT_FOUND, message, HttpStatus.BAD_REQUEST);
  }

  public MonoBadRequestException(ResponseCode code) {
    super(code, code.getMessage(), HttpStatus.BAD_REQUEST);
  }
}
