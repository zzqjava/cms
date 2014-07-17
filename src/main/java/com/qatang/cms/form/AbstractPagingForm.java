package com.qatang.cms.form;

/**
 * Created by qatang on 14-7-10.
 */
public class AbstractPagingForm extends AbstactForm {

    private PageInfo pageInfo;

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }
}
