package com.qatang.cms.entity.role;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * Created by zhangzq on 14-8-6.
 */
@Entity
@Table(name = "c_role_menu")
@DynamicInsert
@DynamicUpdate
public class RoleMenu {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "role_id")
    private Long roleId;
    @Column(name = "menu_id")
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
