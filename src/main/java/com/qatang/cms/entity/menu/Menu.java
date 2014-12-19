package com.qatang.cms.entity.menu;

import com.qatang.cms.enums.EnableDisableStatus;
import com.qatang.cms.enums.YesNoStatus;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by likunpeng on 2014/6/25.
 */
@Entity
@Table(name = "c_menu")
@DynamicInsert
@DynamicUpdate
public class Menu {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String url;
	@Column(name = "order_level")
	private Integer orderLevel;
	@Enumerated
	private EnableDisableStatus valid;
	private String memo;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_time", updatable = false)
	private Date createdTime;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_time")
	private Date updatedTime;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getOrderLevel() {
		return orderLevel;
	}

	public void setOrderLevel(Integer orderLevel) {
		this.orderLevel = orderLevel;
	}

	public EnableDisableStatus getValid() {
		return valid;
	}

	public void setValid(EnableDisableStatus valid) {
		this.valid = valid;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
}