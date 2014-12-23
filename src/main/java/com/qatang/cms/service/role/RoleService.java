package com.qatang.cms.service.role;

import com.qatang.cms.entity.resource.Resource;
import com.qatang.cms.entity.role.Role;
import com.qatang.cms.entity.role.RoleMenu;
import com.qatang.cms.form.role.RoleForm;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by zhangzq on 2014/6/23.
 */
public interface RoleService {

	public Role save(Role role);
	public Role update(Role role);
	public void del(Role role);
	public Role getRole(Long id);
	public Page<Role> findAllPage(RoleForm roleForm);
	public List<Role> findAll();
    public RoleMenu save(RoleMenu roleMenu);
    public List<RoleMenu> findRoleMenuList(Long roleId);
<<<<<<< HEAD
    public List<Menu> getByRoleId(Long roleId);
	public List<Role> findDefaultRoles();
=======
    public List<Resource> getByRoleId(Long roleId);
>>>>>>> 57389f169916442c332fb7f54a6e0c5c3e52b563
}