package com.qatang.cms.service.impl.menu;

import com.qatang.cms.dao.menu.MenuDao;
import com.qatang.cms.entity.menu.Menu;
import com.qatang.cms.enums.YesNoStatus;
import com.qatang.cms.form.menu.MenuForm;
import com.qatang.cms.service.menu.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by likunpeng on 2014/6/24.
 */
@Service
@Transactional
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuDao menuDao;

	@Override
	public List<Menu> getList() {
		return menuDao.findAll();
	}

	@Override
	public Menu get(Long id) {
		return menuDao.findOne(id);
	}

	@Override
	public Menu save(Menu menu) {
		return menuDao.save(menu);
	}

	@Override
	public Menu update(Menu menu) {
		return menuDao.save(menu);
	}

	@Override
	public void delete(Long id) {
		menuDao.delete(id);
	}

    @Override
    public List<Menu> query(MenuForm menuForm) {
        List<Menu> menuList = menuDao.query(menuForm);
        if (menuList != null && menuList.size() > 0) {
            for (Menu menu : menuList) {
                List<Menu> secondMenuList = null;
                if (menu.getLevel() == 1 && menu.getHasChildren().getValue() == YesNoStatus.YES.getValue()) {
                    MenuForm secondMenuForm = new MenuForm();
                    secondMenuForm.setLevel("2");
                    secondMenuForm.setParentID(menu.getId() + "");
                    secondMenuList = menuDao.query(secondMenuForm);
                    if (secondMenuList != null && secondMenuList.size() > 0) {
                        for (Menu secondMenu : secondMenuList) {
                            List<Menu> thirdMenuList = null;
                            if (secondMenu.getLevel() == 2 && secondMenu.getHasChildren().getValue() == YesNoStatus.YES.getValue()) {
                                MenuForm thirdMenuForm = new MenuForm();
                                thirdMenuForm.setLevel("3");
                                thirdMenuForm.setParentID(secondMenu.getId() + "");
                                thirdMenuList = menuDao.query(thirdMenuForm);
                            }
                            if (thirdMenuList != null) {
                                secondMenu.setChildren(thirdMenuList);
                            }
                        }
                    }
                    if (secondMenuList != null) {
                        menu.setChildren(secondMenuList);
                    }
                }
            }
        }
        return menuList;
    }
}
