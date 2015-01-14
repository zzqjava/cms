package com.qatang.cms.form;

/**
 * Created by zhangzq on 14-7-8.
 */
public class PageInfo {

    private int count = 0; // 记录总数
    private int currentPage = 1; // 当前页数
    private int totalPages = 0; // 总页数
    private int pageSize = 2; // 每页显示记录数
    private boolean pageFlag = true;//是否分页 ,默认为true分页 ,等于false时不分页

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public boolean isPageFlag() {
        return pageFlag;
    }

    public void setPageFlag(boolean pageFlag) {
        this.pageFlag = pageFlag;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getOffset() {
        return (currentPage - 1) * pageSize;
    }
}
