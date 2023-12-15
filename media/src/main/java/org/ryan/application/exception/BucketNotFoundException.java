package org.ryan.application.exception;

import org.ryan.constant.ResponseCode;
import org.ryan.exception.SocialMonoException;

public class BucketNotFoundException extends SocialMonoException {

  public BucketNotFoundException(String bucket) {
    super(ResponseCode.NOT_FOUND, "Bucket " + bucket + " not found");
  }
}
