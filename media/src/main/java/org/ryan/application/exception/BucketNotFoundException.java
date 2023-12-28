package org.ryan.application.exception;

import org.ryan.constant.CoreResponseCode;
import org.ryan.exception.SocialMonoException;
import org.springframework.http.HttpStatus;

public class BucketNotFoundException extends SocialMonoException {

  public BucketNotFoundException(String bucket) {
    super(CoreResponseCode.NOT_FOUND, "Bucket " + bucket + " not found", HttpStatus.NOT_FOUND);
  }
}
