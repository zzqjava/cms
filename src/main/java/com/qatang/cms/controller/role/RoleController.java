package com.qatang.cms.controller.role;

import com.qatang.cms.constants.CommonConstants;
import com.qatang.cms.form.PageInfo;
import com.qatang.cms.controller.BaseController;
import com.qatang.cms.entity.role.Role;
import com.qatang.cms.enums.EnableDisableStatus;
import com.qatang.cms.exception.validator.ValidateFailedException;
import com.qatang.cms.form.role.RoleForm;
import com.qatang.cms.service.role.RoleService;
import com.qatang.cms.validator.IValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;


/**
 * Created by zhangzq on 14-6-25.
 */
@Controller
@SessionAttributes(CommonConstants.QUERY_CONDITION_KEY)
@RequestMapping("/role")
public class RoleController extends BaseController {

    @Autowired
    private IValidator<RoleForm> createRoleValidator;
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/input", method = RequestMethod.GET)
    public String input() {
        return "/role/roleInput";
    }

    @RequestMapping(value = "/input/{roleId}", method = RequestMethod.GET)
    public String input(@PathVariable String roleId, ModelMap modelMap) {
        if (roleId != null && !"".equals(roleId)) {
            Role role = roleService.getRole(Long.parseLong(roleId));
            RoleForm roleForm = new RoleForm();
            if (role != null) {
                roleForm.setId(String.valueOf(role.getId()));
                roleForm.setRoleName(role.getRoleName());
                roleForm.setRoleDesc(role.getRoleDesc());
                roleForm.setValid(String.valueOf(role.getValid().getValue()));
            }
            modelMap.addAttribute(roleForm);
        }
        return "/role/roleInput";
    }

    @RequestMapping(value = "/list/{currentPage}", method = {RequestMethod.POST, RequestMethod.GET})
    public String list(@PathVariable String currentPage, @ModelAttribute(CommonConstants.QUERY_CONDITION_KEY) RoleForm roleForm, ModelMap modelMap, HttpServletRequest request) {
        if (currentPage == null || "".equals(currentPage) || Integer.parseInt(currentPage) < 1) {
            currentPage = 1 + "";
        }
        roleForm.getPageInfo().setCurrentPage(Integer.parseInt(currentPage));
        pagination(roleForm, modelMap, request);
        return "role/roleList";
    }

    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    public String list(ModelMap modelMap, HttpServletRequest request) {
        RoleForm roleForm;
        if (modelMap.containsKey(CommonConstants.QUERY_CONDITION_KEY)) {
            roleForm = (RoleForm) modelMap.get(CommonConstants.QUERY_CONDITION_KEY);
        } else {
            roleForm = new RoleForm();
        }
        pagination(roleForm, modelMap, request);
        return "role/roleList";
    }

    private void pagination (RoleForm roleForm, ModelMap modelMap, HttpServletRequest request) {
        Page<Role> rolePage = roleService.findAllPage(roleForm);
        if (rolePage != null) {
            if (rolePage.getContent() != null) {
                List<Role> userList = rolePage.getContent();
                modelMap.addAttribute(userList);
            }
            PageInfo pageInfo = roleForm.getPageInfo();
            pageInfo.setTotalPages(rolePage.getTotalPages());
        }
        modelMap.addAttribute(roleForm);
        request.getSession().setAttribute(CommonConstants.QUERY_CONDITION_KEY, roleForm);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(RoleForm roleForm, RedirectAttributes redirectAttributes, ModelMap modelMap) {
        try {
            createRoleValidator.validate(roleForm);
        } catch (ValidateFailedException e) {
            logger.error(e.getMessage());
            modelMap.addAttribute(ERROR_MESSAGE_KEY, e.getMessage());
            modelMap.addAttribute(roleForm);
            return "/role/roleInput";
        }
        Role role = new Role();
        role.setRoleName(roleForm.getRoleName());
        role.setRoleDesc(roleForm.getRoleDesc());
        role.setValid(EnableDisableStatus.get(Integer.parseInt(roleForm.getValid())));
        role.setCreatedTime(new Date());
        roleService.save(role);
        redirectAttributes.addFlashAttribute(SUCCESS_MESSAGE_KEY, "成功添加角色！");
        return "redirect:/role/list";
    }

    @RequestMapping(value = "/toggleValidStatus/{roleId}", method = RequestMethod.GET)
    public String toggleValidStatus(@PathVariable String roleId, RedirectAttributes redirectAttributes) {
        if (roleId == null || "".equals(roleId)) {
            redirectAttributes.addFlashAttribute(ERROR_MESSAGE_KEY, "传入的角色id为null！");
            return "redirect:/role/list" ;
        }
        Role role = roleService.getRole(Long.parseLong(roleId));
        if (role == null) {
            redirectAttributes.addFlashAttribute(ERROR_MESSAGE_KEY, "要切换状态的角色对象不存在！");
        }
        if (role.getValid().getValue() == EnableDisableStatus.ENABLE.getValue()) {
            role.setValid(EnableDisableStatus.DISABLE);
        } else if (role.getValid().getValue() == EnableDisableStatus.DISABLE.getValue()) {
            role.setValid(EnableDisableStatus.ENABLE);
        }
        roleService.update(role);
        redirectAttributes.addFlashAttribute(SUCCESS_MESSAGE_KEY, "成功切换角色的状态！");
        return "redirect:/role/list";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(RoleForm roleForm, RedirectAttributes redirectAttributes, ModelMap modelMap) {
        try {
            createRoleValidator.validate(roleForm);
        } catch (ValidateFailedException e) {
            logger.error(e.getMessage());
            modelMap.addAttribute(ERROR_MESSAGE_KEY, e.getMessage());
            modelMap.addAttribute(roleForm);
            return "/role/roleInput";
        }
        Role role = roleService.getRole(Long.parseLong(roleForm.getId()));
        role.setRoleName(roleForm.getRoleName());
        role.setRoleDesc(roleForm.getRoleDesc());
        role.setValid(EnableDisableStatus.get(Integer.parseInt(roleForm.getValid())));
        role.setUpdatedTime(new Date());
        roleService.update(role);
        redirectAttributes.addFlashAttribute(SUCCESS_MESSAGE_KEY, "成功更新角色！");
        if (modelMap.containsKey(CommonConstants.QUERY_CONDITION_KEY)) {
            roleForm = (RoleForm) modelMap.get(CommonConstants.QUERY_CONDITION_KEY);
        }
        if (roleForm.getPageInfo() != null && roleForm.getPageInfo().currentPage != null) {
            return "redirect:/role/list/" + roleForm.getPageInfo().currentPage;
        }
        return "redirect:/role/list" ;
    }
    /**
     * 新增与修改时候使用
     * */
    @ModelAttribute("enableDisableStatus")
    public List<EnableDisableStatus> enableDisableStatus() {
        List<EnableDisableStatus> enableDisableStatus = EnableDisableStatus.list();
        return enableDisableStatus;
    }
    /**
     * 查询时候使用
     * */
    @ModelAttribute("queryEnableDisableStatus")
    public List<EnableDisableStatus> queryEnableDisableStatus() {
        List<EnableDisableStatus> enableDisableStatus = EnableDisableStatus.listAll();
        return enableDisableStatus;
    }
}
