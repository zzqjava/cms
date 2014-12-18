package com.qatang.cms.dao.menu;

import com.qatang.cms.entity.menu.Menu;
import com.qatang.cms.form.menu.MenuForm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by likunpeng on 2014/6/24.
 */
public interface MenuDao extends JpaRepository<Menu, Long> {

/*public Menu findByMenuname(String name);*/
    /**
     * 查询
     * @param menuForm
     */
    public List<Menu> query(MenuForm menuForm);

}