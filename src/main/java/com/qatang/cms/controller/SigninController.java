package com.qatang.cms.controller;

import com.qatang.cms.constants.CommonConstants;
import com.qatang.cms.entity.user.User;
import com.qatang.cms.exception.validator.ValidateFailedException;
import com.qatang.cms.form.user.UserForm;
import com.qatang.cms.service.user.UserService;
import com.qatang.cms.validator.IValidator;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by qatang on 14-6-5.
 */
@Controller
@RequestMapping(value = "/signin")
public class SigninController extends BaseController {
    @Autowired
    private IValidator<UserForm> signinValidator;
    @Autowired
    private UserService userService;

   @RequestMapping(method = RequestMethod.GET)
    public String signinPage() {
        return "signin";
    }

    @RequestMapping(method = RequestMethod.POST)
    //public String fail(@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String userName,@RequestParam(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME) String error, Model model) {
    public String fail(HttpServletRequest req, Model model) {
        //model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, userName);
        String exceptionClassName = (String)req.getAttribute("shiroLoginFailure");
        String error = null;
        if(UnknownAccountException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if(IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if(exceptionClassName != null) {
            error = "其他错误：" + exceptionClassName;
        }
        model.addAttribute("error", error);
        return "signin";
    }

    /*@RequestMapping(value = "/signin", method = RequestMethod.POST)
    public String signin(UserForm userForm, @ModelAttribute(CommonConstants.KAPTCHA_SESSION_KEY) String captchaExpected, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        userForm.setCaptchaExpected(captchaExpected);
        try {
            signinValidator.validate(userForm);
        } catch (ValidateFailedException e) {
            logger.error(e.getMessage(), e);
            redirectAttributes.addFlashAttribute(ERROR_MESSAGE_KEY, e.getMessage());
            return "redirect:/signin";
        }

        User user = userService.getByUsername(userForm.getUsername());
        if (user == null || !user.getPassword().equals(DigestUtils.md5Hex(userForm.getPassword()))) {
            redirectAttributes.addFlashAttribute(ERROR_MESSAGE_KEY, "用户名或密码错误！");
            return "redirect:/signin";
        }
        user.setLastLoginTime(user.getLoginTime());
        user.setLoginTime(new Date());
        userService.update(user);
        request.getSession().setAttribute(CommonConstants.USER_SESSION_KEY, user);
        return "redirect:/dashboard";
    }*/
}
