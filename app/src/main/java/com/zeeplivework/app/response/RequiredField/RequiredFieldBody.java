package com.zeeplivework.app.response.RequiredField;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequiredFieldBody {
    @SerializedName("epayAccount")
    @Expose
    private String epayAccount;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("receiveCurrency")
    @Expose
    private String receiveCurrency;
    @SerializedName("countryCode")
    @Expose
    private String countryCode;
    @SerializedName("transactionType")
    @Expose
    private String transactionType;
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

    public String getReceiveCurrency() {
        return receiveCurrency;
    }

    public void setReceiveCurrency(String receiveCurrency) {
        this.receiveCurrency = receiveCurrency;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}
