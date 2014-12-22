package com.qatang.cms.dao.menu.impl;


import com.qatang.cms.entity.menu.Menu;
import com.qatang.cms.form.menu.MenuForm;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by zhangzq on 14-12-16.
 */

public class MenuDaoImpl{

    @PersistenceContext
    private EntityManager em;
    @SuppressWarnings("unchecked")
    public List<Menu> query(MenuForm menuForm) {

        StringBuilder hql = new StringBuilder("from Menu u where 1=1");
        if (menuForm != null) {
            if (StringUtils.isNotEmpty(menuForm.getLevel())) {
                hql.append(" and u.level=:level");
            }
            if (StringUtils.isNotEmpty(menuForm.getParentID())) {
                hql.append(" and u.parentID=:parentID");
            }
        }
        hql.append(" order by u.parentID,u.orderLevel desc");
        Query q = em.createQuery(hql.toString());

        if (menuForm != null) {
            if (StringUtils.isNotEmpty(menuForm.getLevel())) {
                q.setParameter("level", Integer.parseInt(menuForm.getLevel()));
            }
            if (StringUtils.isNotEmpty(menuForm.getParentID())) {
                q.setParameter("parentID", Long.parseLong(menuForm.getParentID()));
            }
        }
        return q.getResultList();
    }

}


