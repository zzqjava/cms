package com.qatang.cms.form.role;


import com.qatang.cms.form.AbstractPagingForm;

/**
 * Created by zhangzq on 2014/6/24.
 */
public class RoleForm extends AbstractPagingForm {

    private String id;
    private String identifier;
    private String name;
    private String description;
    private String isDefault;
    private String valid;

    private String queryRoleName;
    private String queryValid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    public String getQueryRoleName() {
        return queryRoleName;
    }

    public void setQueryRoleName(String queryRoleName) {
        this.queryRoleName = queryRoleName;
    }

    public String getQueryValid() {
        return queryValid;
    }

    public void setQueryValid(String queryValid) {
        this.queryValid = queryValid;
    }
}
