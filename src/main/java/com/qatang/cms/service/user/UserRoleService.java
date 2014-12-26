package com.qatang.cms.service.user;

import com.qatang.cms.entity.role.Role;
import com.qatang.cms.entity.user.User;
import com.qatang.cms.entity.user.UserRole;
import com.qatang.cms.form.user.UserForm;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by hewang on 14-12-24
 */
public interface UserRoleService {

    /**
     * 保存
     * @param userRoleList
     * @return
     */
    public List<UserRole> save(List<UserRole> userRoleList);

    /**
     * 删除
     * @param userRoleList
     */
    public void delete(List<UserRole> userRoleList);

    /**
     * 查询
     * @param userId
     * @return
     */
    public List<UserRole> findUserRolesByUserId(Long userId);

    /**
     *  根据userId查询roles
     * @param userId
     * @return
     */
    public List<Role> findRolesByUserId(Long userId);

}
