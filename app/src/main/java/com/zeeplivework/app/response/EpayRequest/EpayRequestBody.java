package com.zeeplivework.app.response.EpayRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EpayRequestBody {
    @SerializedName("epayAccount")
    @Expose
    private String epayAccount;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("version")
    @Expose
    private String version;
    @SerializedName("transactionType")
    @Expose
    private String transactionType;

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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

}
