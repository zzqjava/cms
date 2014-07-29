package com.qatang.cms.controller.user;

import com.qatang.cms.constants.CommonConstants;
import com.qatang.cms.controller.BaseController;
import com.qatang.cms.entity.user.User;
import com.qatang.cms.enums.EnableDisableStatus;
import com.qatang.cms.enums.Gender;
import com.qatang.cms.exception.validator.ValidateFailedException;
import com.qatang.cms.form.PageInfo;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by JSH on 2014/6/26.
 */
@Controller
@SessionAttributes(CommonConstants.QUERY_CONDITION_KEY)
@RequestMapping("/user")
public class UserController extends BaseController {

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

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(ModelMap modelMap, HttpServletRequest request) {
        UserForm userForm;
        if (modelMap.containsKey(CommonConstants.QUERY_CONDITION_KEY)) {
            userForm = (UserForm) modelMap.get(CommonConstants.QUERY_CONDITION_KEY);
        } else {
            userForm = new UserForm();
        }
        pagination(userForm, modelMap, request);
        return "user/userList";
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String list(@ModelAttribute(CommonConstants.QUERY_CONDITION_KEY) UserForm userForm, ModelMap modelMap, HttpServletRequest request) {
        try {
            queryUserValidator.validate(userForm);
        } catch (ValidateFailedException e) {
            logger.error(e.getMessage(), e);
            modelMap.addAttribute(ERROR_MESSAGE_KEY, e.getMessage());
            modelMap.addAttribute(FORWARD_URL, "/user/list");
            return "failure";
        }
        pagination(userForm, modelMap, request);
        return "user/userList";
    }

    public void pagination(UserForm userForm, ModelMap modelMap, HttpServletRequest request) {
        Page<User> page = userService.getAll(userForm);
        if (page.getContent() != null) {
            List<User> userList = page.getContent();
            modelMap.addAttribute(userList);
        }
        PageInfo pageInfo = userForm.getPageInfo();
        pageInfo.setTotalPages(page.getTotalPages());
        modelMap.addAttribute(userForm);
        request.getSession().setAttribute(CommonConstants.QUERY_CONDITION_KEY, userForm);
    }

    @RequestMapping(value = "/input", method = RequestMethod.GET)
    public String input(ModelMap modelMap) {
        modelMap.addAttribute(FORWARD_URL, "/user/list");
        return "user/userInput";
    }

    @RequestMapping(value = "/input/{userId}", method = RequestMethod.GET)
    public String input(@PathVariable String userId, ModelMap modelMap) {
        Long id;
        try {
            id = Long.parseLong(userId);
        } catch (NumberFormatException e) {
            logger.error("修改用户，用户id不合法");
            modelMap.addAttribute(ERROR_MESSAGE_KEY, "修改用户，用户id不合法");
            modelMap.addAttribute(FORWARD_URL, "/user/list");
            return "failure";
        }
        User user = userService.get(id);

        if (StringUtils.isEmpty(user.getUsername())) {
            logger.error("用户名为空");
            modelMap.addAttribute(ERROR_MESSAGE_KEY, "用户名为空");
            modelMap.addAttribute(FORWARD_URL, "/user/list");
            return "failure";
        }
        if (StringUtils.isEmpty(user.getName())) {
            logger.error("用户姓名为空");
            modelMap.addAttribute(ERROR_MESSAGE_KEY, "用户姓名为空");
            modelMap.addAttribute(FORWARD_URL, "/user/list");
            return "failure";
        }
        if (StringUtils.isEmpty(user.getEmail())) {
            logger.error("用户邮箱为空");
            modelMap.addAttribute(ERROR_MESSAGE_KEY, "用户邮箱为空");
            modelMap.addAttribute(FORWARD_URL, "/user/list");
            return "failure";
        }
        if (user.getGender() == null) {
            logger.error("用户性别为空");
            modelMap.addAttribute(ERROR_MESSAGE_KEY, "用户性别为空");
            modelMap.addAttribute(FORWARD_URL, "/user/list");
            return "failure";
        }
        if (user.getValid() == null) {
            logger.error("用户是否有效状态为空");
            modelMap.addAttribute(ERROR_MESSAGE_KEY, "用户是否有效状态为空");
            modelMap.addAttribute(FORWARD_URL, "/user/list");
            return "failure";
        }
        UserForm userForm = new UserForm();
        userForm.setId(userId);
        userForm.setUsername(user.getUsername());
        userForm.setName(user.getName());
        userForm.setEmail(user.getEmail());
        userForm.setMobile(user.getMobile());
        userForm.setGenderValue(String.valueOf(user.getGender().getValue()));
        userForm.setValidValue(String.valueOf(user.getValid().getValue()));
        userForm.setQQ(user.getQQ());

        modelMap.addAttribute(userForm);
        modelMap.addAttribute(FORWARD_URL, "/user/list");
        return "user/userInput";
    }

    @RequestMapping(value = "/disable/{userId}", method = RequestMethod.GET)
    public String disable(@PathVariable String userId, ModelMap modelMap) {
        Long id;
        try {
            id = Long.parseLong(userId);
        } catch (NumberFormatException e) {
            logger.error("禁用用户，用户id不合法");
            modelMap.addAttribute(ERROR_MESSAGE_KEY, "禁用用户，用户id不合法");
            modelMap.addAttribute(FORWARD_URL, "/user/list");
            return "failure";
        }
        User user = userService.get(id);
        user.setValid(EnableDisableStatus.DISABLE);
        userService.update(user);
        return "redirect:/user/list";
    }

    @RequestMapping(value = "/enable/{userId}", method = RequestMethod.GET)
    public String enable(@PathVariable String userId, ModelMap modelMap) {
        Long id;
        try {
            id = Long.parseLong(userId);
        } catch (NumberFormatException e) {
            logger.error("启用用户，用户id不合法");
            modelMap.addAttribute(ERROR_MESSAGE_KEY, "启用用户，用户id不合法");
            modelMap.addAttribute(FORWARD_URL, "/user/list");
            return "failure";
        }
        User user = userService.get(id);
        user.setValid(EnableDisableStatus.ENABLE);
        userService.update(user);
        return "redirect:/user/list";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(UserForm userForm, ModelMap modelMap) {
        try {
            createUserValidator.validate(userForm);
        } catch (ValidateFailedException e) {
            logger.error(e.getMessage(), e);
            modelMap.addAttribute(userForm);
            modelMap.addAttribute(ERROR_MESSAGE_KEY, e.getMessage());
            modelMap.addAttribute(FORWARD_URL, "/user/list");
            return "user/userInput";
        }
        User user = new User();
        user.setUsername(userForm.getUsername());
        user.setPassword(DigestUtils.md5Hex(userForm.getPassword()));
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setGender(Gender.get(Integer.parseInt(userForm.getGenderValue())));
        user.setMobile(userForm.getMobile());
        user.setQQ(userForm.getQQ());
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
            modelMap.addAttribute(FORWARD_URL, "/user/list");
            return "user/userInput";
        }
        Long id = Long.parseLong(userForm.getId());
        User user = userService.get(id);
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setGender(Gender.get(Integer.parseInt(userForm.getGenderValue())));
        user.setMobile(userForm.getMobile());
        user.setQQ(userForm.getQQ());
        user.setUpdatedTime(new Date());
        user.setValid(EnableDisableStatus.get(Integer.parseInt(userForm.getValidValue())));
        userService.update(user);
        modelMap.addAttribute(FORWARD_URL, "/user/list");
        return "success";
    }

    @RequestMapping(value = "/del/{userId}", method = RequestMethod.GET)
    public String delete(@PathVariable String userId, ModelMap modelMap) {
        Long id;
        try {
            id = Long.parseLong(userId);
        } catch (NumberFormatException e) {
            logger.error("启用用户，用户id不合法");
            modelMap.addAttribute(ERROR_MESSAGE_KEY, "启用用户，用户id不合法");
            modelMap.addAttribute(FORWARD_URL, "/user/list");
            return "failure";
        }
        userService.delete(id);
        return "redirect:/user/list";
    }

    @RequestMapping(value = "/view/{userId}", method = RequestMethod.GET)
    public String view(@PathVariable String userId, ModelMap modelMap) {
        Long id;
        try {
            id = Long.parseLong(userId);
        } catch (NumberFormatException e) {
            logger.error("启用用户，用户id不合法");
            modelMap.addAttribute(ERROR_MESSAGE_KEY, "启用用户，用户id不合法");
            modelMap.addAttribute(FORWARD_URL, "/user/list");
            return "failure";
        }
        User user = userService.get(id);
        modelMap.addAttribute(user);
        modelMap.addAttribute(FORWARD_URL, "/user/list");
        return "user/userView";
    }

    @RequestMapping(value = "/password/input/{id}", method = RequestMethod.GET)
    public String inputPassword(@PathVariable String id, ModelMap modelMap) {
        UserForm userForm = new UserForm();
        userForm.setId(id);
        modelMap.addAttribute(userForm);
        modelMap.addAttribute(FORWARD_URL, "/user/list");
        return "user/passwordInput";
    }

    @RequestMapping(value = "/password/update", method = RequestMethod.POST)
    public String updatePassword(UserForm userForm, ModelMap modelMap) {
        try {
            updatePasswordValidator.validate(userForm);
        } catch (ValidateFailedException e) {
            logger.error(e.getMessage(), e);
            modelMap.addAttribute(ERROR_MESSAGE_KEY, e.getMessage());
            modelMap.addAttribute(FORWARD_URL, "/user/list");
            return "user/passwordInput";
        }
        Long id = Long.parseLong(userForm.getId());
        User user = userService.get(id);
        user.setPassword(DigestUtils.md5Hex(userForm.getNewPassword()));
        userService.update(user);
        modelMap.addAttribute(FORWARD_URL, "/user/list");
        modelMap.addAttribute(SUCCESS_MESSAGE_KEY, "修改用户密码成功");
        return "success";
    }

    @RequestMapping(value = "/password/forget", method = RequestMethod.GET)
    public String forgetPassword(ModelMap modelMap) {
        modelMap.addAttribute(FORWARD_URL, "/signin");
        return "user/passwordReset";
    }

    @RequestMapping(value = "/password/reset", method = RequestMethod.GET)
    public String resetPassword() {
        return "success";
    }

    @ModelAttribute("genderList")
    public List<Gender> genderList() {
        List<Gender> genderList = Gender.list();
        return genderList;
    }

    @ModelAttribute("genderListAll")
    public List<Gender> genderListAll() {
        List<Gender> genderListAll = Gender.listAll();
        return genderListAll;
    }

    @ModelAttribute("validList")
    public List<EnableDisableStatus> enableDisableStatusList() {
        List<EnableDisableStatus> enableDisableStatusList = EnableDisableStatus.list();
        return enableDisableStatusList;
    }

    @ModelAttribute("validListAll")
    public List<EnableDisableStatus> enableDisableStatusListAll() {
        List<EnableDisableStatus> enableDisableStatusListAll = EnableDisableStatus.listAll();
        return enableDisableStatusListAll;
    }
}
