package com.qatang.cms.controller.user;

import com.qatang.cms.controller.BaseController;
import com.qatang.cms.entity.user.User;
import com.qatang.cms.exception.validator.ValidateFailedException;
import com.qatang.cms.form.user.UserForm;
import com.qatang.cms.service.user.UserService;
import com.qatang.cms.validator.IValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Date;

/**
 * Created by qatang on 14-6-12.
 */
@Controller
@SessionAttributes(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY)
public class RegisterController extends BaseController {
    @Autowired
    private IValidator<UserForm> registerValidator;
    @Autowired
    private UserService userService;

    @RequestMapping("/signup")
    public String signup() {
        return "/user/signup";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(UserForm userForm, @ModelAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY) String captchaExpected) {
        userForm.setCaptchaExpected(captchaExpected);
        try {
            registerValidator.validate(userForm);
        } catch (ValidateFailedException e) {
            logger.error(e.getMessage(), e);
            userForm.setErrorMessage(e.getMessage());
            return "/user/signup";
        }
        User user = new User();
        user.setUsername(userForm.getUsername());
        user.setPassword(userForm.getPassword());
        user.setEmail(userForm.getUsername());
        user.setCreatedTime(new Date());
        userService.save(user);
        return "user/signupSuccess";
    }
}
