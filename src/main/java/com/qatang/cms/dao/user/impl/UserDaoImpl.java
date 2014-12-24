package com.qatang.cms.dao.user.impl;

import com.qatang.cms.dao.user.UserDao;
import com.qatang.cms.dao.user.UserRoleDao;
import com.qatang.cms.entity.user.User;
import com.qatang.cms.enums.EnableDisableStatus;
import com.qatang.cms.enums.Gender;
import com.qatang.cms.form.PageInfo;
import com.qatang.cms.form.user.UserForm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Created by JSH on 2014/7/6.
 */
public class UserDaoImpl {

    @PersistenceContext
    private EntityManager em;
    public Page<User> findAll(UserForm userForm) {
        StringBuffer hql = new StringBuffer("from User u where 1 = 1");
        StringBuffer hqlCount = new StringBuffer("select count(u) ");
        if (StringUtils.isNotEmpty(userForm.getUsername())) {
            hql.append(" and u.username like :username");
        }
        if (StringUtils.isNotEmpty(userForm.getName())) {
            hql.append(" and u.name like :name");
        }
        if (StringUtils.isNotEmpty(userForm.getEmail())) {
            hql.append(" and u.email like :email");
        }
        if (StringUtils.isNotEmpty(userForm.getMobile())) {
            hql.append(" and u.mobile like :mobile");
        }
        if (StringUtils.isNotEmpty(userForm.getGenderValue())) {
            int genger = Integer.parseInt(userForm.getGenderValue());
            if (genger != Gender.ALL.getValue()) {
                hql.append(" and u.gender=:gender");
            }
        }
        if (StringUtils.isNotEmpty(userForm.getValidValue())) {
            int valid = Integer.parseInt(userForm.getValidValue());
            if (valid != EnableDisableStatus.ALL.getValue()) {
                hql.append(" and u.valid=:valid");
            }
        }
        if (StringUtils.isNotEmpty(userForm.getOrderType())) {
            hql.append(" order by u." + userForm.getOrderType());
        }
        if (StringUtils.isNotEmpty(userForm.getSortType())) {
            hql.append(" " + userForm.getSortType());
        }
        hqlCount.append(hql);
        Query query = em.createQuery(hql.toString());
        Query queryCount = em.createQuery(hqlCount.toString());
        if (StringUtils.isNotEmpty(userForm.getUsername())) {
            query.setParameter("username", "%" + userForm.getUsername() + "%");
            queryCount.setParameter("username", "%" + userForm.getUsername() + "%");
        }
        if (StringUtils.isNotEmpty(userForm.getName())) {
            query.setParameter("name", "%" + userForm.getName() + "%");
            queryCount.setParameter("name", "%" + userForm.getName() + "%");
        }
        if (StringUtils.isNotEmpty(userForm.getEmail())) {
            query.setParameter("email", "%" + userForm.getEmail() + "%");
            queryCount.setParameter("email", "%" + userForm.getEmail() + "%");
        }
        if (StringUtils.isNotEmpty(userForm.getMobile())) {
            query.setParameter("mobile", "%" + userForm.getMobile() + "%");
            queryCount.setParameter("mobile", "%" + userForm.getMobile() + "%");
        }
        if (StringUtils.isNotEmpty(userForm.getGenderValue())) {
            int genger = Integer.parseInt(userForm.getGenderValue());
            if (genger != Gender.ALL.getValue()) {
                query.setParameter("gender", Gender.get(genger));
                queryCount.setParameter("gender", Gender.get(genger));
            }
        }
        if (StringUtils.isNotEmpty(userForm.getValidValue())) {
            int valid = Integer.parseInt(userForm.getValidValue());
            if (valid != EnableDisableStatus.ALL.getValue()) {
                query.setParameter("valid", EnableDisableStatus.get(valid));
                queryCount.setParameter("valid", EnableDisableStatus.get(valid));
            }
        }
        if (userForm.getPageInfo() != null) {
            PageInfo pageInfo = userForm.getPageInfo();
            query.setFirstResult(pageInfo.getOffset());
            query.setMaxResults(pageInfo.getPageSize());
        }
        long count = (Long) queryCount.getSingleResult();
        PageInfo pageInfo = userForm.getPageInfo();
        if (pageInfo.getCurrentPage() > 0) {
            query.setFirstResult(pageInfo.getOffset());
        }
        if (pageInfo.getPageSize() > 0) {
            query.setMaxResults(pageInfo.getPageSize());
        }
        return new PageImpl<User>(query.getResultList(), new PageRequest(pageInfo.getCurrentPage(), pageInfo.getPageSize()), count);
    }
}
