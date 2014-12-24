package com.qatang.cms.form.role;

import com.qatang.cms.form.AbstactForm;

/**
 * Created by zhangzq on 14-8-6.
 */
public class RoleResourceForm extends AbstactForm {

    private Long roleId;
    private String resourceIds;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
    }
}
