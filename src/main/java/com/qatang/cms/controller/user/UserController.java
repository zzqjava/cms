package com.qatang.cms.controller.user;

import com.qatang.cms.controller.BaseController;
import com.qatang.cms.entity.user.User;
import com.qatang.cms.enums.EnableDisableStatus;
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
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
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

    private final static String USER_ID_KEY = "id";
    private final static String USER_KEY = "user";
    private final static String USERFORM_KEY = "userForm";

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

    @RequestMapping(value = "/list")
    public String list(UserForm userForm, ModelMap modelMap) {
        List<User> userList = null;
        try {
            queryUserValidator.validate(userForm);
            Page<User> page = userService.getAll(userForm);
            userList = page.getContent();
        } catch (ValidateFailedException e) {
            logger.error(e.getMessage(), e);
            modelMap.addAttribute(ERROR_MESSAGE_KEY, e.getMessage());
            modelMap.addAttribute(FORWARD_URL, "/user/list");
            return "failure";
        }
        modelMap.addAttribute(userList);
        return "user/userList";
    }

    @RequestMapping(value = "/input")
    public String input() {
        return "user/userInput";
    }

    @RequestMapping(value = "/input/{id}")
    public String input(@PathVariable Long id, ModelMap modelMap) {
        if (id != null) {
            User user = userService.get(id);
            UserForm userForm = new UserForm();
            userForm.setId(user.getId());
            if (StringUtils.isEmpty(user.getUsername())) {
                logger.error("用户名为空");
                modelMap.addAttribute(ERROR_MESSAGE_KEY, "用户名为空");
                modelMap.addAttribute(FORWARD_URL, "/user/list");
                return "failure";
            }
            userForm.setUsername(user.getUsername());
            if (StringUtils.isNotEmpty(user.getName())) {
                userForm.setName(user.getName());
            }
            if (StringUtils.isEmpty(user.getEmail())) {
                logger.error("用户邮箱为空");
                modelMap.addAttribute(ERROR_MESSAGE_KEY, "用户邮箱为空");
                modelMap.addAttribute(FORWARD_URL, "/user/list");
                return "failure";
            }
            userForm.setEmail(user.getEmail());
            if (StringUtils.isNotEmpty(user.getMobile())) {
                userForm.setMobile(user.getMobile());
            }
            if (user.getGender() == null) {
                logger.error("用户性别为空");
                modelMap.addAttribute(ERROR_MESSAGE_KEY, "用户性别为空");
                modelMap.addAttribute(FORWARD_URL, "/user/list");
                return "failure";
            }
            userForm.setGenderValue(String.valueOf(user.getGender().getValue()));
            if (StringUtils.isNotEmpty(user.getQQ())) {
                userForm.setQQ(user.getQQ());
            }
            if (user.getValid() == null) {
                logger.error("用户是否有效状态为空");
                modelMap.addAttribute(ERROR_MESSAGE_KEY, "用户是否有效状态为空");
                modelMap.addAttribute(FORWARD_URL, "/user/list");
                return "failure";
            }
            userForm.setValidValue(String.valueOf(user.getValid().getValue()));
            modelMap.addAttribute(USERFORM_KEY, userForm);
        }
        return "user/userInput";
    }

    @RequestMapping(value = "/forbidden/{id}")
    public String forbidden(@PathVariable Long id, ModelMap modelMap) {
        if (id == null) {
            logger.error("禁用用户，用户id为空");
            modelMap.addAttribute(ERROR_MESSAGE_KEY, "禁用用户，用户id为空");
            modelMap.addAttribute(FORWARD_URL, "/user/list");
            return "failure";
        }
        User user = userService.get(id);
        user.setValid(EnableDisableStatus.DISABLE);
        userService.update(user);
        return "redirect:/user/list";
    }

    @RequestMapping(value = "/passwordInput/{id}")
    public String passwordInput(@PathVariable Long id, ModelMap modelMap) {
        if (id == null) {
            logger.error("修改用户密码，用户id为空");
            modelMap.addAttribute(ERROR_MESSAGE_KEY, "修改用户密码，用户id为空");
            modelMap.addAttribute(FORWARD_URL, "/user/list");
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
            modelMap.addAttribute(USERFORM_KEY, userForm);
            return "/user/userInput";
        }
        User user = new User();
        user.setUsername(userForm.getUsername());
        user.setPassword(DigestUtils.md5Hex(userForm.getPassword()));
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setGender(Gender.get(Integer.parseInt(userForm.getGenderValue())));
        if (!StringUtils.isEmpty(userForm.getMobile())) {
            user.setMobile(userForm.getMobile());
        }
        if (!StringUtils.isEmpty(userForm.getQQ())) {
            user.setQQ(userForm.getQQ());
        }
        user.setCreatedTime(new Date());
        user.setValid(EnableDisableStatus.get(Integer.parseInt(userForm.getValidValue())));
        userService.save(user);
        modelMap.addAttribute(FORWARD_URL, "/user/list");
        return "success";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(UserForm userForm, ModelMap modelMap) {
        try {
            updateUserValidator.validate(userForm);
        } catch (ValidateFailedException e) {
            logger.error(e.getMessage(), e);
            modelMap.addAttribute(ERROR_MESSAGE_KEY, e.getMessage());
            modelMap.addAttribute(USERFORM_KEY, userForm);
            return "/user/userInput";
        }
        User user = userService.get(userForm.getId());
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setGender(Gender.get(Integer.parseInt(userForm.getGenderValue())));
        if (StringUtils.isNotEmpty(userForm.getMobile())) {
            user.setMobile(userForm.getMobile());
        }
        if (StringUtils.isNotEmpty(userForm.getQQ())) {
            user.setQQ(userForm.getQQ());
        }
        user.setUpdatedTime(new Date());
        user.setValid(EnableDisableStatus.get(Integer.parseInt(userForm.getValidValue())));
        userService.update(user);
        modelMap.addAttribute(FORWARD_URL, "/user/list");
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
        modelMap.addAttribute(FORWARD_URL, "/user/list");
        modelMap.addAttribute(SUCCESS_MESSAGE_KEY, "修改用户密码成功");
        return "success";
    }

    @RequestMapping(value = "/del/{id}")
    public String delete(@PathVariable Long id, ModelMap modelMap) {
        if (id == null) {
            logger.error("删除用户，id为空");
            modelMap.addAttribute(ERROR_MESSAGE_KEY, "删除用户，id为空");
            modelMap.addAttribute(FORWARD_URL, "/user/list");
            return "failure";
        }
        userService.delete(id);
        return "redirect:/user/list";
    }

    @RequestMapping(value = "/view/{id}")
    public String view(@PathVariable Long id, ModelMap modelMap) {
        if (id == null) {
            logger.error("查看用户，id为空");
            modelMap.addAttribute(ERROR_MESSAGE_KEY, "查看用户，id为空");
            modelMap.addAttribute(FORWARD_URL, "/user/list");
            return "failure";
        }
        User user = userService.get(id);
        modelMap.addAttribute(USER_KEY, user);
        modelMap.addAttribute(FORWARD_URL, "/user/list");
        return "user/userView";
    }
}
