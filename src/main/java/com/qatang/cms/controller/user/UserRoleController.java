package com.qatang.cms.controller.user;

import com.qatang.cms.constants.CommonConstants;
import com.qatang.cms.controller.BaseController;
import com.qatang.cms.entity.role.Role;
import com.qatang.cms.entity.user.UserRole;
import com.qatang.cms.form.user.UserForm;
import com.qatang.cms.service.role.RoleService;
import com.qatang.cms.service.user.UserRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by JSH on 2014/6/26.
 */
@Controller
@SessionAttributes(CommonConstants.QUERY_CONDITION_KEY)
@RequestMapping("/userRole")
public class UserRoleController extends BaseController {
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RoleService roleService;

    @RequiresPermissions("sys:userRole:allot")
    @RequestMapping(value = "/allot/{userId}", method = RequestMethod.GET)
    public String input(@PathVariable Long userId, ModelMap modelMap) {
        List<Role> existRoles = userRoleService.findRolesByUserId(userId);
        List<Role> rolesList = roleService.findAll();
        UserForm userForm = new UserForm();
        userForm.setId(String.valueOf(userId));
        modelMap.addAttribute(userForm);
        modelMap.addAttribute("rolesList", rolesList);
        modelMap.addAttribute("existRoles", existRoles);
        modelMap.addAttribute(FORWARD_URL, "/user/list");
        return "user/roles";
    }

    @RequiresPermissions("sys:userRole:allot")
    @RequestMapping(value = "/allot", method = RequestMethod.POST)
    public String update(UserForm userForm, ModelMap modelMap) {

        Long userId = Long.parseLong(userForm.getId());
        List<Long> roleIdList = userForm.getRoleIdList();

        List<UserRole> existRoleList = userRoleService.findUserRolesByUserId(userId);
        Set<Long> roleSet = new HashSet<>();
        for (UserRole userRole : existRoleList) {
            if (!roleSet.contains(userRole.getRoleId())) {
                roleSet.add(userRole.getRoleId());
            }
        }
        List<UserRole> deleteList = new ArrayList<>();
        List<UserRole> saveList = new ArrayList<>();
        for (Long roleId : roleIdList) {
            if (!roleSet.contains(roleId)) {
                if (roleId == null) {
                    continue;
                }
                UserRole userRole = new UserRole();
                userRole.setRoleId(roleId);
                userRole.setUserId(userId);
                saveList.add(userRole);
            }
        }
        userRoleService.save(saveList);
        for (Long roleId : roleSet) {
            if (!roleIdList.contains(roleId)) {
                for (UserRole existUserRole : existRoleList) {
                    if (existUserRole.getRoleId() == roleId) {
                        deleteList.add(existUserRole);
                    }
                }
            }
        }
        userRoleService.delete(deleteList);
        modelMap.addAttribute(FORWARD_URL, "/user/list");
        return "success";
    }

}
