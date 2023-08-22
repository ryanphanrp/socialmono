package org.ryan.exception;

import lombok.Getter;
import org.ryan.constant.ResponseCode;

@Getter
public class SocialMonoException extends RuntimeException {
    private final ResponseCode responseCode;

    public SocialMonoException(ResponseCode responseCode) {
        super(responseCode.getMessage());
        this.responseCode = responseCode;
    }

    public SocialMonoException(String message) {
        super(message);
        this.responseCode = ResponseCode.INTERNAL_ERROR;
    }

    public SocialMonoException(ResponseCode responseCode, String message) {
        super(message);
        this.responseCode = responseCode;
    }
}
