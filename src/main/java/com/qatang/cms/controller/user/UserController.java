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
import com.qatang.cms.form.user.UserForm;
import com.qatang.cms.service.role.RoleService;
import com.qatang.cms.service.user.UserService;
import com.qatang.cms.shiro.authentication.PasswordHelper;
import com.qatang.cms.validator.impl.user.CreateUserValidator;
import com.qatang.cms.validator.impl.user.QueryUserValidator;
import com.qatang.cms.validator.impl.user.UpdatePasswordValidator;
import com.qatang.cms.validator.impl.user.UpdateUserValidator;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

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
    @Autowired
    private RoleService roleService;
    @Autowired
    private PasswordHelper passwordHelper;

    @RequiresPermissions("sys:user:list")
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

    @RequiresPermissions("sys:user:list")
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

    private void pagination(UserForm userForm, ModelMap modelMap, HttpServletRequest request) {
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

    @RequiresPermissions("sys:user:input")
    @RequestMapping(value = "/input", method = RequestMethod.GET)
    public String input(ModelMap modelMap) {

        modelMap.addAttribute(FORWARD_URL, "/user/list");
        return "user/userInput";
    }

    @RequiresPermissions("sys:user:input")
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

    @RequiresPermissions("sys:user:validate")
    @RequestMapping(value = "validate/{id}", method = RequestMethod.GET)
    public String validate(@PathVariable String id, RedirectAttributes redirectAttributes) {
        Long userId;
        try {
            userId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            logger.error("禁用/启用用户，用户id不合法");
            redirectAttributes.addFlashAttribute(ERROR_MESSAGE_KEY, "禁用用户，用户id不合法");
            redirectAttributes.addFlashAttribute(FORWARD_URL, "/user/list");
            return "failure";
        }
        User user = userService.get(userId);
        EnableDisableStatus enableDisableStatus = user.getValid();
        enableDisableStatus = enableDisableStatus.getValue() == EnableDisableStatus.ENABLE.getValue() ? EnableDisableStatus.DISABLE : EnableDisableStatus.ENABLE;
        user.setValid(enableDisableStatus);
        userService.update(user);
        return "redirect:/user/list";
    }

    @RequiresPermissions("sys:user:create")
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
        List<Long> roleIdList = userForm.getRoleIdList();
        List<UserRole> userRoleList = new ArrayList<>();
        for (Long roleId : roleIdList) {
            UserRole userRole = new UserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(roleId);
            userRoleList.add(userRole);
        }
        userService.save(userRoleList);
        modelMap.addAttribute(FORWARD_URL, "/user/list");
        return "success";
    }

    @RequiresPermissions("sys:user:update")
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
        List<Long> roleIdList = userForm.getRoleIdList();
        List<Role> roleList = userService.getByUserId(id);
        Map<Long, Role> roleMap = new HashMap<>();
        for (Role role : roleList) {
            roleMap.put(role.getId(), role);
        }
        List<UserRole> deleteList = new ArrayList<>();
        List<UserRole> saveList = new ArrayList<>();
        for (Long roleId : roleIdList) {
            if (!roleMap.containsKey(roleId)) {
                UserRole userRole = new UserRole();
                userRole.setRoleId(roleId);
                userRole.setUserId(id);
                saveList.add(userRole);
            }
        }
        userService.save(saveList);
        for (Long roleId : roleMap.keySet()) {
            if (!roleIdList.contains(roleId)) {
                UserRole userRole = userService.findByUserIdAndRoleId(id, roleId);
                deleteList.add(userRole);
            }
        }
        userService.delete(deleteList);
        modelMap.addAttribute(FORWARD_URL, "/user/list");
        return "success";
    }

    @RequiresPermissions("sys:user:del")
    @RequestMapping(value = "/del/{userId}", method = RequestMethod.GET)
    public String delete(@PathVariable String userId, RedirectAttributes redirectAttributes) {
        Long id;
        try {
            id = Long.parseLong(userId);
        } catch (NumberFormatException e) {
            logger.error("启用用户，用户id不合法");
            redirectAttributes.addFlashAttribute(ERROR_MESSAGE_KEY, "启用用户，用户id不合法");
            redirectAttributes.addFlashAttribute(FORWARD_URL, "/user/list");
            return "failure";
        }
        userService.delete(id);
        return "redirect:/user/list";
    }

    @RequiresPermissions("sys:user:view")
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

    @RequiresPermissions("sys:user:inputPassword")
    @RequestMapping(value = "/password/input/{id}", method = RequestMethod.GET)
    public String inputPassword(@PathVariable String id, ModelMap modelMap) {
        UserForm userForm = new UserForm();
        userForm.setId(id);
        modelMap.addAttribute(userForm);
        modelMap.addAttribute(FORWARD_URL, "/user/list");
        return "user/passwordInput";
    }

    @RequiresPermissions("sys:user:updatePassword")
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
        user.setPassword(userForm.getNewPassword());
        passwordHelper.encryptPassword(user);
        userService.update(user);
        modelMap.addAttribute(FORWARD_URL, "/user/list");
        modelMap.addAttribute(SUCCESS_MESSAGE_KEY, "修改用户密码成功");
        return "success";
    }

    @RequiresPermissions("sys:user:forgetPassword")
    @RequestMapping(value = "/password/forget", method = RequestMethod.GET)
    public String forgetPassword(ModelMap modelMap) {
        modelMap.addAttribute(FORWARD_URL, "/signin");
        return "user/passwordReset";
    }

    @RequiresPermissions("sys:user:resetPassword")
    @RequestMapping(value = "/password/reset", method = RequestMethod.GET)
    public String resetPassword() {
        return "success";
    }

    @RequiresPermissions("sys:user:ajaxRoles")
    @RequestMapping(value = "/ajax/roles", method = RequestMethod.POST)
    public void ajaxRoles(Long id, HttpServletResponse response) {
        try {
            response.setContentType("text/html; charset=utf-8");
            PrintWriter printWriter = response.getWriter();
            List<Role> allRoles = roleService.findAll();
            StringBuffer stringBuffer = new StringBuffer();
            if (id == null) {
                int index = 0;
                for (Role role : allRoles) {
                    stringBuffer.append("<input type=\"checkbox\" name=\"roleIdList[").append(index ++).append("]\" value=\"").append(role.getId()).append("\">").append("&nbsp;").append(role.getName()).append("&nbsp;");
                }
            } else {
                List<Role> roles = userService.getByUserId(id);
                Map<Long, String> roleIdRoleNameMap = new HashMap<>();
                for (Role role : roles) {
                    roleIdRoleNameMap.put(role.getId(), role.getName());
                }
                for (Role role : allRoles) {
                    int index = 0;
                    if (roleIdRoleNameMap.containsKey(role.getId())) {
                        stringBuffer.append("<input type=\"checkbox\" checked=\"checked\" name=\"roleIdList[").append(index ++).append("]\" value=\"").append(role.getId()).append("\">").append("&nbsp;").append(role.getName()).append("&nbsp;");
                    } else {
                        stringBuffer.append("<input type=\"checkbox\" name=\"roleIdList[").append(index ++).append("]\" value=\"\").append(role.getId()).append(\">").append("&nbsp;").append(role.getName()).append("&nbsp;");
                    }
                }
            }
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("success", true);
//            jsonObject.put("roles", stringBuffer.toString());
//            printWriter.print(jsonObject.toString());
            printWriter.write(stringBuffer.toString());
            printWriter.flush();
            printWriter.close();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
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
