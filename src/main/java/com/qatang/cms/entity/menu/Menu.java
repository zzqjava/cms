package com.qatang.cms.entity.menu;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;
import com.qatang.cms.enums.YesNoStatus;

/**
 * Created by zhangzq on 2014/6/23.
 */
@Entity
@Table(name = "c_menu")
@DynamicInsert
@DynamicUpdate
public class Menu {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "parent_id")
    private Long parentId;
    @Column(name = "menu_name")
    private String menuName;
    @Column(name = "menu_url")
    private String menuUrl;
    @Column(name = "menu_path")
    private String menuPath;
    @Column(name = "menu_desc")
    private String menuDesc;
    @Column(name = "user_id")
    private Long userId;
    @Enumerated
    private YesNoStatus status;
    @Enumerated
    private YesNoStatus use;
    @Enumerated
    private YesNoStatus show;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_time", updatable = false)
    private Date createdTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_time")
    private Date updatedTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public String getMenuPath() {
        return menuPath;
    }

    public void setMenuPath(String menuPath) {
        this.menuPath = menuPath;
    }

    public String getMenuDesc() {
        return menuDesc;
    }

    public void setMenuDesc(String menuDesc) {
        this.menuDesc = menuDesc;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public YesNoStatus getStatus() {
        return status;
    }

    public void setStatus(YesNoStatus status) {
        this.status = status;
    }

    public YesNoStatus getUse() {
        return use;
    }

    public void setUse(YesNoStatus use) {
        this.use = use;
    }

    public YesNoStatus getShow() {
        return show;
    }

    public void setShow(YesNoStatus show) {
        this.show = show;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}
