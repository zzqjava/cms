package com.qatang.cms.controller.role;

import com.qatang.cms.constants.CommonConstants;
import com.qatang.cms.controller.BaseController;
import com.qatang.cms.entity.role.Role;
import com.qatang.cms.enums.EnableDisableStatus;
import com.qatang.cms.enums.YesNoStatus;
import com.qatang.cms.exception.validator.ValidateFailedException;
import com.qatang.cms.form.PageInfo;
import com.qatang.cms.form.role.RoleForm;
import com.qatang.cms.service.role.RoleService;
import com.qatang.cms.validator.IValidator;
import com.qatang.cms.validator.impl.role.RoleFormTypeConverterValidator;
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
@SessionAttributes(CommonConstants.ROLE_QUERY_CONDITION_KEY)
@RequestMapping("/role")
public class RoleController extends BaseController {

    @Autowired
    private IValidator<RoleForm> createRoleValidator;
	@Autowired
	private IValidator<RoleForm> queryRoleValidator;
    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleFormTypeConverterValidator roleFormTypeConverterValidator;

    /*@RequiresPermissions("sys:role:list")*/
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(ModelMap modelMap, HttpServletRequest request) {
        RoleForm roleForm;
        if (modelMap.containsKey(CommonConstants.ROLE_QUERY_CONDITION_KEY)) {
            roleForm = (RoleForm) modelMap.get(CommonConstants.ROLE_QUERY_CONDITION_KEY);
        } else {
            roleForm = new RoleForm();
        }
        pagination(roleForm, modelMap, request);
        return "role/roleList";
    }

	/*@RequiresPermissions("sys:role:list")*/
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public String list(@ModelAttribute(CommonConstants.ROLE_QUERY_CONDITION_KEY) RoleForm roleForm, ModelMap modelMap, HttpServletRequest request) {
		try {
			queryRoleValidator.validate(roleForm);
		} catch (ValidateFailedException e) {
			logger.error(e.getMessage(), e);
			modelMap.addAttribute(ERROR_MESSAGE_KEY, e.getMessage());
			return "/role/roleList";
		}
		pagination(roleForm, modelMap, request);
		return "role/roleList";
	}

	private void pagination(RoleForm roleForm, ModelMap modelMap, HttpServletRequest request) {
		Page<Role> rolePage = roleService.findAllPage(roleForm);
		if (rolePage.getContent() != null) {
			List<Role> roleList = rolePage.getContent();
			modelMap.addAttribute(roleList);
		}
		PageInfo pageInfo = roleForm.getPageInfo();
		pageInfo.setTotalPages(rolePage.getTotalPages());
		modelMap.addAttribute(roleForm);
		request.getSession().setAttribute(CommonConstants.ROLE_QUERY_CONDITION_KEY, roleForm);
	}

    /*@RequiresPermissions("sys:role:create")*/
    @RequestMapping(value = "/input", method = RequestMethod.GET)
    public String input(@ModelAttribute RoleForm roleForm, ModelMap modelMap) {
        //是否默认角色默认为“否”
	    roleForm.setIsDefault(String.valueOf(YesNoStatus.NO.getValue()));
	    roleForm.setValid(String.valueOf(EnableDisableStatus.ENABLE.getValue()));
        modelMap.addAttribute(roleForm);
        modelMap.addAttribute(FORWARD_URL, "/role/list");
        return "/role/roleInput";
    }

    /*@RequiresPermissions("sys:role:input")*/
    @RequestMapping(value = "/input/{roleId}", method = RequestMethod.GET)
    public String input(@PathVariable String roleId, ModelMap modelMap, RedirectAttributes redirectAttributes) {
        RoleForm roleForm = new RoleForm();
        roleForm.setId(roleId);
        try {
            roleFormTypeConverterValidator.validate(roleForm);
        } catch (ValidateFailedException e) {
            logger.error(e.getMessage(), e);
            modelMap.addAttribute(ERROR_MESSAGE_KEY, e.getMessage());
            redirectAttributes.addFlashAttribute(roleForm);
            return "redirect:/role/list";
        }
        Role role = roleService.getRole(Long.parseLong(roleId));
        if (role == null) {
            redirectAttributes.addFlashAttribute(ERROR_MESSAGE_KEY, "要修改的角色对象不存在！");
        }
        roleForm.setName(role.getName());
        roleForm.setIdentifier(role.getIdentifier());
        roleForm.setDescription(role.getDescription());
        roleForm.setIsDefault(String.valueOf(role.getIsDefault().getValue()));
        roleForm.setValid(String.valueOf(role.getValid().getValue()));
        modelMap.addAttribute(roleForm);
        modelMap.addAttribute(FORWARD_URL, "/role/list");
        return "/role/roleInput";
    }

    /*@RequiresPermissions("sys:role:create")*/
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(RoleForm roleForm, RedirectAttributes redirectAttributes, ModelMap modelMap) {
        try {
            createRoleValidator.validate(roleForm);
        } catch (ValidateFailedException e) {
            logger.error(e.getMessage());
            if (roleForm != null && roleForm.getId() != null) {
                roleForm.setId(null);
            }
            redirectAttributes.addFlashAttribute(ERROR_MESSAGE_KEY, e.getMessage());
            redirectAttributes.addFlashAttribute(roleForm);
            return "redirect:/role/input";
        }
        Role role = new Role();
        role.setName(roleForm.getName());
        role.setIdentifier(roleForm.getIdentifier());
        role.setDescription(roleForm.getDescription());
        role.setIsDefault(YesNoStatus.get(Integer.parseInt(roleForm.getIsDefault())));
        role.setValid(EnableDisableStatus.get(Integer.parseInt(roleForm.getValid())));
        role.setCreatedTime(new Date());
        role.setUpdatedTime(new Date());
        /*roleService.save(role);*/
        redirectAttributes.addFlashAttribute(SUCCESS_MESSAGE_KEY, "成功添加角色！");
        return "redirect:/role/list";
    }

    /*@RequiresPermissions("sys:role:toggleValidStatus")*/
    @RequestMapping(value = "/toggleValidStatus/{roleId}", method = RequestMethod.GET)
    public String toggleValidStatus(@PathVariable String roleId, RedirectAttributes redirectAttributes) {
        RoleForm roleForm = new RoleForm();
        roleForm.setId(roleId);
        try {
            roleFormTypeConverterValidator.validate(roleForm);
        } catch (ValidateFailedException e) {
            logger.error(e.getMessage(), e);
            redirectAttributes.addAttribute(ERROR_MESSAGE_KEY, e.getMessage());
            return "redirect:/role/list";
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
        if (role == null) {
            redirectAttributes.addFlashAttribute(ERROR_MESSAGE_KEY, "要保存的角色对象不存在！");
        }
        role.setName(roleForm.getName());
        role.setIdentifier(roleForm.getIdentifier());
        role.setDescription(roleForm.getDescription());
        role.setIsDefault(YesNoStatus.get(Integer.parseInt(roleForm.getIsDefault())));
        role.setValid(EnableDisableStatus.get(Integer.parseInt(roleForm.getValid())));
        role.setUpdatedTime(new Date());
        roleService.update(role);
        redirectAttributes.addFlashAttribute(SUCCESS_MESSAGE_KEY, "成功更新角色！");
        return "redirect:/role/list" ;
    }

    /*@RequiresPermissions("sys:role:view")*/
    @RequestMapping(value = "/view/{roleId}", method = RequestMethod.GET)
    public String view(@PathVariable String roleId, ModelMap modelMap) {
        RoleForm roleForm = new RoleForm();
        roleForm.setId(roleId);
        try {
            roleFormTypeConverterValidator.validate(roleForm);
        } catch (ValidateFailedException e) {
            logger.error(e.getMessage(), e);
            modelMap.addAttribute(ERROR_MESSAGE_KEY, e.getMessage());
            return "redirect:/role/list";
        }
        Role role = roleService.getRole(Long.parseLong(roleId));
        modelMap.addAttribute(role);
        modelMap.addAttribute(FORWARD_URL, "/role/list");
        return "role/roleView";
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
     * 新增与修改时候使用
     * */
    @ModelAttribute("yesNoStatuses")
    public List<YesNoStatus> yesNoStatuses() {
        List<YesNoStatus> yesNoStatuses = YesNoStatus.list();
        return yesNoStatuses;
    }
}
