package com.qatang.cms.controller.menu;

import com.qatang.cms.controller.BaseController;
import com.qatang.cms.entity.menu.Menu;
import com.qatang.cms.enums.EnableDisableStatus;
import com.qatang.cms.exception.validator.ValidateFailedException;
import com.qatang.cms.form.menu.MenuForm;
import com.qatang.cms.service.menu.MenuService;
import com.qatang.cms.validator.IValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.List;

/**
 * Created by likunpeng on 2014/6/24.
 */
@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController {

	@Autowired
	private IValidator<MenuForm> menuValidator;
	@Autowired
	private MenuService menuService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(ModelMap model) {
		List<Menu> menuList = menuService.getList();
		model.addAttribute(menuList);
		return "/menu/menuList";
	}

	@RequestMapping(value = "/input", method = RequestMethod.GET)
	public String input() {
		return "/menu/menuInput";
	}

	@RequestMapping(value = "/input/{menuId}", method = RequestMethod.GET)
	public String input(@PathVariable String menuId, ModelMap modelMap) {
		if (StringUtils.isNotEmpty(menuId)) {
			Long id = null;
			try {
				id = Long.parseLong(menuId);
			} catch (NumberFormatException e) {
				logger.error("修改的菜单id不合法");
				modelMap.addAttribute(ERROR_MESSAGE_KEY, "修改的菜单id不合法");
				modelMap.addAttribute(FORWARD_URL, "/menu/list");
				return "failure";
			}
			Menu menu = menuService.get(id);
			if (menu == null) {
				logger.error("根据菜单id,没有查询到该菜单信息！");
				modelMap.addAttribute(ERROR_MESSAGE_KEY, "根据菜单id,没有查询到该菜单信息！");
				modelMap.addAttribute(FORWARD_URL, "/menu/list");
				return "failure";
			}
			MenuForm menuForm = new MenuForm();
			menuForm.setId(menuId);
			menuForm.setName(menu.getName());
			menuForm.setUrl(menu.getUrl());
			menuForm.setOrderLevelValue(menu.getOrderLevel() + "");
			menuForm.setValidValue(menu.getValid().getValue() + "");
			menuForm.setMemo(menu.getMemo());
			modelMap.addAttribute(menuForm);
		}
		return "/menu/menuInput";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(MenuForm menuForm, ModelMap modelMap) {
		try {
			menuValidator.validate(menuForm);
		} catch (ValidateFailedException e) {
			logger.error(e.getMessage(), e);
			modelMap.addAttribute(ERROR_MESSAGE_KEY, e.getMessage());
			modelMap.addAttribute(menuForm);
			return "menu/menuInput";
		}
		Menu menu = new Menu();
		menu.setName(menuForm.getName());
		menu.setUrl(menuForm.getUrl());
		menu.setOrderLevel(Integer.valueOf(menuForm.getOrderLevelValue()));
		menu.setValid(EnableDisableStatus.get(Integer.valueOf(menuForm.getValidValue())));
		menu.setMemo(menuForm.getMemo());
		menu.setCreatedTime(new Date());
		menu.setUpdatedTime(new Date());
		menuService.save(menu);
		modelMap.addAttribute(FORWARD_URL, "/menu/list");
		return "success";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(MenuForm menuForm, ModelMap modelMap) {
		if (StringUtils.isNotEmpty(menuForm.getId())) {
			try {
				menuValidator.validate(menuForm);
			} catch (ValidateFailedException e) {
				logger.error(e.getMessage(), e);
				modelMap.addAttribute(ERROR_MESSAGE_KEY, e.getMessage());
				modelMap.addAttribute(menuForm);
				return "menu/menuInput";
			}
			Long id = Long.parseLong(menuForm.getId());
			Menu menu = menuService.get(id);
			if (menu == null) {
				logger.error("根据菜单id,没有查询到该菜单信息！");
				modelMap.addAttribute(ERROR_MESSAGE_KEY, "根据菜单id,没有查询到该菜单信息！");
				modelMap.addAttribute(FORWARD_URL, "/menu/list");
				return "failure";
			}
			menu.setName(menuForm.getName());
			menu.setName(menuForm.getName());
			menu.setUrl(menuForm.getUrl());
			menu.setOrderLevel(Integer.valueOf(menuForm.getOrderLevelValue()));
			menu.setValid(EnableDisableStatus.get(Integer.valueOf(menuForm.getValidValue())));
			menu.setMemo(menuForm.getMemo());
			menu.setUpdatedTime(new Date());
			menuService.update(menu);
		} else {
			logger.error("菜单id为空");
			modelMap.addAttribute(ERROR_MESSAGE_KEY, "菜单id为空");
			return "/menu/menuInput";
		}
		modelMap.addAttribute(FORWARD_URL, "/menu/list");
		return "success";
	}

	@RequestMapping(value = "/disable/{menuId}", method = RequestMethod.GET)
	public String disable(@PathVariable String menuId, ModelMap modelMap) {
		if (StringUtils.isEmpty(menuId)) {
			logger.error("传递的参数菜单id为空！");
			modelMap.addAttribute(ERROR_MESSAGE_KEY, "传递的参数菜单id为空！");
			modelMap.addAttribute(FORWARD_URL, "/menu/list");
			return "failure";
		}
		Long id = null;
		try {
			id = Long.parseLong(menuId);
		} catch (NumberFormatException e) {
			logger.error("禁用的菜单id不合法");
			modelMap.addAttribute(ERROR_MESSAGE_KEY, "禁用的菜单id不合法");
			modelMap.addAttribute(FORWARD_URL, "/menu/list");
			return "failure";
		}
		Menu menu = menuService.get(id);
		if (menu == null) {
			logger.error("根据菜单id,没有查询到该菜单信息！");
			modelMap.addAttribute(ERROR_MESSAGE_KEY, "根据菜单id,没有查询到该菜单信息！");
			modelMap.addAttribute(FORWARD_URL, "/menu/list");
			return "failure";
		}
		menu.setValid(EnableDisableStatus.DISABLE);
		menu.setUpdatedTime(new Date());
		menuService.update(menu);
		return "redirect:/menu/list";
	}

	@RequestMapping(value = "/enable/{menuId}", method = RequestMethod.GET)
	public String enable(@PathVariable String menuId, ModelMap modelMap) {
		if (StringUtils.isEmpty(menuId)) {
			logger.error("传递的参数菜单id为空！");
			modelMap.addAttribute(ERROR_MESSAGE_KEY, "传递的参数菜单id为空！");
			modelMap.addAttribute(FORWARD_URL, "/menu/list");
			return "failure";
		}
		Long id = null;
		try {
			id = Long.parseLong(menuId);
		} catch (NumberFormatException e) {
			logger.error("启用的菜单id不合法");
			modelMap.addAttribute(ERROR_MESSAGE_KEY, "启用的菜单id不合法");
			modelMap.addAttribute(FORWARD_URL, "/menu/list");
			return "failure";
		}
		Menu menu = menuService.get(id);
		if (menu == null) {
			logger.error("根据菜单id,没有查询到该菜单信息！");
			modelMap.addAttribute(ERROR_MESSAGE_KEY, "根据菜单id,没有查询到该菜单信息！");
			modelMap.addAttribute(FORWARD_URL, "/menu/list");
			return "failure";
		}
		menu.setValid(EnableDisableStatus.ENABLE);
		menu.setUpdatedTime(new Date());
		menuService.update(menu);
		return "redirect:/menu/list";
	}

	@RequestMapping(value = "/view/{menuId}", method = RequestMethod.GET)
	public String view(@PathVariable String menuId, ModelMap modelMap) {
		if (StringUtils.isEmpty(menuId)) {
			logger.error("查看用户，id为空");
			modelMap.addAttribute(ERROR_MESSAGE_KEY, "查看用户，id为空");
			modelMap.addAttribute(FORWARD_URL, "/menu/list");
			return "failure";
		}
		Long id = null;
		try {
			id = Long.parseLong(menuId);
		} catch (NumberFormatException e) {
			logger.error("查看菜单，菜单id不合法");
			modelMap.addAttribute(ERROR_MESSAGE_KEY, "查看菜单，菜单id不合法");
			modelMap.addAttribute(FORWARD_URL, "/menu/list");
			return "failure";
		}
		Menu menu = menuService.get(id);
		if (menu == null) {
			logger.error("根据菜单id,没有查询到该菜单信息！");
			modelMap.addAttribute(ERROR_MESSAGE_KEY, "根据菜单id,没有查询到该菜单信息！");
			modelMap.addAttribute(FORWARD_URL, "/menu/list");
			return "failure";
		}
		modelMap.addAttribute(menu);
		modelMap.addAttribute(FORWARD_URL, "/menu/list");
		return "menu/menuView";
	}

	@RequestMapping(value = "/delete/{menuId}", method = RequestMethod.GET)
	public String delete(@PathVariable String menuId, ModelMap modelMap) {
		if (StringUtils.isEmpty(menuId)) {
			logger.error("传递的参数菜单id为空！");
			modelMap.addAttribute(ERROR_MESSAGE_KEY, "传递的参数菜单id为空！");
			modelMap.addAttribute(FORWARD_URL, "/menu/list");
			return "failure";
		}
		Long id = null;
		try {
			id = Long.parseLong(menuId);
		} catch (NumberFormatException e) {
			logger.error("删除的菜单id不合法");
			modelMap.addAttribute(ERROR_MESSAGE_KEY, "删除的菜单id不合法");
			modelMap.addAttribute(FORWARD_URL, "/menu/list");
			return "failure";
		}
		Menu menu = menuService.get(id);
		if (menu == null) {
			logger.error("根据菜单id,没有查询到该菜单信息！");
			modelMap.addAttribute(ERROR_MESSAGE_KEY, "根据菜单id,没有查询到该菜单信息！");
			modelMap.addAttribute(FORWARD_URL, "/menu/list");
			return "failure";
		}
		menu.setValid(EnableDisableStatus.DISABLE);
		menu.setUpdatedTime(new Date());
		menuService.update(menu);
		modelMap.addAttribute(FORWARD_URL, "/menu/list");
		return "success";
	}

	@ModelAttribute("enableDisableStatus")
	public List<EnableDisableStatus> enableDisableItems() {
		List<EnableDisableStatus> enableDisableStatus = EnableDisableStatus.list();
		return enableDisableStatus;
	}
}
