package com.qatang.cms.controller.user;

import com.qatang.cms.controller.BaseController;
import com.qatang.cms.entity.user.User;
import com.qatang.cms.enums.Gender;
import com.qatang.cms.exception.validator.ValidateFailedException;
import com.qatang.cms.form.user.UserForm;
import com.qatang.cms.service.user.UserService;
import com.qatang.cms.validator.impl.user.CreateUserValidator;
import com.qatang.cms.validator.impl.user.QueryUserValidator;
import com.qatang.cms.validator.impl.user.UpdatePasswordValidator;
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

    protected final static String USER_ID_KEY = "id";
    protected final static String USER_KEY = "user";

    @Autowired
    private QueryUserValidator queryUserValidator;
    @Autowired
    private CreateUserValidator createUserValidator;
    @Autowired
    private UpdateUserValidator updateUserValidator;
    @Autowired
    private UpdatePasswordValidator updatePasswordValidator;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/query")
    public String query(UserForm userForm, ModelMap modelMap) {
        List<User> userList;
        if (userForm == null) {
            userList = userService.getList();
        } else {
            try {
                queryUserValidator.validate(userForm);
                userList = userService.getByCondition(userForm);
            } catch (ValidateFailedException e) {
                logger.error(e.getMessage(), e);
                modelMap.addAttribute(ERROR_MESSAGE_KEY, e.getMessage());
                modelMap.addAttribute(FORWARD_URL, "/user/query");
                return "failure";
            }
        }
        modelMap.addAttribute(userList);
        modelMap.addAttribute("genders", Gender.listAll());
        return "user/userList";
    }

    @RequestMapping(value = "/input")
    public String input(Long id, ModelMap modelMap) {
        if (id != null) {
            User user = userService.get(id);
            modelMap.addAttribute(USER_KEY, user);
        }
        return "user/userInput";
    }

    @RequestMapping(value = "/passwordInput")
    public String passwordInput(Long id, ModelMap modelMap) {
        if (id == null) {
            logger.error("修改用户密码，用户id为空");
            modelMap.addAttribute(ERROR_MESSAGE_KEY, "修改用户密码，用户id为空");
            modelMap.addAttribute(FORWARD_URL, "/user/query");
            return "failure";
        }
        modelMap.addAttribute(USER_ID_KEY, id);
        return "user/passwordInput";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(UserForm userForm, ModelMap modelMap) {
        try {
            createUserValidator.validate(userForm);
        } catch (ValidateFailedException e) {
            logger.error(e.getMessage(), e);
            modelMap.addAttribute(ERROR_MESSAGE_KEY, e.getMessage());
            modelMap.addAttribute(FORWARD_URL, "/user/query");
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
        if (!StringUtils.isEmpty(userForm.getQQ())) {
            user.setQQ(userForm.getQQ());
        }
        user.setCreatedTime(new Date());
        userService.save(user);
        return "success";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(User updateUser, ModelMap modelMap) {
        try {
            updateUserValidator.validate(updateUser);
        } catch (ValidateFailedException e) {
            logger.error(e.getMessage(), e);
            modelMap.addAttribute(ERROR_MESSAGE_KEY, e.getMessage());
            modelMap.addAttribute(USER_KEY, updateUser);
            return "/user/userInput";
        }
        User user = userService.get(updateUser.getId());
        user.setName(updateUser.getName());
        if (StringUtils.isNotEmpty(updateUser.getEmail())) {
            user.setEmail(updateUser.getEmail());
        }
        user.setMobile(updateUser.getMobile());
        user.setUpdatedTime(new Date());
        userService.update(user);
        modelMap.addAttribute(FORWARD_URL, "/user/query");
        return "success";
    }

    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    public String updatePassword(UserForm userForm, ModelMap modelMap) {
        try {
            updatePasswordValidator.validate(userForm);
        } catch (ValidateFailedException e) {
            logger.error(e.getMessage(), e);
            modelMap.addAttribute(USER_ID_KEY, userForm.getId());
            modelMap.addAttribute(ERROR_MESSAGE_KEY, e.getMessage());
            return "/user/passwordInput";
        }
        User user = userService.get(userForm.getId());
        user.setPassword(DigestUtils.md5Hex(userForm.getNewPassword()));
        userService.update(user);
        modelMap.addAttribute(FORWARD_URL, "/user/query");
        modelMap.addAttribute(SUCCESS_MESSAGE_KEY, "修改用户密码成功");
        return "success";
    }

    @RequestMapping(value = "/del")
    public String delete(Long id, ModelMap modelMap) {
        if (id == null) {
            logger.error("删除用户，id为空");
            modelMap.addAttribute(ERROR_MESSAGE_KEY, "删除用户，id为空");
            modelMap.addAttribute(FORWARD_URL, "/user/query");
            return "failure";
        }
        userService.delete(id);
        return "redirect:/user/query";
    }
}
