package com.zeeplivework.app.response.CreateTransaction;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReceiverInfo {
    @SerializedName("idNumber")
    @Expose
    private String idNumber;
    @SerializedName("surName")
    @Expose
    private String surName;
    @SerializedName("givName")
    @Expose
    private String givName;
    @SerializedName("idType")
    @Expose
    private String idType;
    @SerializedName("occupation")
    @Expose
    private String occupation;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("issueDate")
    @Expose
    private String issueDate;
    @SerializedName("expireDate")
    @Expose
    private String expireDate;
    @SerializedName("licIssuAutho")
    @Expose
    private String licIssuAutho;
    @SerializedName("area")
    @Expose
    private String area;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("accountNo")
    @Expose
    private String accountNo;
    @SerializedName("bankMobile")
    @Expose
    private String bankMobile;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("nationality")
    @Expose
    private String nationality;

    @SerializedName("bankName")
    @Expose
    private String bankName;
    @SerializedName("bankBranchName")
    @Expose
    private String bankBranchName;
    @SerializedName("bankBranchCode")
    @Expose
    private String bankBranchCode;

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

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

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getLicIssuAutho() {
        return licIssuAutho;
    }

    public void setLicIssuAutho(String licIssuAutho) {
        this.licIssuAutho = licIssuAutho;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getBankMobile() {
        return bankMobile;
    }

    public void setBankMobile(String bankMobile) {
        this.bankMobile = bankMobile;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }



}
