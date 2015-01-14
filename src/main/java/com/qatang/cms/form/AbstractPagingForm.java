package com.qatang.cms.form;

/**
 * Created by qatang on 14-7-10.
 */
public class AbstractPagingForm extends AbstactForm {

    private PageInfo pageInfo;
    private String pageString;

    public PageInfo getPageInfo() {
        if (pageInfo == null) {
            pageInfo = new PageInfo();
        }
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public String getPageString() {
        return pageString;
    }

    public void setPageString(String pageString) {
        this.pageString = pageString;
    }
}
