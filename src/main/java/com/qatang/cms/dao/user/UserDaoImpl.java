package com.qatang.cms.dao.user;

import com.qatang.cms.entity.user.User;
import com.qatang.cms.enums.Gender;
import com.qatang.cms.form.user.UserForm;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by JSH on 2014/7/6.
 */
public class UserDaoImpl {

    @PersistenceContext
    private EntityManager em;
    public List<User> findByCondition(UserForm userForm){
        StringBuffer hql = new StringBuffer("from User u where 1 = 1");
        if (StringUtils.isNotEmpty(userForm.getUsername())) {
            hql.append(" and u.username=:username");
        }
        if (StringUtils.isNotEmpty(userForm.getName())) {
            hql.append(" and u.name=:name");
        }
        if (StringUtils.isNotEmpty(userForm.getEmail())) {
            hql.append(" and u.email=:email");
        }
        if (StringUtils.isNotEmpty(userForm.getMobile())) {
            hql.append(" and u.mobile=:mobile");
        }
        if (StringUtils.isNotEmpty(userForm.getGenderValue())) {
            int genger = Integer.parseInt(userForm.getGenderValue());
            if (genger != Gender.ALL.getValue()) {
                hql.append(" and u.gender=:gender");
            }
        }
        Query q = em.createQuery(hql.toString());
        if (StringUtils.isNotEmpty(userForm.getUsername())) {
            q.setParameter("username", userForm.getUsername());
        }
        if (StringUtils.isNotEmpty(userForm.getName())) {
            q.setParameter("name", userForm.getName());
        }
        if (StringUtils.isNotEmpty(userForm.getEmail())) {
            q.setParameter("email", userForm.getEmail());
        }
        if (StringUtils.isNotEmpty(userForm.getMobile())) {
            q.setParameter("mobile", userForm.getMobile());
        }
        if (StringUtils.isNotEmpty(userForm.getGenderValue())) {
            int genger = Integer.parseInt(userForm.getGenderValue());
            if (genger != Gender.ALL.getValue()) {
                q.setParameter("gender", Gender.get(genger));
            }
        }
//        q.setFirstResult(0);
//        q.setMaxResults(1);
//        Page<Object[]> page = new PageImpl<Object[]>(q.getResultList(),new PageRequest(0,1), 3);
        return q.getResultList();
    }
}
