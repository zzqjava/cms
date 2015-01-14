package com.qatang.cms.dao.impl.role;

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
import java.util.Set;

/**
 * Created by zhangzq on 14-7-22.
 */
public class RoleDaoImpl {

    @PersistenceContext
    private EntityManager em;
    public Page<Role> findAllPage(RoleForm roleForm) {

        StringBuilder hql = new StringBuilder("from Role t where 1=1");
        StringBuilder hqlCount = new StringBuilder("select count(t) ");

        if (StringUtils.isNotEmpty(roleForm.getName())) {
            hql.append(" and t.name like :name");
        }
        if (StringUtils.isNotEmpty(roleForm.getValid())) {
            int valid = Integer.parseInt(roleForm.getValid());
            if (valid != EnableDisableStatus.ALL.getValue()) {
                hql.append(" and t.valid=:valid");
            }
        }
        hql.append(" order by t.id asc");
        hqlCount.append(hql);
        Query query = em.createQuery(hql.toString());
        Query queryCount = em.createQuery(hqlCount.toString());
        if (StringUtils.isNotEmpty(roleForm.getName())) {
            query.setParameter("name", "%" + roleForm.getName().trim() + "%");
            queryCount.setParameter("name", "%" + roleForm.getName().trim() + "%");
        }
        if (StringUtils.isNotEmpty(roleForm.getValid())) {
            int valid = Integer.parseInt(roleForm.getValid());
            if (valid != EnableDisableStatus.ALL.getValue()) {
                query.setParameter("valid", EnableDisableStatus.get(valid));
                queryCount.setParameter("valid", EnableDisableStatus.get(valid));
            }
        }

        long count = (Long)queryCount.getSingleResult();
        PageInfo pageInfo = roleForm.getPageInfo();
        if (pageInfo.getCurrentPage() > 0) {
            query.setFirstResult(pageInfo.getOffset());
        }
        if (pageInfo.getPageSize() > 0) {
            query.setMaxResults(pageInfo.getPageSize());
        }
        return new PageImpl<Role>(query.getResultList(), new PageRequest(pageInfo.getCurrentPage(), pageInfo.getPageSize()), count);
    }

    public List<Role> findDefaultRoles() {
        StringBuilder hql = new StringBuilder("from Role t where 1=1");
        hql.append(" and t.isDefault =:isDefault");
        hql.append(" order by t.id desc");
        Query query = em.createQuery(hql.toString());
        query.setParameter("isDefault", YesNoStatus.YES);
        return query.getResultList();
    }

    public List<Role> findByIds(Set<Long> ids) {
        StringBuilder hql = new StringBuilder("from Role r where 1 = 1");
        hql.append(" and r.id in :ids");
        Query query = em.createQuery(hql.toString());
        query.setParameter("ids", ids);
        return query.getResultList();
    }
}