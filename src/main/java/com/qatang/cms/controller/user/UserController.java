package com.qatang.cms.controller.user;

import com.qatang.cms.controller.BaseController;
import com.qatang.cms.entity.user.User;
import com.qatang.cms.enums.Gender;
import com.qatang.cms.exception.validator.ValidateFailedException;
import com.qatang.cms.form.user.UserForm;
import com.qatang.cms.service.user.UserService;
import com.qatang.cms.validator.impl.user.CreateUserValidator;
import com.qatang.cms.validator.impl.user.UpdateUserValidator;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.List;

/**
 * Created by JSH on 2014/6/26.
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    private CreateUserValidator createUserValidator;
    @Autowired
    private UpdateUserValidator updateUserValidator;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/list")
    public String list(ModelMap modelMap) {
        List<User> userList = userService.getList();
        modelMap.addAttribute(userList);
        return "user/userList";
    }

    @RequestMapping(value = "/input")
    public String input(Long id, ModelMap modelMap) {
        if (id != null) {
            User user = userService.get(id);
            modelMap.addAttribute("user", user);
        }
        return "user/userInput";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(UserForm userForm, ModelMap modelMap) {
        try {
            createUserValidator.validate(userForm);
        } catch (ValidateFailedException e) {
            logger.error(e.getMessage(), e);
            modelMap.addAttribute(ERROR_MESSAGE_KEY, e.getMessage());
            return "failure";
        }
        User user = new User();
        user.setUsername(userForm.getUsername());
        user.setPassword(DigestUtils.md5Hex(userForm.getPassword()));
        user.setName(userForm.getName());
        if (!StringUtils.isEmpty(userForm.getEmail())) {
            user.setEmail(userForm.getEmail());
        } else {
            user.setEmail(userForm.getUsername());
        }
        if (!StringUtils.isEmpty(userForm.getMobile())) {
            user.setMobile(userForm.getMobile());
        }
        user.setCreatedTime(new Date());
        userService.save(user);
        return "success";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Long id, UserForm userForm, ModelMap modelMap) {
        if (id == null) {
            logger.error("更新用户，id为空");
            modelMap.addAttribute(ERROR_MESSAGE_KEY, "更新用户，id为空");
            return "failure";
        }
        User user = userService.get(id);
        if (user == null) {
            logger.error("所要更新的用户不存在");
            modelMap.addAttribute(ERROR_MESSAGE_KEY, "所要更新的用户不存在");
            return "failure";
        }
        try {
            updateUserValidator.validate(userForm);
        } catch (ValidateFailedException e) {
            logger.error(e.getMessage(), e);
            modelMap.addAttribute(ERROR_MESSAGE_KEY, e.getMessage());
            return "failure";
        }
        user.setUsername(userForm.getUsername());
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setMobile(userForm.getMobile());
        user.setGender(Gender.get(Integer.parseInt(userForm.getGenderValue())));
        user.setUpdatedTime(new Date());
        userService.save(user);
        return "success";
    }

    @RequestMapping(value = "/del")
    public String delete(Long id, ModelMap modelMap) {
        if (id == null) {
            logger.error("删除用户，id为空");
            modelMap.addAttribute(ERROR_MESSAGE_KEY, "删除用户，id为空");
            return "failure";
        }
        userService.delete(id);
        return "redirect:/user/list";
    }
}
