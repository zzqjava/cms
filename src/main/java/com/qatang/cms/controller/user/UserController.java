package com.qatang.cms.controller.user;

import com.qatang.cms.entity.user.User;
import com.qatang.cms.enums.Gender;
import com.qatang.cms.exception.validator.ValidateFailedException;
import com.qatang.cms.form.user.UserForm;
import com.qatang.cms.service.user.UserService;
import com.qatang.cms.validator.IValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

/**
 * Created by qatang on 14-6-12.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IValidator<UserForm> userValidator;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(UserForm userForm) {
        try {
            userValidator.validate(userForm);
        } catch (ValidateFailedException e) {
            logger.error(e.getMessage(), e);
            return "input";
        }

        User user = new User();
        user.setUsername(userForm.getUsername());
        user.setPassword(userForm.getPassword());
        user.setName(userForm.getName());
        user.setGender(Gender.get(Integer.valueOf(userForm.getGenderValue())));
        user.setCreatedTime(new Date());
        userService.save(user);
        return "success";
    }
}
