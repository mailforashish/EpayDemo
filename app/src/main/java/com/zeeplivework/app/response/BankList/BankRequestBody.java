package com.zeeplivework.app.response.BankList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BankRequestBody {
    @SerializedName("epayAccount")
    @Expose
    private String epayAccount;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("transactionType")
    @Expose
    private String transactionType;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("countryCode")
    @Expose
    private String countryCode;
    @SerializedName("pageNum")
    @Expose
    private String pageNum;
    @SerializedName("pageSize")
    @Expose
    private String pageSize;
    @SerializedName("version")
    @Expose
    private String version;

    public String getEpayAccount() {
        return epayAccount;
    }

    public void setEpayAccount(String epayAccount) {
        this.epayAccount = epayAccount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}
