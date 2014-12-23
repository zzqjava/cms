package com.qatang.cms.service.impl.role;

import com.qatang.cms.dao.resource.ResourceDao;
import com.qatang.cms.dao.role.RoleDao;
import com.qatang.cms.dao.role.RoleMenuDao;
import com.qatang.cms.entity.resource.Resource;
import com.qatang.cms.entity.role.Role;
import com.qatang.cms.entity.role.RoleMenu;
import com.qatang.cms.form.role.RoleForm;
import com.qatang.cms.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangzq on 2014/6/24.
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;
    @Autowired
    private RoleMenuDao roleMenuDao;
    @Autowired
    private ResourceDao menuDao;

    @Override
    public Role save(Role user) {
        return roleDao.save(user);
    }

    @Override
    public Role update(Role user) {
        return roleDao.save(user);
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
    public RoleMenu save(RoleMenu roleMenu) {
        return roleMenuDao.save(roleMenu);
    }

    @Override
    public List<RoleMenu> findRoleMenuList(Long roleId) {
        return roleMenuDao.findByRoleId(roleId);
    }

    @Override
    public List<Resource> getByRoleId(Long roleId) {
        List<RoleMenu> roleMenus = roleMenuDao.findByRoleId(roleId);
        List<Resource> menus = new ArrayList<>();
        if(roleMenus != null){
            for(RoleMenu rm : roleMenus){
                menus.add(menuDao.findOne(rm.getMenuId()));
            }
        }
        return menus;
    }
}
