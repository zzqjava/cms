package com.qatang.cms.controller.role;

import com.qatang.cms.controller.BaseController;
import com.qatang.cms.entity.role.Role;
import com.qatang.cms.enums.YesNoStatus;
import com.qatang.cms.exception.validator.ValidateFailedException;
import com.qatang.cms.form.role.RoleForm;
import com.qatang.cms.service.role.RoleService;
import com.qatang.cms.validator.IValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by zhangzq on 14-6-25.
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

    @Autowired
    private IValidator<RoleForm> createRoleValidator;
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/input", method = RequestMethod.GET)
    public ModelAndView input() {
        List<YesNoStatus> yesNoStatuses = YesNoStatus.list();
        Map map = new HashMap();
        map.put("yesNoStatuses", yesNoStatuses);
        return new ModelAndView("/role/roleInput", map);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list() {
        int page = 1 ;
        int size = 10;
        PageRequest pageRequest = new PageRequest(page,size);
        Page<Role> rolePage = roleService.findAllPage(pageRequest);
        List<Role> list = rolePage.getContent();
        rolePage.getTotalElements();
        Map map = new HashMap();
        map.put("list", list);
        map.put("totalPages",rolePage.getTotalPages());
        map.put("currentPage",page);
        return new ModelAndView("/role/roleList", map);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(RoleForm roleForm,RedirectAttributes redirectAttributes) {
        try {
            createRoleValidator.validate(roleForm);
        } catch (ValidateFailedException e) {
            logger.error(e.getMessage());
            redirectAttributes.addFlashAttribute(ERROR_MESSAGE_KEY, e.getMessage());
            return "redirect:/role/input";
        }
        Role role = new Role();
        role.setRoleName(roleForm.getRoleName());
        role.setRoleDesc(roleForm.getRoleDesc());
        role.setStatus(YesNoStatus.get(roleForm.getStatus()));
        role.setCreatedTime(new Date());
        roleService.save(role);
        redirectAttributes.addFlashAttribute("message", "成功添加角色！");
        return "redirect:/role/list";
    }

    @RequestMapping(value = "/del/{id}", method = RequestMethod.GET)
    public String del(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (id == null) {
            redirectAttributes.addFlashAttribute(ERROR_MESSAGE_KEY, "传入的角色id为null！");
            return "redirect:/role/list";
        }
        Role role = new Role();
        role.setId(id);
        roleService.del(role);
        redirectAttributes.addFlashAttribute("message", "成功删除角色！");
        return "redirect:/role/list";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public ModelAndView update(RoleForm roleForm, @PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (id == null) {
            redirectAttributes.addFlashAttribute(ERROR_MESSAGE_KEY, "传入的角色id为null！");
            return new ModelAndView("redirect:/role/list", new HashMap());
        }
        Role role = roleService.getRole(id);
        if (role == null) {
            redirectAttributes.addFlashAttribute(ERROR_MESSAGE_KEY, "所查询的角色对象不存在！");
            return new ModelAndView("redirect:/role/list", new HashMap());
        }
        if (role.getId() != null ) {
            roleForm.setId(role.getId());
        }
        if (role.getRoleName() != null ) {
            roleForm.setRoleName(role.getRoleName());
        }
        if (role.getRoleDesc() != null ) {
            roleForm.setRoleDesc(role.getRoleDesc());
        }
        if (role.getStatus() != null ) {
            roleForm.setStatus(role.getStatus().getValue());
        }

        List<YesNoStatus> yesNoStatuses = YesNoStatus.list();
        Map map = new HashMap();
        map.put("yesNoStatuses", yesNoStatuses);
        return new ModelAndView("/role/roleUpdate", map);
    }

    @RequestMapping(value = "/saveUpdate", method = RequestMethod.POST)
    public String saveUpdate(RoleForm roleForm,RedirectAttributes redirectAttributes) {
        try {
            createRoleValidator.validate(roleForm);
        } catch (ValidateFailedException e) {
            logger.error(e.getMessage());
            redirectAttributes.addFlashAttribute(ERROR_MESSAGE_KEY, e.getMessage());
            return "redirect:/role/preUpdate/" + roleForm.getId();
        }
        Role role = roleService.getRole(roleForm.getId());
        role.setRoleName(roleForm.getRoleName());
        role.setRoleDesc(roleForm.getRoleDesc());
        role.setStatus(YesNoStatus.get(roleForm.getStatus()));
        role.setUpdatedTime(new Date());
        roleService.update(role);
        redirectAttributes.addFlashAttribute("message", "成功更新角色！");
        return "redirect:/role/list";
    }
}
