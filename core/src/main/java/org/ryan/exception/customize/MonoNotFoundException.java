package org.ryan.exception.customize;

import org.ryan.constant.CoreResponseCode;
import org.ryan.constant.ResponseCode;
import org.ryan.exception.SocialMonoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MonoNotFoundException extends SocialMonoException {

  public MonoNotFoundException() {
    super(CoreResponseCode.NOT_FOUND);
  }

  public MonoNotFoundException(ResponseCode responseCode) {
    super(responseCode, HttpStatus.NOT_FOUND);
  }

  public MonoNotFoundException(String message) {
    super(CoreResponseCode.NOT_FOUND, message, HttpStatus.NOT_FOUND);
  }
}
