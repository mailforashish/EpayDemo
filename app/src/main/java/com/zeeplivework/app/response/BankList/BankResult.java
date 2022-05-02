package com.zeeplivework.app.response.BankList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BankResult {
    @SerializedName("page")
    @Expose
    private Page page;
    @SerializedName("bankList")
    @Expose
    private List<Bank> bankList = null;

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public List<Bank> getBankList() {
        return bankList;
    }

    public void setBankList(List<Bank> bankList) {
        this.bankList = bankList;
    }

}
