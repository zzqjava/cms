package com.qatang.cms.dao.role;

import com.qatang.cms.entity.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by zhangzq on 2014/6/23.
 */
public interface RoleDao extends JpaRepository<Role, Long> {

}