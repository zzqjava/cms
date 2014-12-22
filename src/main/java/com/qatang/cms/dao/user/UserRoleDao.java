package com.qatang.cms.dao.user;

import com.qatang.cms.entity.role.Role;
import com.qatang.cms.entity.user.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by qatang on 14-6-12.
 */
public interface UserRoleDao extends JpaRepository<UserRole, Long> {

    public List<Role> findByUserId(Long userId);
    public UserRole findByUserIdAndRoleId(Long userId, Long roleId);
}
