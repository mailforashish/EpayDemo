package com.zeeplivework.app.response.CurrencyList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CurrenciesResult {
    @SerializedName("countryList")
    @Expose
    private List<Country> countryList = null;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("transactionType")
    @Expose
    private String transactionType;

    public List<Country> getCountryList() {
        return countryList;
    }

    public void setCountryList(List<Country> countryList) {
        this.countryList = countryList;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

}