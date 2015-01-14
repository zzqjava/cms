package com.qatang.cms.service.impl.user;

import com.qatang.cms.dao.user.UserRoleDao;
import com.qatang.cms.entity.role.Role;
import com.qatang.cms.entity.user.UserRole;
import com.qatang.cms.service.user.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by hewang on 14-12-24.
 */
@Service
@Transactional
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public List<UserRole> save(List<UserRole> userRoleList) {
        return userRoleDao.save(userRoleList);
    }

    @Override
    public void delete(List<UserRole> userRoleList) {
        userRoleDao.delete(userRoleList);
    }

    @Override
    public List<UserRole> findUserRolesByUserId(Long userId) {
        return userRoleDao.findByUserId(userId);
    }

    @Override
    public List<Role> findRolesByUserId(Long userId) {
        return userRoleDao.findRolesByUserId(userId);
    }
}
