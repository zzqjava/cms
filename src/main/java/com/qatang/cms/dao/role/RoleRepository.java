package com.qatang.cms.dao.role;

import com.qatang.cms.entity.role.Role;
import com.qatang.cms.enums.EnableDisableStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Created by zhangzq on 14-7-16.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    Page<Role> findByValid(EnableDisableStatus valid, Pageable pageable);

}
