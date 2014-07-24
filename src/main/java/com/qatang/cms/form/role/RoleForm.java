package com.qatang.cms.form.role;


import com.qatang.cms.form.AbstractPagingForm;

/**
 * Created by zhangzq on 2014/6/24.
 */
public class RoleForm extends AbstractPagingForm {

    private Long id;
    private String roleName;
    private String roleDesc;
    private Integer valid;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
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

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }
}
