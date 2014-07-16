package com.qatang.cms.controller.menu;

import com.qatang.cms.controller.BaseController;
import com.qatang.cms.entity.menu.Menu;
import com.qatang.cms.entity.user.User;
import com.qatang.cms.enums.YesNoStatus;
import com.qatang.cms.exception.validator.ValidateFailedException;
import com.qatang.cms.form.menu.MenuForm;
import com.qatang.cms.service.menu.MenuService;
import com.qatang.cms.validator.IValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

	@RequestMapping(value = "/input")
	public String input(Long id, ModelMap modelMap) {
        if (id != null) {
            Menu menu = menuService.get(id);
            modelMap.addAttribute("menu", menu);
        }
        List<YesNoStatus> yesNoStatuses = YesNoStatus.list();
        modelMap.addAttribute("yesNoStatuses", yesNoStatuses);
		return "/menu/menuInput";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Long id, MenuForm menuForm, RedirectAttributes redirectAttributes) {
		try {
			menuValidator.validate(menuForm);
		} catch (ValidateFailedException e) {
			logger.error(e.getMessage(), e);
			menuForm.setErrorMessage(e.getMessage());
            redirectAttributes.addFlashAttribute(ERROR_MESSAGE_KEY, e.getMessage());
            return "redirect:/menu/input";
		}
		Menu menu = null;
		if (id != null) {
			menu = menuService.get(id);
			if (menu ==null) {
				menuForm.setErrorMessage("根据菜单id,没有查询到该菜单！");
				return "/menu/menuInput";
			}
			menu.setName(menuForm.getName());
			menu.setName(menuForm.getName());
			menu.setUrl(menuForm.getUrl());
			int orderValue = 0;
			try {
				orderValue = Integer.valueOf(menuForm.getOrderValue());
			} catch (Exception e) {
				String msg = String.format("菜单排序值字段格式不合法");
				logger.error(msg);
			}
			menu.setOrderView(orderValue);
			int validValue = 0;
			try {
				validValue = Integer.valueOf(menuForm.getValidValue());
			} catch (Exception e) {
				String msg = String.format("是否有效字段格式不合法");
				logger.error(msg);
			}
			menu.setValid(YesNoStatus.get(validValue));
			menu.setMemo(menuForm.getMemo());
		} else {
			menu = new Menu();
			menu.setName(menuForm.getName());
			menu.setUrl(menuForm.getUrl());
			int orderValue = 0;
			try {
				orderValue = Integer.valueOf(menuForm.getOrderValue());
			} catch (Exception e) {
				String msg = String.format("菜单排序值字段格式不合法");
				logger.error(msg);
			}
			menu.setOrderView(orderValue);
			int validValue = 0;
			try {
				validValue = Integer.valueOf(menuForm.getValidValue());
			} catch (Exception e) {
				String msg = String.format("是否有效字段格式不合法");
				logger.error(msg);
			}
            menu.setValid(YesNoStatus.get(validValue));
			menu.setMemo(menuForm.getMemo());
		}
		menuService.save(menu);
		return "success";
	}

	@RequestMapping(value = "/delete")
	public String delete(Long id) {
		if (id == null) {
			logger.error("传递的参数菜单id为空！");
			return "/menu/input";
		}
		menuService.delete(id);
		return "success";
	}
}
