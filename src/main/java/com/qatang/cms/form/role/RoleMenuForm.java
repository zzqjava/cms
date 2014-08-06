package com.qatang.cms.form.role;

import com.qatang.cms.form.AbstractPagingForm;

/**
 * Created by zhangzq on 14-8-6.
 */
public class RoleMenuForm extends AbstractPagingForm {

    private Long id;
    private Long roleId;
    private Long menuId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }
}
