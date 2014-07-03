package com.qatang.cms.validator.impl.user;

import com.qatang.cms.exception.validator.ValidateFailedException;
import com.qatang.cms.form.user.UserForm;
import com.qatang.cms.validator.AbstractValidator;
import org.springframework.stereotype.Component;

/**
 * Created by JSH on 2014/7/2.
 */
@Component
public class UpdateUserValidator extends AbstractValidator<UserForm> {
    @Override
    public boolean validate(UserForm userForm) throws ValidateFailedException {
        return true;
    }
}
