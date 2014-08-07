package com.qatang.cms.controller.role;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;


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

    @RequestMapping(value = "/input", method = {RequestMethod.POST, RequestMethod.GET})
    public String input(RoleForm roleForm, ModelMap modelMap) {
        if (roleForm != null && roleForm.getId() != null && !"".equals(roleForm.getId())) {
            Role role = roleService.getRole(Long.parseLong(roleForm.getId()));
            modelMap.addAttribute(role);
            if (role != null) {
                roleForm.setRoleName(role.getRoleName());
                roleForm.setRoleDesc(role.getRoleDesc());
                roleForm.setValid(role.getValid().getValue() + "");
            }
        }
        return "/role/roleInput";
    }

    @RequestMapping(value = "/list/{currentPage}", method = {RequestMethod.POST, RequestMethod.GET})
    public String list(@PathVariable String currentPage, RoleForm roleForm, ModelMap modelMap) {
        getModelMap(roleForm, currentPage, modelMap);
        return "role/roleList";
    }

    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    public String list(RoleForm roleForm, ModelMap modelMap) {
        getModelMap(roleForm, "", modelMap);
        return "role/roleList";
    }

    private ModelMap getModelMap (RoleForm roleForm, String currentPage, ModelMap modelMap) {
        if (roleForm == null) {
            roleForm = new RoleForm();
        }
        if (currentPage == null || "".equals(currentPage) || Integer.parseInt(currentPage) < 1) {
            currentPage = 1 + "";
        }
        if (roleForm.getQueryRoleName() != null) {
            try {
                String queryRoleName = java.net.URLDecoder.decode(roleForm.getQueryRoleName(),"UTF-8");
                roleForm.setQueryRoleName(queryRoleName);
            } catch (UnsupportedEncodingException e) {
                logger.error(e.getMessage(), e);
            }
        }
        PageInfo pageInfo = roleForm.getPageInfo();
        pageInfo.setCurrentPage(Integer.parseInt(currentPage));
        roleForm.setPageInfo(pageInfo);
        Page<Role> rolePage = roleService.findAllPage(roleForm);
        if (rolePage == null) {
            return null;
        }
        List<Role> roleList = rolePage.getContent();
        rolePage.getTotalPages();
        roleForm.getPageInfo().setTotalPages(rolePage.getTotalPages());
        modelMap.addAttribute(roleList);
        modelMap.addAttribute(roleForm);
        return modelMap;
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

    @RequestMapping(value = "/toggleValidStatus", method = RequestMethod.POST)
    public String toggleValidStatus(RoleForm roleForm, RedirectAttributes redirectAttributes) {
        if (roleForm == null || roleForm.getId() == null) {
            redirectAttributes.addFlashAttribute(ERROR_MESSAGE_KEY, "传入的角色id为null！");
            return "redirect:/role/list" ;
        }
        Role role = roleService.getRole(Long.parseLong(roleForm.getId()));
        if (role == null) {
            redirectAttributes.addFlashAttribute(ERROR_MESSAGE_KEY, "要切换状态的角色对象不存在！");
        }
        if (roleForm.getValid() == null) {
            redirectAttributes.addFlashAttribute(ERROR_MESSAGE_KEY, "传入要切换的状态值为null！");
            return "redirect:/role/list" ;
        }
        role.setValid(EnableDisableStatus.get(Integer.parseInt(roleForm.getValid())));
        roleService.update(role);
        redirectAttributes.addFlashAttribute(SUCCESS_MESSAGE_KEY, "成功切换角色的状态！");
        redirectAttributes.addFlashAttribute(roleForm);
        if (roleForm.getPageInfo() != null && roleForm.getPageInfo().currentPage != null) {
            return "redirect:/role/list/" + roleForm.getPageInfo().currentPage;
        }
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
        redirectAttributes.addFlashAttribute(roleForm);
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
