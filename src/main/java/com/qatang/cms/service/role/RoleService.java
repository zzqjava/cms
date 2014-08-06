package com.qatang.cms.service.role;

import com.qatang.cms.entity.role.Role;
import com.qatang.cms.entity.role.RoleMenu;
import com.qatang.cms.form.role.RoleForm;
import org.springframework.data.domain.Page;

/**
 * Created by zhangzq on 2014/6/23.
 */
public interface RoleService {

	public Role save(Role user);
	public Role update(Role user);
	public void del(Role role);
	public Role getRole(Long id);
	public Page<Role> findAllPage(RoleForm roleForm);

    public RoleMenu save(RoleMenu roleMenu);
}