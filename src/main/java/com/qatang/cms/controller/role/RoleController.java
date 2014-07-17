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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;


/**
 * Created by zhangzq on 14-6-25.
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

	protected final static String ROLE_KEY = "role";

	@Autowired
	private IValidator<RoleForm> createRoleValidator;
	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "/input")
	public String input(RoleForm roleForm, ModelMap modelMap) {
		List<EnableDisableStatus> enableDisableStatus = EnableDisableStatus.list();
		modelMap.put("enableDisableStatus",enableDisableStatus);
		if (roleForm != null && roleForm.getId() != null) {
			Role role = roleService.getRole(roleForm.getId());
			modelMap.addAttribute(ROLE_KEY, role);
		}
		//处理新增时 验证不通过，回显用户已经填写的记录
		if (roleForm != null && roleForm.getId() == null) {
			Role role = new Role();
			if (roleForm.getRoleName() != null) {
				role.setRoleName(roleForm.getRoleName());
			}
			if (roleForm.getRoleDesc() != null) {
				role.setRoleDesc(roleForm.getRoleDesc());
			}
			if (roleForm.getValid() != null) {
				role.setValid(EnableDisableStatus.get(roleForm.getValid()));
			}
			modelMap.addAttribute(ROLE_KEY, role);
		}
		return "/role/roleInput";
	}

	@RequestMapping(value = "/list/{currentPage}")
	public String list(@PathVariable Integer currentPage, RoleForm roleForm, ModelMap modelMap) {
		if (currentPage == null || currentPage < 1) {
			currentPage = 1;
		}
		if (roleForm == null) {
			roleForm = new RoleForm();
		}
		PageInfo pageInfo = roleForm.getPageInfo();
		if (pageInfo == null) {
			pageInfo = new PageInfo();
		}
		pageInfo.setCurrentPage(currentPage);
		roleForm.setPageInfo(pageInfo);
		Page<Role> rolePage = roleService.findAllPage(roleForm);
		List<Role> list = rolePage.getContent();
		rolePage.getTotalPages();
		roleForm.getPageInfo().setTotalPages(rolePage.getTotalPages());
		modelMap.put("list",list);
		modelMap.put("roleForm",roleForm);
		return "role/roleList";
	}

	@RequestMapping(value = "/list")
	public String list(RoleForm roleForm, ModelMap modelMap) {
		if (roleForm == null) {
			roleForm = new RoleForm();
		}
		PageInfo pageInfo = roleForm.getPageInfo();
		if (pageInfo == null) {
			pageInfo = new PageInfo();
		}
		roleForm.setPageInfo(pageInfo);
		Page<Role> rolePage = roleService.findAllPage(roleForm);
		List<Role> list = rolePage.getContent();
		rolePage.getTotalPages();
		roleForm.getPageInfo().setTotalPages(rolePage.getTotalPages());
		modelMap.put("list",list);
		modelMap.put("roleForm",roleForm);
		return "role/roleList";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(RoleForm roleForm, RedirectAttributes redirectAttributes) {
		try {
			createRoleValidator.validate(roleForm);
		} catch (ValidateFailedException e) {
			logger.error(e.getMessage());
			redirectAttributes.addFlashAttribute(ERROR_MESSAGE_KEY, e.getMessage());
			redirectAttributes.addFlashAttribute("roleForm", roleForm);
			return "redirect:/role/input";
		}
		Role role = new Role();
		role.setRoleName(roleForm.getRoleName());
		role.setRoleDesc(roleForm.getRoleDesc());
		role.setValid(EnableDisableStatus.get(roleForm.getValid()));
		role.setCreatedTime(new Date());
		roleService.save(role);
		redirectAttributes.addFlashAttribute("message", "成功添加角色！");
		return "redirect:/role/list";
	}

	@RequestMapping(value = "/del/{id}", method = RequestMethod.POST)
	public String del(@PathVariable Long id, RedirectAttributes redirectAttributes) {
		if (id == null) {
			redirectAttributes.addFlashAttribute(ERROR_MESSAGE_KEY, "传入的角色id为null！");
			return "redirect:/role/list" ;
		}
		Role role = roleService.getRole(id);
		if (role == null) {
			redirectAttributes.addFlashAttribute(ERROR_MESSAGE_KEY, "要删除的角色对象不存在！");
		}
		role.setValid(EnableDisableStatus.DISABLE);
		roleService.update(role);
		redirectAttributes.addFlashAttribute("message", "成功删除角色！");
		return "redirect:/role/list";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(RoleForm roleForm, RedirectAttributes redirectAttributes) {
		try {
			createRoleValidator.validate(roleForm);
		} catch (ValidateFailedException e) {
			logger.error(e.getMessage());
			redirectAttributes.addFlashAttribute(ERROR_MESSAGE_KEY, e.getMessage());
			redirectAttributes.addFlashAttribute("roleForm", roleForm);
			return "redirect:/role/input";
		}
		Role role = roleService.getRole(roleForm.getId());
		role.setRoleName(roleForm.getRoleName());
		role.setRoleDesc(roleForm.getRoleDesc());
		role.setValid(EnableDisableStatus.get(roleForm.getValid()));
		role.setUpdatedTime(new Date());
		roleService.update(role);
		redirectAttributes.addFlashAttribute("message", "成功更新角色！");
		return "redirect:/role/list" ;
	}
}