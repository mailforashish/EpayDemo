package com.zeeplivework.app.response.CreateTransaction;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReceiverInfo {
    @SerializedName("givName")
    @Expose
    private String givName;
    @SerializedName("surName")
    @Expose
    private String surName;
    @SerializedName("locationId")
    @Expose
    private String locationId;
    @SerializedName("bankId")
    @Expose
    private String bankId;
    @SerializedName("bankName")
    @Expose
    private String bankName;
    @SerializedName("accountNo")
    @Expose
    private String accountNo;
    @SerializedName("bankBranchCode")
    @Expose
    private String bankBranchCode;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("address")
    @Expose
    private String address;

    public String getGivName() {
        return givName;
    }

    public void setGivName(String givName) {
        this.givName = givName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

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

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getBankBranchCode() {
        return bankBranchCode;
    }

    public void setBankBranchCode(String bankBranchCode) {
        this.bankBranchCode = bankBranchCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



}
