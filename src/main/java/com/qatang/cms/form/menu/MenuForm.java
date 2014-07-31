package com.qatang.cms.form.menu;

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
}
