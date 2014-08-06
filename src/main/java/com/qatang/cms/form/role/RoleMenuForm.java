package com.qatang.cms.form.role;

import com.qatang.cms.form.AbstractPagingForm;

/**
 * Created by zhangzq on 14-8-6.
 */
public class RoleMenuForm extends AbstractPagingForm {

    private String id;
    private String roleId;
    private String menuId;
    private String menuIds;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(String menuIds) {
        this.menuIds = menuIds;
    }
}
