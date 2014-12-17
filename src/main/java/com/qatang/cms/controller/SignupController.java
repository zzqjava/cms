package com.qatang.cms.controller;

import com.qatang.cms.constants.CommonConstants;
import com.qatang.cms.entity.user.User;
import com.qatang.cms.enums.EnableDisableStatus;
import com.qatang.cms.exception.validator.ValidateFailedException;
import com.qatang.cms.form.user.UserForm;
import com.qatang.cms.service.user.UserService;
import com.qatang.cms.shiro.authentication.PasswordHelper;
import com.qatang.cms.validator.IValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

/**
 * Created by qatang on 14-6-12.
 */
@Controller
@SessionAttributes(CommonConstants.KAPTCHA_SESSION_KEY)
public class SignupController extends BaseController {
    @Autowired
    private IValidator<UserForm> registerValidator;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordHelper passwordHelper;

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signupPage() {
        return "/user/signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signup(UserForm userForm, @ModelAttribute(CommonConstants.KAPTCHA_SESSION_KEY) String captchaExpected, RedirectAttributes redirectAttributes) {
        userForm.setCaptchaExpected(captchaExpected);
        try {
            registerValidator.validate(userForm);
        } catch (ValidateFailedException e) {
            logger.error(e.getMessage(), e);
            redirectAttributes.addFlashAttribute(ERROR_MESSAGE_KEY, e.getMessage());
            return "redirect:/signup";
        }
        User user = new User();
        user.setUsername(userForm.getUsername());
        user.setPassword(userForm.getPassword());
        passwordHelper.encryptPassword(user);
        user.setEmail(userForm.getEmail());
        user.setCreatedTime(new Date());
        user.setValid(EnableDisableStatus.ENABLE);
        userService.save(user);
        return "user/signupSuccess";
    }
}
