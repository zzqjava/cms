package com.qatang.cms.controller.user;

import com.qatang.cms.constants.CommonConstants;
import com.qatang.cms.controller.BaseController;
import com.qatang.cms.entity.role.Role;
import com.qatang.cms.entity.user.User;
import com.qatang.cms.entity.user.UserRole;
import com.qatang.cms.enums.EnableDisableStatus;
import com.qatang.cms.enums.Gender;
import com.qatang.cms.exception.validator.ValidateFailedException;
import com.qatang.cms.form.PageInfo;
import com.qatang.cms.form.PageUtil;
import com.qatang.cms.form.user.UserForm;
import com.qatang.cms.service.role.RoleService;
import com.qatang.cms.service.user.UserRoleService;
import com.qatang.cms.service.user.UserService;
import com.qatang.cms.shiro.authentication.PasswordHelper;
import com.qatang.cms.validator.impl.user.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by JSH on 2014/6/26.
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    private QueryUserValidator queryUserValidator;
    @Autowired
    private CreateUserValidator createUserValidator;
    @Autowired
    private UpdateUserValidator updateUserValidator;
    @Autowired
    private ChangePasswordValidator changePasswordValidator;
    @Autowired
    private ResetPasswordValidator resetPasswordValidator;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PasswordHelper passwordHelper;

    @RequiresPermissions("sys:user:list")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listGet(ModelMap modelMap, HttpServletRequest request) {
        UserForm userForm;
        if (modelMap.containsKey(CommonConstants.QUERY_CONDITION_KEY)) {
            userForm = (UserForm) modelMap.get(CommonConstants.QUERY_CONDITION_KEY);
        } else {
            userForm = new UserForm();
        }
        pagination(userForm, modelMap);
        userForm.setPageString(PageUtil.getPageString(request, userForm.getPageInfo()));
        return "user/list";
    }

    @RequiresPermissions("sys:user:list")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String listPost(@ModelAttribute(CommonConstants.QUERY_CONDITION_KEY) UserForm userForm, ModelMap modelMap, HttpServletRequest request) {
        try {
            queryUserValidator.validate(userForm);
        } catch (ValidateFailedException e) {
            logger.error(e.getMessage(), e);
            modelMap.addAttribute(ERROR_MESSAGE_KEY, e.getMessage());
            modelMap.addAttribute(FORWARD_URL, "/user/list");
            return "failure";
        }
        pagination(userForm, modelMap);
        userForm.setPageString(PageUtil.getPageString(request, userForm.getPageInfo()));
        return "user/list";
    }

    @RequiresPermissions("sys:user:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createGet(@ModelAttribute UserForm userForm, ModelMap modelMap) {
        userForm.setGenderValue(String.valueOf(Gender.MALE.getValue()));
        userForm.setValidValue(String.valueOf(EnableDisableStatus.ENABLE.getValue()));
        modelMap.addAttribute(FORWARD_URL, "/user/list");
        return "user/create";
    }

    @RequiresPermissions("sys:user:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createPost(UserForm userForm, ModelMap modelMap) {
        try {
            createUserValidator.validate(userForm);
        } catch (ValidateFailedException e) {
            logger.error(e.getMessage(), e);
            modelMap.addAttribute(userForm);
            modelMap.addAttribute(ERROR_MESSAGE_KEY, e.getMessage());
            modelMap.addAttribute(FORWARD_URL, "/user/list");
            return "user/create";
        }
        List<Role> roleList = roleService.findDefaultRoles();
        if (roleList == null && roleList.isEmpty()) {
            logger.error("用户添加失败,默认角色为空");
            modelMap.addAttribute(ERROR_MESSAGE_KEY, "用户添加失败,默认角色为空");
            modelMap.addAttribute(FORWARD_URL, "/user/input");
            return "failure";
        }
        User user = new User();
        user.setUsername(userForm.getUsername());
        user.setPassword(userForm.getPassword());
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setGender(Gender.get(Integer.parseInt(userForm.getGenderValue())));
        user.setMobile(userForm.getMobile());
        user.setQQ(userForm.getQQ());
        user.setCreatedTime(new Date());
        user.setValid(EnableDisableStatus.get(Integer.parseInt(userForm.getValidValue())));
        passwordHelper.encryptPassword(user);
        user = userService.save(user);
        Long userId = user.getId();
        List<UserRole> userRoleList = new ArrayList<>();
        for (Role role : roleList) {
            UserRole userRole = new UserRole(userId, role.getId());
            userRoleList.add(userRole);
        }
        userRoleService.save(userRoleList);
        modelMap.addAttribute(FORWARD_URL, "/user/list");
        return "success";
    }

    @RequiresPermissions("sys:user:update")
    @RequestMapping(value = "/update/{userId}", method = RequestMethod.GET)
    public String updateGet(@PathVariable String userId, ModelMap modelMap) {
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
        return "user/update";
    }

    @RequiresPermissions("sys:user:update")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updatePost(UserForm userForm, ModelMap modelMap) {
        try {
            updateUserValidator.validate(userForm);
        } catch (ValidateFailedException e) {
            logger.error(e.getMessage(), e);
            modelMap.addAttribute(ERROR_MESSAGE_KEY, e.getMessage());
            modelMap.addAttribute(FORWARD_URL, "/user/list");
            return "user/create";
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

    @RequiresPermissions("sys:user:switchStatus")
    @RequestMapping(value = "/switchStatus/{id}", method = RequestMethod.POST)
    public @ResponseBody
    String switchStatus(@PathVariable String id) {
        Long userId;
        try {
            userId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            logger.error("禁用/启用用户，用户id不合法");
            return null;
        }
        User user = userService.get(userId);
        EnableDisableStatus enableDisableStatus = user.getValid();
        enableDisableStatus = enableDisableStatus.getValue() == EnableDisableStatus.ENABLE.getValue() ? EnableDisableStatus.DISABLE : EnableDisableStatus.ENABLE;
        user.setValid(enableDisableStatus);
        userService.update(user);
        String value = String.valueOf(enableDisableStatus.getValue());
        return value;
    }

    @RequiresPermissions("sys:user:detail")
    @RequestMapping(value = "/detail/{userId}", method = RequestMethod.GET)
    public String detail(@PathVariable String userId, ModelMap modelMap) {
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
        List<UserRole> userRoleList = userRoleService.findUserRolesByUserId(id);
        Set<Long> ids = new HashSet<>();
        for (UserRole userRole : userRoleList) {
            ids.add(userRole.getRoleId());
        }
        List<Role> roleList = roleService.findByIds(ids);
        modelMap.addAttribute(user);
        modelMap.addAttribute(roleList);
        modelMap.addAttribute(FORWARD_URL, "/user/list");
        return "user/detail";
    }

    @RequiresPermissions("sys:user:changePassword")
    @RequestMapping(value = "/password/change", method = RequestMethod.GET)
    public String changePasswordGet(UserForm userForm, ModelMap modelMap) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        String userId = String.valueOf(user.getId());
        userForm.setId(userId);
        modelMap.addAttribute(userForm);
        modelMap.addAttribute(FORWARD_URL, "/user/list");
        return "user/passwordChange";
    }

    @RequiresPermissions("sys:user:changePassword")
    @RequestMapping(value = "/password/change", method = RequestMethod.POST)
    public String changePasswordPost(UserForm userForm, ModelMap modelMap) {
        try {
            changePasswordValidator.validate(userForm);
        } catch (ValidateFailedException e) {
            logger.error(e.getMessage(), e);
            modelMap.addAttribute(ERROR_MESSAGE_KEY, e.getMessage());
            modelMap.addAttribute(FORWARD_URL, "/user/list");
            return "user/passwordChange";
        }
        Long id = Long.parseLong(userForm.getId());
        User user = userService.get(id);
        user.setPassword(userForm.getNewPassword());
        passwordHelper.encryptPassword(user);
        userService.update(user);
        modelMap.addAttribute(FORWARD_URL, "/user/list");
        modelMap.addAttribute(SUCCESS_MESSAGE_KEY, "修改用户密码成功");
        return "success";
    }

    @RequiresPermissions("sys:user:resetPassword")
    @RequestMapping(value = "/password/reset/{id}", method = RequestMethod.GET)
    public String resetPasswordGet(@PathVariable String id, ModelMap modelMap) {
        UserForm userForm = new UserForm();
        userForm.setId(id);
        modelMap.addAttribute(userForm);
        modelMap.addAttribute(FORWARD_URL, "/user/list");
        return "user/passwordReset";
    }

    @RequiresPermissions("sys:user:resetPassword")
    @RequestMapping(value = "/password/reset", method = RequestMethod.POST)
    public String resetPasswordPost(UserForm userForm, ModelMap modelMap) {
        try {
            resetPasswordValidator.validate(userForm);
        } catch (ValidateFailedException e) {
            logger.error(e.getMessage(), e);
            modelMap.addAttribute(ERROR_MESSAGE_KEY, e.getMessage());
            modelMap.addAttribute(FORWARD_URL, "/user/list");
            return "user/passwordReset";
        }
        Long id = Long.parseLong(userForm.getId());
        User user = userService.get(id);
        user.setPassword(userForm.getNewPassword());
        passwordHelper.encryptPassword(user);
        userService.update(user);
        modelMap.addAttribute(FORWARD_URL, "/user/list");
        modelMap.addAttribute(SUCCESS_MESSAGE_KEY, "重置用户密码成功");
        return "success";
    }

    @RequiresPermissions("sys:user:forgetPassword")
    @RequestMapping(value = "/password/forget", method = RequestMethod.GET)
    public String forgetPasswordGet(ModelMap modelMap) {
        modelMap.addAttribute(FORWARD_URL, "/signin");
        return "user/passwordForget";
    }

    private void pagination(UserForm userForm, ModelMap modelMap) {
        Page<User> userPage = userService.getAll(userForm);
        if (userPage.getContent() != null) {
            List<User> userList = userPage.getContent();
            modelMap.addAttribute(userList);
        }
        PageInfo pageInfo = userForm.getPageInfo();
        pageInfo.setTotalPages(userPage.getTotalPages());
        pageInfo.setCount((int) userPage.getTotalElements());
        userForm.setPageInfo(pageInfo);
        modelMap.addAttribute(userForm);
    }

    @ModelAttribute("genderList")
    public List<Gender> getGenderList() {
        List<Gender> genderList = Gender.list();
        return genderList;
    }

    @ModelAttribute("queryGenderList")
    public List<Gender> getQueryGenderList() {
        List<Gender> queryGenderList = Gender.listAll();
        return queryGenderList;
    }

    @ModelAttribute("enableDisableStatusList")
    public List<EnableDisableStatus> getEnableDisableStatusList() {
        List<EnableDisableStatus> enableDisableStatusList = EnableDisableStatus.list();
        return enableDisableStatusList;
    }

    @ModelAttribute("queryEnableDisableStatusList")
    public List<EnableDisableStatus> getQueryEnableDisableStatusList() {
        List<EnableDisableStatus> queryEnableDisableStatusList = EnableDisableStatus.listAll();
        return queryEnableDisableStatusList;
    }
}
