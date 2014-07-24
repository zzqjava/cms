package com.qatang.cms.dao.role;

import com.qatang.cms.entity.role.Role;
import com.qatang.cms.enums.EnableDisableStatus;
import com.qatang.cms.form.role.RoleForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by zhangzq on 2014/6/23.
 */
public interface RoleDao extends JpaRepository<Role, Long> {

    public Page<Role> findAllPage(RoleForm roleForm);

    Page<Role> findByValid(EnableDisableStatus valid, Pageable pageable);

}
