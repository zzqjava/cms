package com.qatang.cms.entity.role;

import com.qatang.cms.enums.EnableDisableStatus;
import com.qatang.cms.enums.YesNoStatus;
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
	@Column(name = "identifier")
	private String identifier;
	@Column(name = "name")
	private String name;
	@Column(name = "description")
	private String description;
	@Enumerated
	@Column(name = "is_default")
	private YesNoStatus isDefault;
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

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public YesNoStatus getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(YesNoStatus isDefault) {
		this.isDefault = isDefault;
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
