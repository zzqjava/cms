package com.qatang.cms.validator.impl.user;

import com.qatang.cms.exception.validator.ValidateFailedException;
import com.qatang.cms.form.user.UserForm;
import com.qatang.cms.validator.AbstractValidator;

/**
 * Created by JSH on 14-7-3.
 */
public class QueryUserValidator extends AbstractValidator<UserForm> {
    @Override
    public boolean validate(UserForm userForm) throws ValidateFailedException {
        return true;
    }
}
