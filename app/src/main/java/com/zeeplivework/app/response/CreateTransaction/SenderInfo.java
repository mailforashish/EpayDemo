package com.zeeplivework.app.response.CreateTransaction;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SenderInfo {
    @SerializedName("surName")
    @Expose
    private String surName;
    @SerializedName("givName")
    @Expose
    private String givName;
    @SerializedName("accountNo")
    @Expose
    private String accountNo;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("nationality")
    @Expose
    private String nationality;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("birthday")
    @Expose
    private String birthday;
    @SerializedName("idType")
    @Expose
    private String idType;
    @SerializedName("idNumber")
    @Expose
    private String idNumber;
    @SerializedName("purposeOfRemittance")
    @Expose
    private String purposeOfRemittance;

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getGivName() {
        return givName;
    }

    public void setGivName(String givName) {
        this.givName = givName;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
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

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getPurposeOfRemittance() {
        return purposeOfRemittance;
    }

    public void setPurposeOfRemittance(String purposeOfRemittance) {
        this.purposeOfRemittance = purposeOfRemittance;
    }

}