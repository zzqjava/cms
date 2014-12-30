package com.qatang.cms.service.role;

import com.qatang.cms.entity.role.Role;
import com.qatang.cms.form.role.RoleForm;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;

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
	public List<Role> findDefaultRoles();
	public List<Role> findByIds(Set<Long> ids);
}