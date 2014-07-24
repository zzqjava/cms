package com.qatang.cms.entity.role;

import com.qatang.cms.enums.EnableDisableStatus;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by zhangzq on 2014/6/23.
 */
@Entity
@Table(name = "c_role")
@DynamicInsert
@DynamicUpdate
public class Role {
	@Id
	@GeneratedValue
	private Long id;
	@Column(name = "role_name")
	private String roleName;
	@Column(name = "role_desc")
	private String roleDesc;
	@Enumerated
	private EnableDisableStatus valid;
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

	public EnableDisableStatus getValid() {
		return valid;
	}

	public void setValid(EnableDisableStatus valid) {
		this.valid = valid;
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
