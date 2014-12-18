package com.qatang.cms.form.menu;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by likunpeng on 2014/6/24.
 */
public class MenuForm {

	private String id;
	private String name;
	private String url;
	private String orderLevelValue;
	private String validValue;
	private String memo;
	private String errorMessage;
    private String parentID;
    private String resourcesType;
    private String level;
    private String permission;
    private String hasChildren;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getValidValue() {
		return validValue;
	}

	public void setValidValue(String validValue) {
		this.validValue = validValue;
	}

	public String getOrderLevelValue() {
		return orderLevelValue;
	}

	public void setOrderLevelValue(String orderLevelValue) {
		this.orderLevelValue = orderLevelValue;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

    public String getParentID() {
        return parentID;
    }

    public void setParentID(String parentID) {
        this.parentID = parentID;
    }

    public String getResourcesType() {
        return resourcesType;
    }

    public void setResourcesType(String resourcesType) {
        this.resourcesType = resourcesType;
    }

    public String getLevel() {
        if (StringUtils.isEmpty(level)) {
            level = "0";
        }
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(String hasChildren) {
        this.hasChildren = hasChildren;
    }
}
