package com.qatang.cms.controller;

import com.qatang.cms.exception.validator.ValidateFailedException;
import com.qatang.cms.form.user.UserForm;
import com.qatang.cms.validator.IValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * Created by qatang on 14-6-5.
 */
@Controller
@SessionAttributes(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY)
public class LoginController extends BaseController {
    @Autowired
    private IValidator<UserForm> loginValidator;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(UserForm userForm, @ModelAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY) String captchaExpected) {
        userForm.setCaptchaExpected(captchaExpected);
        try {
            loginValidator.validate(userForm);
        } catch (ValidateFailedException e) {
            logger.error(e.getMessage(), e);
            userForm.setErrorMessage(e.getMessage());
            return "failure";
        }
        return "login";
    }
}
