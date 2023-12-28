package org.ryan.application.exception;

import org.ryan.constant.CoreResponseCode;
import org.ryan.exception.SocialMonoException;
import org.springframework.http.HttpStatus;

public class MediaServiceException extends SocialMonoException {

  public MediaServiceException(String message) {
    super(CoreResponseCode.INTERNAL_ERROR, message, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
