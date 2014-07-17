package com.qatang.cms.controller.menu;

import com.qatang.cms.controller.BaseController;
import com.qatang.cms.entity.menu.Menu;
import com.qatang.cms.enums.EnableDisableStatus;
import com.qatang.cms.exception.validator.ValidateFailedException;
import com.qatang.cms.form.menu.MenuForm;
import com.qatang.cms.service.menu.MenuService;
import com.qatang.cms.validator.IValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
	public String input(ModelMap modelMap) {
		List<EnableDisableStatus> enableDisableStatus = EnableDisableStatus.list();
		modelMap.addAttribute("enableDisableStatus", enableDisableStatus);
		return "/menu/menuInput";
	}

	@RequestMapping(value = "/input/{id}", method = RequestMethod.GET)
	public String input(@PathVariable Long id, ModelMap modelMap) {
		if (id != null) {
			Menu menu = menuService.get(id);
			MenuForm menuForm = new MenuForm();
			menuForm.setId(menu.getId());
			menuForm.setName(menu.getName());
			menuForm.setUrl(menu.getUrl());
			menuForm.setOrderLevelValue(menu.getOrderLevel() + "");
			menuForm.setValidValue(menu.getValid().getValue() + "");
			menuForm.setMemo(menu.getMemo());
			modelMap.addAttribute("menuForm", menuForm);
		}
		List<EnableDisableStatus> enableDisableStatus = EnableDisableStatus.list();
		modelMap.addAttribute("enableDisableStatus", enableDisableStatus);
		return "/menu/menuInput";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(MenuForm menuForm, ModelMap modelMap) {
		try {
			menuValidator.validate(menuForm);
		} catch (ValidateFailedException e) {
			logger.error(e.getMessage(), e);
			modelMap.addAttribute(ERROR_MESSAGE_KEY, e.getMessage());
			List<EnableDisableStatus> enableDisableStatus = EnableDisableStatus.list();
			modelMap.addAttribute("enableDisableStatus", enableDisableStatus);
			modelMap.addAttribute("menuForm", menuForm);
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
		if (menuForm.getId() != null) {
			try {
				menuValidator.validate(menuForm);
			} catch (ValidateFailedException e) {
				logger.error(e.getMessage(), e);
				modelMap.addAttribute(ERROR_MESSAGE_KEY, e.getMessage());
				List<EnableDisableStatus> enableDisableStatus = EnableDisableStatus.list();
				modelMap.addAttribute("enableDisableStatus", enableDisableStatus);
				modelMap.addAttribute("menuForm", menuForm);
				return "menu/menuInput";
			}
			Menu menu = menuService.get(menuForm.getId());
			if (menu ==null) {
				menuForm.setErrorMessage("根据菜单id,没有查询到该菜单！");
				return "/menu/menuInput";
			}
			menu.setName(menuForm.getName());
			menu.setName(menuForm.getName());
			menu.setUrl(menuForm.getUrl());
			menu.setOrderLevel(Integer.valueOf(menuForm.getOrderLevelValue()));
			menu.setValid(EnableDisableStatus.get(Integer.valueOf(menuForm.getValidValue())));
			menu.setMemo(menuForm.getMemo());
			menu.setUpdatedTime(new Date());
			menuService.save(menu);
		} else {
			logger.error("菜单id为空");
			modelMap.addAttribute(ERROR_MESSAGE_KEY, "菜单id为空");
			return "/menu/menuInput";
		}
		modelMap.addAttribute(FORWARD_URL, "/menu/list");
		return "success";
	}

	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable Long id, ModelMap modelMap) {
		if (id == null) {
			logger.error("传递的参数菜单id为空！");
			return "/menu/input";
		}
		Menu menu = menuService.get(id);
		menu.setValid(EnableDisableStatus.DISABLE);
		menu.setUpdatedTime(new Date());
		menuService.save(menu);
		modelMap.addAttribute(FORWARD_URL, "/menu/list");
		return "success";
	}
}