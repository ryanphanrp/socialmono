package org.ryan.exception.customize;

import org.ryan.constant.ResponseCode;
import org.ryan.exception.SocialMonoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MonoBadRequestException extends SocialMonoException {

  public MonoBadRequestException() {
    super(ResponseCode.NOT_FOUND);
  }

  public MonoBadRequestException(String message) {
    super(ResponseCode.NOT_FOUND, message);
  }
}
