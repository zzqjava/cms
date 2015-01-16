package com.qatang.cms.service.role;

import com.qatang.cms.entity.role.RoleResource;

import java.util.List;

/**
 * Created by chirowong on 2014/12/23.
 */
public interface RoleResourceService {

	public List<RoleResource> findByRoleId(Long roleId);
	public void save(Long roleId, List<RoleResource> roleResources);
}