package com.qatang.cms.service.impl.role;

import com.qatang.cms.dao.role.RoleDao;
import com.qatang.cms.entity.role.Role;
import com.qatang.cms.form.role.RoleForm;
import com.qatang.cms.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by zhangzq on 2014/6/24.
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public Role save(Role role) {
        return roleDao.save(role);
    }

    @Override
    public Role update(Role role) {
        return roleDao.save(role);
    }

    @Override
    public void del(Role role) {
        roleDao.delete(role);
    }

    @Override
    public Role getRole(Long id) {
        return roleDao.findOne(id);
    }

    @Override
    public Page<Role> findAllPage(RoleForm roleForm) {
        return roleDao.findAllPage(roleForm);
    }

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public List<Role> findDefaultRoles() {
        return roleDao.findDefaultRoles();
    }
}
