package com.qatang.cms.form.role;


import com.qatang.cms.form.AbstractPagingForm;

/**
 * Created by zhangzq on 2014/6/24.
 */
public class RoleForm extends AbstractPagingForm {

    private String id;
    private String roleName;
    private String roleDesc;
    private String valid;

    private String queryRoleName;
    private String queryValid;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    public String getQueryRoleName() {
        return queryRoleName;
    }

    public void setQueryRoleName(String queryRoleName) {
        this.queryRoleName = queryRoleName;
    }

    public String getQueryValid() {
        return queryValid;
    }

    public void setQueryValid(String queryValid) {
        this.queryValid = queryValid;
    }
}
