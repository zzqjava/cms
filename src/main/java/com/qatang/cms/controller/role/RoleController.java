package com.qatang.cms.controller.role;

import com.qatang.cms.entity.role.Role;
import com.qatang.cms.form.role.RoleForm;
import com.qatang.cms.service.role.RoleService;
import com.qatang.cms.validator.IValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Created by zhangzq on 14-6-25.
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IValidator<RoleForm> createRoleValidator;
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String register(RoleForm roleForm,Model model) {
        Role role = new Role();
        role.setRoleName(roleForm.getRoleName());
        role.setRoleDesc(roleForm.getRoleDesc());
        return "role/roleList";
    }

}
