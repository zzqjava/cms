package com.qatang.cms.validator;

import com.qatang.cms.exception.validator.ValidateFailedException;

/**
 * Created by qatang on 14-6-12.
 */
public interface IValidator<T> {
    public boolean validate(T t) throws ValidateFailedException;
}
