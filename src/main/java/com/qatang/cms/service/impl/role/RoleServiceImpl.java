package com.qatang.cms.service.impl.role;

import com.qatang.cms.dao.role.RoleDao;
import com.qatang.cms.dao.role.RoleRepository;
import com.qatang.cms.entity.role.Role;
import com.qatang.cms.enums.EnableDisableStatus;
import com.qatang.cms.form.role.RoleForm;
import com.qatang.cms.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by zhangzq on 2014/6/24.
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Role save(Role user) {
		return roleRepository.save(user);
	}

	@Override
	public Role update(Role user) {
		return roleRepository.save(user);
	}

	@Override
	public void del(Role role) {
		roleRepository.delete(role);
	}

	@Override
	public Role getRole(Long id) {
		return roleRepository.findOne(id);
	}

	@Override
	public Page<Role> findAllPage(RoleForm roleForm) {
		PageRequest pageRequest = new PageRequest(roleForm.getPageInfo().getCurrentPage() - 1, roleForm.getPageInfo().getPageSize());
		return roleRepository.findByValid(EnableDisableStatus.ENABLE, pageRequest);
	}
}