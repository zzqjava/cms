package com.qatang.cms;

import org.apache.shiro.authc.AuthenticationException;

/**
 * Created by chirowong on 2014/9/3.
 */
public class IncorrectCaptchaException extends AuthenticationException {
    public IncorrectCaptchaException() {
        super();
    }

    public IncorrectCaptchaException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectCaptchaException(String message) {
        super(message);
    }

    public IncorrectCaptchaException(Throwable cause) {
        super(cause);
    }
}
