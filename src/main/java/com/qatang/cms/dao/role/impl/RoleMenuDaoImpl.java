package com.qatang.cms.dao.role.impl;

import com.qatang.cms.entity.role.RoleMenu;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by zhangzq on 14-8-6.
 */
public class RoleMenuDaoImpl {

    @PersistenceContext
    private EntityManager em;
    public List<RoleMenu> findRoleMenuList(Long roleId) {

        StringBuilder hql = new StringBuilder("from RoleMenu t where 1=1");
        if (roleId != null) {
            hql.append(" and t.roleId=:roleId");
        }
        Query q = em.createQuery(hql.toString());

        if (roleId != null) {
            q.setParameter("roleId", roleId);
        }
        List list = q.getResultList();
        return  list;
    }
}
