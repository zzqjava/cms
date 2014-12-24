package com.qatang.cms.dao.role.impl;

import com.qatang.cms.entity.role.Role;
import com.qatang.cms.enums.EnableDisableStatus;
import com.qatang.cms.enums.YesNoStatus;
import com.qatang.cms.form.PageInfo;
import com.qatang.cms.form.role.RoleForm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by zhangzq on 14-7-22.
 */
public class RoleDaoImpl {

    @PersistenceContext
    private EntityManager em;
    public Page<Role> findAllPage(RoleForm roleForm){

        StringBuilder hql = new StringBuilder("from Role t where 1=1");
        StringBuilder hqlCount = new StringBuilder("select count(t) ");

        if (StringUtils.isNotEmpty(roleForm.getQueryRoleName())) {
            hql.append(" and t.name like :queryName");
        }
        if (StringUtils.isNotEmpty(roleForm.getQueryValid())) {
            int valid = Integer.parseInt(roleForm.getQueryValid());
            if (valid != EnableDisableStatus.ALL.getValue()) {
                hql.append(" and t.valid=:queryValid");
            }
        }
        hql.append(" order by t.createdTime desc");
        hqlCount.append(hql);
        Query q = em.createQuery(hql.toString());
        Query qc = em.createQuery(hqlCount.toString());
        if (StringUtils.isNotEmpty(roleForm.getQueryRoleName())) {
            q.setParameter("queryName", "%" + roleForm.getQueryRoleName().trim() + "%");
            qc.setParameter("queryName", "%" + roleForm.getQueryRoleName().trim() + "%");
        }
        if (StringUtils.isNotEmpty(roleForm.getQueryValid())) {
            int valid = Integer.parseInt(roleForm.getQueryValid());
            if (valid != EnableDisableStatus.ALL.getValue()) {
                q.setParameter("queryValid", EnableDisableStatus.get(valid));
                qc.setParameter("queryValid", EnableDisableStatus.get(valid));
            }
        }

        long count = (Long)qc.getSingleResult();
        PageInfo pageInfo = roleForm.getPageInfo();
        if (roleForm.getPageInfo() != null) {

            q.setFirstResult(pageInfo.getOffset());
            q.setMaxResults(pageInfo.getPageSize());
        }
        List list = q.getResultList();
        if (list == null || list.isEmpty()) {
            return null;
        }
        return new PageImpl<Role>(list, new PageRequest(pageInfo.getCurrentPage(), pageInfo.getPageSize()), count);
    }

    public List<Role> findDefaultRoles() {
        StringBuilder hql = new StringBuilder("from Role t where 1=1");
        hql.append(" and t.isDefault =:isDefault");
        hql.append(" order by t.id desc");
        Query query = em.createQuery(hql.toString());
        query.setParameter("isDefault", YesNoStatus.YES);
        return query.getResultList();
    }

  }
