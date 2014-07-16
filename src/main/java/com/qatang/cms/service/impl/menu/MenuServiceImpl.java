package com.qatang.cms.service.impl.menu;

import com.qatang.cms.dao.menu.MenuDao;
import com.qatang.cms.entity.menu.Menu;
import com.qatang.cms.service.menu.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
	public void delete(Long id) {
		menuDao.delete(id);
	}
}
