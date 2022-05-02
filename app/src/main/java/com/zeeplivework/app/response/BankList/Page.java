package com.zeeplivework.app.response.BankList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Page {
    @SerializedName("pageNum")
    @Expose
    private Integer pageNum;
    @SerializedName("pageSize")
    @Expose
    private Integer pageSize;
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("pages")
    @Expose
    private Integer pages;
    @SerializedName("nextPage")
    @Expose
    private Integer nextPage;
    @SerializedName("prePage")
    @Expose
    private Integer prePage;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Integer getNextPage() {
        return nextPage;
    }

    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }

    public Integer getPrePage() {
        return prePage;
    }

    public void setPrePage(Integer prePage) {
        this.prePage = prePage;
    }

}
