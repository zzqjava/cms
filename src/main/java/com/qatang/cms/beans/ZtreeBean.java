package com.qatang.cms.beans;

import java.io.Serializable;

public class ZtreeBean implements Serializable {

	private static final long serialVersionUID = 4978471057820080944L;
	
	private String id;
	private String pId;
	private String name;
	private boolean checked;
	private boolean open = true;

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

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}
}
