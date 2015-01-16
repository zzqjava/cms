package com.qatang.cms.dao.user;

import com.qatang.cms.entity.role.Role;
import com.qatang.cms.entity.user.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by qatang on 14-6-12.
 */
public interface UserRoleDao extends JpaRepository<UserRole, Long> {

    public List<UserRole> findByUserId(Long userId);

    public UserRole findByUserIdAndRoleId(Long userId, Long roleId);
    /**
     * 根据userId查询roles
     * @param userId
     * @return
     */
    public List<Role> findRolesByUserId(Long userId);
}
