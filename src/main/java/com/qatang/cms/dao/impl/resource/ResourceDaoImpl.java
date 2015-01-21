package com.qatang.cms.dao.impl.resource;


import com.qatang.cms.entity.resource.Resource;
import com.qatang.cms.enums.EnableDisableStatus;
import com.qatang.cms.enums.ResourcesType;
import com.qatang.cms.form.PageInfo;
import com.qatang.cms.form.resource.ResourceForm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by zhangzq on 14-12-16.
 */

public class ResourceDaoImpl {

    @PersistenceContext
    private EntityManager em;
    @SuppressWarnings("unchecked")
    public List<Resource> query(ResourceForm resourceForm) {

        StringBuilder hql = new StringBuilder("from Resource u where 1=1");
        if (resourceForm != null) {
            if (StringUtils.isNotEmpty(resourceForm.getTreeLevel())) {
                hql.append(" and u.treeLevel=:treeLevel");
            }
            if (StringUtils.isNotEmpty(resourceForm.getParentID())) {
                hql.append(" and u.parentID=:parentID");
            }
        }
        hql.append(" order by u.parentID,u.priority desc");
        Query q = em.createQuery(hql.toString());
        if (resourceForm != null) {
            if (StringUtils.isNotEmpty(resourceForm.getTreeLevel())) {
                q.setParameter("treeLevel", Integer.parseInt(resourceForm.getTreeLevel()));
            }
            if (StringUtils.isNotEmpty(resourceForm.getParentID())) {
                q.setParameter("parentID", Long.parseLong(resourceForm.getParentID()));
            }
        }
        return q.getResultList();
    }

    @SuppressWarnings("unchecked")
    public Page<Resource> findAllPage(ResourceForm resourceForm){

        StringBuilder hql = new StringBuilder("from Resource u where 1=1");
        StringBuilder hqlCount = new StringBuilder("select count(u) ");

        if (resourceForm != null) {
            if (StringUtils.isNotEmpty(resourceForm.getTreeLevel()) && !"0".equals(resourceForm.getTreeLevel())) {
                hql.append(" and u.treeLevel=:treeLevel");
            }
            if (StringUtils.isNotEmpty(resourceForm.getParentID())) {
                hql.append(" and u.parentID=:parentID");
            }
            if (StringUtils.isNotEmpty(resourceForm.getQueryResourceName())) {
                hql.append(" and u.name like :queryResourceName");
            }
            if (StringUtils.isNotEmpty(resourceForm.getQueryValid())) {
                int valid = Integer.parseInt(resourceForm.getQueryValid());
                if (valid != EnableDisableStatus.ALL.getValue()) {
                    hql.append(" and u.valid=:queryValid");
                }
            }
        }
        hql.append(" order by u.path,u.priority");
        hqlCount.append(hql);
        Query q = em.createQuery(hql.toString());
        Query qc = em.createQuery(hqlCount.toString());
        if (resourceForm != null) {
            if (StringUtils.isNotEmpty(resourceForm.getTreeLevel()) && !"0".equals(resourceForm.getTreeLevel())) {
                q.setParameter("treeLevel", Integer.parseInt(resourceForm.getTreeLevel()));
                qc.setParameter("treeLevel", Integer.parseInt(resourceForm.getTreeLevel()));
            }
            if (StringUtils.isNotEmpty(resourceForm.getParentID())) {
                q.setParameter("parentID", Long.parseLong(resourceForm.getParentID()));
                qc.setParameter("parentID", Long.parseLong(resourceForm.getParentID()));
            }
            if (StringUtils.isNotEmpty(resourceForm.getQueryResourceName())) {
                q.setParameter("queryResourceName", "%" + resourceForm.getQueryResourceName().trim() + "%");
                qc.setParameter("queryResourceName", "%" + resourceForm.getQueryResourceName().trim() + "%");
            }
            if (StringUtils.isNotEmpty(resourceForm.getQueryValid())) {
                int valid = Integer.parseInt(resourceForm.getQueryValid());
                if (valid != EnableDisableStatus.ALL.getValue()) {
                    q.setParameter("queryValid", EnableDisableStatus.get(valid));
                    qc.setParameter("queryValid", EnableDisableStatus.get(valid));
                }
            }
        }
        long count = (Long)qc.getSingleResult();
        if (resourceForm == null) {
            resourceForm = new ResourceForm();
        }
        PageInfo pageInfo = resourceForm.getPageInfo();
        if (resourceForm.getPageInfo() != null) {

            q.setFirstResult(pageInfo.getOffset());
            q.setMaxResults(pageInfo.getPageSize());
        }
        List list = q.getResultList();
        if (list == null || list.isEmpty()) {
            return null;
        }
        return new PageImpl<Resource>(list, new PageRequest(pageInfo.getCurrentPage(), pageInfo.getPageSize()), count);
    }

    public List<Resource> query(Long userId, ResourcesType resourcesType) {
        StringBuilder hql = new StringBuilder("select c.* from c_user_role a inner join c_role_resource b on a.role_id = b.role_id ");
        hql.append(" inner join c_resource c on b.resource_id = c.id ");
        hql.append(" where a.user_id = :userId ");
        if (resourcesType != null && resourcesType.getValue() != ResourcesType.ALL.getValue()) {
            hql.append(" and c.type = :type ");
        }
        hql.append(" order by c.priority ");
        Query query = em.createNativeQuery(hql.toString(), Resource.class);
        query.setParameter("userId", userId);
        if (resourcesType != null && resourcesType.getValue() != ResourcesType.ALL.getValue()) {
            query.setParameter("type", resourcesType.getValue());
        }
        List<Resource> list = query.getResultList();
        return list;
    }

}


