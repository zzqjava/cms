package com.qatang.cms.form.resource;

import com.qatang.cms.form.AbstractPagingForm;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by likunpeng on 2014/6/24.
 */
public class ResourceForm extends AbstractPagingForm {
	private String id;
	private String name;
	private String url;
	private String priority;
	private String validValue;
	private String memo;
	private String errorMessage;
    private String parentID;
    private String type;
    private String treeLevel;
    private String identifier;
    private String hasChildren;

    private String queryResourceName;
    private String queryValid;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getValidValue() {
        return validValue;
    }

    public void setValidValue(String validValue) {
        this.validValue = validValue;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getParentID() {
        return parentID;
    }

    public void setParentID(String parentID) {
        this.parentID = parentID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTreeLevel() {
        if (StringUtils.isEmpty(treeLevel)) {
            treeLevel = "0";
        }
        return treeLevel;
    }

    public void setTreeLevel(String treeLevel) {
        this.treeLevel = treeLevel;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(String hasChildren) {
        this.hasChildren = hasChildren;
    }

    public String getQueryResourceName() {
        return queryResourceName;
    }

    public void setQueryResourceName(String queryResourceName) {
        this.queryResourceName = queryResourceName;
    }

    public String getQueryValid() {
        return queryValid;
    }

    public void setQueryValid(String queryValid) {
        this.queryValid = queryValid;
    }
}
