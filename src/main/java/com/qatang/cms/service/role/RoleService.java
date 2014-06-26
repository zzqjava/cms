package com.qatang.cms.service.role;

import com.qatang.cms.entity.role.Role;
import java.util.List;

/**
 * Created by zhangzq on 2014/6/23.
 */
public interface RoleService {

    public Role save(Role user);
    public void del(Role role);
    public Role getRole(Long id);
    public List<Role> findAll();

}
