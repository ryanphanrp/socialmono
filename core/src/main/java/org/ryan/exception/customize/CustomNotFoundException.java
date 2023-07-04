package org.ryan.exception.customize;

import org.ryan.constant.ResponseCode;
import org.ryan.exception.SocialMonoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomNotFoundException extends SocialMonoException {
    public CustomNotFoundException() {
        super(ResponseCode.NOT_FOUND);
    }
}
