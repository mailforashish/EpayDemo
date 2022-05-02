package com.zeeplivework.app.response.BankList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bank {
    @SerializedName("locationId")
    @Expose
    private String locationId;
    @SerializedName("bankId")
    @Expose
    private String bankId;
    @SerializedName("bankName")
    @Expose
    private String bankName;
    @SerializedName("bankBranch")
    @Expose
    private String bankBranch;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("countryCode")
    @Expose
    private String countryCode;
    @SerializedName("transactionType")
    @Expose
    private String transactionType;

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

}