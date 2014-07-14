package com.qatang.cms.service.role;

import com.qatang.cms.entity.role.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by zhangzq on 2014/6/23.
 */
public interface RoleService {

    public Role save(Role user);
    public Role update(Role user);
    public void del(Role role);
    public Role getRole(Long id);
    public List<Role> findAll();
    public Page<Role> findAllPage(PageRequest pageRequest);

}
