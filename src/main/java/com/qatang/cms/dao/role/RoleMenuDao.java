package com.qatang.cms.dao.role;

import com.qatang.cms.entity.role.RoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by zhangzq on 14-8-6.
 */
public interface RoleMenuDao extends JpaRepository<RoleMenu, Long> {

    public List<RoleMenu> findByRoleId(Long roleId);

}
