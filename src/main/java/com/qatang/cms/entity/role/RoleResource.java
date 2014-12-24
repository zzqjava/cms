package com.qatang.cms.entity.role;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * Created by zhangzq on 14-8-6.
 */
@Entity
@Table(name = "c_role_resource")
@DynamicInsert
@DynamicUpdate
public class RoleResource {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "role_id")
    private Long roleId;
    @Column(name = "resource_id")
    private Long resourceId;

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

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }
}
