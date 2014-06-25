package com.qatang.cms.exception.validator;

/**
 * Created by qatang on 14-6-12.
 */
public class ValidateFailedException extends Exception {
    protected final static String MSG = "参数验证失败异常";

    public ValidateFailedException() {
        super(MSG);
    }

    public ValidateFailedException(String message) {
        super(MSG + "：" + message);
    }
}
