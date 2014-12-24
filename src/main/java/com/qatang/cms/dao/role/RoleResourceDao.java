package com.qatang.cms.dao.role;

import com.qatang.cms.entity.role.RoleResource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by zhangzq on 14-8-6.
 */
public interface RoleResourceDao extends JpaRepository<RoleResource, Long> {
    public List<RoleResource> findByRoleId(Long roleId);
}
