package com.qatang.cms.controller.role;

import com.qatang.cms.entity.resource.Resource;
import com.qatang.cms.entity.role.RoleMenu;
import com.qatang.cms.form.PageInfo;
import com.qatang.cms.controller.BaseController;
import com.qatang.cms.entity.role.Role;
import com.qatang.cms.entity.role.RoleMenu;
import com.qatang.cms.enums.EnableDisableStatus;
import com.qatang.cms.enums.YesNoStatus;
import com.qatang.cms.exception.validator.ValidateFailedException;
import com.qatang.cms.form.PageInfo;
import com.qatang.cms.form.role.RoleForm;
import com.qatang.cms.form.role.RoleMenuForm;
import com.qatang.cms.service.resource.ResourceService;
import com.qatang.cms.service.role.RoleService;
import com.qatang.cms.validator.IValidator;
import org.apache.commons.lang3.StringUtils;
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
@SessionAttributes("queryParam")
@RequestMapping("/role")
public class RoleController extends BaseController {

    @Autowired
    private IValidator<RoleForm> createRoleValidator;
    @Autowired
    private IValidator<RoleMenuForm> createRoleMenuValidator;
    @Autowired
    private RoleService roleService;
    @Autowired
    private ResourceService resourceService;

    /*@RequiresPermissions("sys:role:input")*/
    @RequestMapping(value = "/input", method = RequestMethod.GET)
    public String input(ModelMap modelMap) {
        modelMap.addAttribute(FORWARD_URL, "/role/list");
        return "/role/roleInput";
    }

    /*@RequiresPermissions("sys:role:input")*/
    @RequestMapping(value = "/input/{roleId}", method = RequestMethod.GET)
    public String input(@PathVariable String roleId, ModelMap modelMap) {
        if (roleId != null && !"".equals(roleId)) {
            Role role = roleService.getRole(Long.parseLong(roleId));
            RoleForm roleForm = new RoleForm();
            if (role != null) {
                roleForm.setId(String.valueOf(role.getId()));
                roleForm.setName(role.getName());
                roleForm.setIdentifier(role.getIdentifier());
                roleForm.setDescription(role.getDescription());
                roleForm.setIsDefault(String.valueOf(role.getIsDefault().getValue()));
                roleForm.setValid(String.valueOf(role.getValid().getValue()));
            }
            modelMap.addAttribute(roleForm);
        }
        return "/role/roleInput";
    }

    /*@RequiresPermissions("sys:role:list")*/
    @RequestMapping(value = "/list/{currentPage}", method = RequestMethod.POST)
    public String list(@PathVariable String currentPage, @ModelAttribute("queryParam") RoleForm roleForm, ModelMap modelMap, HttpServletRequest request) {
        if (currentPage == null || "".equals(currentPage) || Integer.parseInt(currentPage) < 1) {
            currentPage = 1 + "";
        }
        roleForm.getPageInfo().setCurrentPage(Integer.parseInt(currentPage));
        pagination(roleForm, modelMap, request);
        return "role/roleList";
    }

    /*@RequiresPermissions("sys:role:list")*/
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    public String list(ModelMap modelMap, HttpServletRequest request) {
        RoleForm roleForm;
        if (modelMap.containsKey("queryParam")) {
            roleForm = (RoleForm) modelMap.get("queryParam");
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
                List<Role> roleList = rolePage.getContent();
                modelMap.addAttribute(roleList);
            }
            PageInfo pageInfo = roleForm.getPageInfo();
            pageInfo.setTotalPages(rolePage.getTotalPages());
        }
        modelMap.addAttribute(roleForm);
        request.getSession().setAttribute("queryParam", roleForm);
    }

    /*@RequiresPermissions("sys:role:create")*/
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(RoleForm roleForm, RedirectAttributes redirectAttributes, ModelMap modelMap) {
        try {
            //roleForm.setValid("1");
            createRoleValidator.validate(roleForm);
        } catch (ValidateFailedException e) {
            logger.error(e.getMessage());
            modelMap.addAttribute(ERROR_MESSAGE_KEY, e.getMessage());
            modelMap.addAttribute(roleForm);
            return "/role/roleInput";
        }
        Role role = new Role();
        role.setName(roleForm.getName());
        role.setIdentifier(roleForm.getIdentifier());
        role.setDescription(roleForm.getDescription());
        role.setIsDefault(YesNoStatus.get(Integer.parseInt(roleForm.getIsDefault())));
        role.setValid(EnableDisableStatus.get(Integer.parseInt(roleForm.getValid())));
        role.setIsDefault(YesNoStatus.YES);
        role.setValid(EnableDisableStatus.ENABLE);
        role.setCreatedTime(new Date());
        roleService.save(role);
        redirectAttributes.addFlashAttribute(SUCCESS_MESSAGE_KEY, "成功添加角色！");
        return "redirect:/role/list";
    }

    /*@RequiresPermissions("sys:role:toggleValidStatus")*/
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

    /*@RequiresPermissions("sys:role:update")*/
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(RoleForm roleForm, RedirectAttributes redirectAttributes, ModelMap modelMap) {
        try {
            createRoleValidator.validate(roleForm);
        } catch (ValidateFailedException e) {
            logger.error(e.getMessage());
            redirectAttributes.addFlashAttribute(ERROR_MESSAGE_KEY, e.getMessage());
            modelMap.addAttribute(roleForm);
            return "/role/roleInput";
        }
        Role role = roleService.getRole(Long.parseLong(roleForm.getId()));
        role.setName(roleForm.getName());
        roleForm.setDescription(role.getDescription());
        roleForm.setIsDefault(String.valueOf(role.getIsDefault().getValue()));
        role.setValid(EnableDisableStatus.get(Integer.parseInt(roleForm.getValid())));
        role.setUpdatedTime(new Date());
        roleService.update(role);
        redirectAttributes.addFlashAttribute(SUCCESS_MESSAGE_KEY, "成功更新角色！");
        return "redirect:/role/list" ;
    }

    /*@RequiresPermissions("sys:role:queryMenu")*/
    @RequestMapping(value = "/queryMenu/{roleId}", method = RequestMethod.GET)
    public String queryMenu(@PathVariable String roleId, ModelMap modelMap,RoleMenuForm roleMenuForm, RedirectAttributes redirectAttributes) {
        if (StringUtils.isEmpty(roleId)) {
            redirectAttributes.addFlashAttribute(ERROR_MESSAGE_KEY, "传入的角色id为null！");
            return "redirect:/role/list" ;
        }
        //根据传入过来的角色 先判断当前角色已拥有的菜单
        List<RoleMenu> hasExistRoleMenuList = roleService.findRoleMenuList(Long.parseLong(roleId));
        StringBuilder stringBuilder = new StringBuilder("");
        for (RoleMenu roleMenu : hasExistRoleMenuList) {
            stringBuilder.append(roleMenu.getMenuId()).append(",");
        }
        String menuIds = !stringBuilder.equals("") ? stringBuilder.toString() : "";
        roleMenuForm.setMenuIds(menuIds.lastIndexOf(",") > 0 ? menuIds.substring(0, menuIds.lastIndexOf(",")) : menuIds);
        modelMap.addAttribute(roleId);
        modelMap.addAttribute(roleMenuForm);
        return "/role/roleMenuInput";
    }

    /*@RequiresPermissions("sys:role:createRoleMenu")*/
    @RequestMapping(value ="/createRoleMenu", method = RequestMethod.POST)
    public String createRoleMenu(RoleMenuForm roleMenuForm, ModelMap modelMap, RedirectAttributes redirectAttributes) {
        try {
            createRoleMenuValidator.validate(roleMenuForm);
        } catch (ValidateFailedException e) {
            logger.error(e.getMessage());
            redirectAttributes.addFlashAttribute(ERROR_MESSAGE_KEY, e.getMessage());
            return "redirect:/role/queryMenu/"+roleMenuForm.getRoleId() ;
        }

        String menuIds = roleMenuForm.getMenuIds();
        String[] menuIdsArray = menuIds.split(",");
        for (String menuId : menuIdsArray) {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleId(Long.parseLong(roleMenuForm.getRoleId()));
            roleMenu.setMenuId(Long.parseLong(menuId));
            roleService.save(roleMenu);
        }
        List<RoleMenu> roleMenuList = roleService.findRoleMenuList(Long.parseLong(roleMenuForm.getRoleId()));
        modelMap.addAttribute(roleMenuForm);
        redirectAttributes.addFlashAttribute(SUCCESS_MESSAGE_KEY, "保存成功！");
        redirectAttributes.addFlashAttribute("roleMenuList", roleMenuList);
        return "redirect:/role/queryMenu/" + roleMenuForm.getRoleId() ;
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

    /**
     * 获取菜单列表
     * */
    @ModelAttribute("menuList")
    public List<Resource> menuList() {
        return resourceService.getList();
    }
}
