package org.ryan.application.exception;

import org.ryan.constant.ResponseCode;
import org.ryan.exception.SocialMonoException;

public class MediaServiceException extends SocialMonoException {
    public MediaServiceException(String message) {
        super(ResponseCode.INTERNAL_ERROR, message);
    }
}
