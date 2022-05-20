package com.zeeplivework.app.response.CreateTransaction;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SenderInfo {
    @SerializedName("givName")
    @Expose
    private String givName;
    @SerializedName("surName")
    @Expose
    private String surName;
    @SerializedName("nationality")
    @Expose
    private String nationality;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("area")
    @Expose
    private String area;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("idType")
    @Expose
    private String idType;
    @SerializedName("idNumber")
    @Expose
    private String idNumber;
    @SerializedName("issueDate")
    @Expose
    private String issueDate;
    @SerializedName("expireDate")
    @Expose
    private String expireDate;
    @SerializedName("birthday")
    @Expose
    private String birthday;
    @SerializedName("occupation")
    @Expose
    private String occupation;
    @SerializedName("sourceOfFund")
    @Expose
    private Integer sourceOfFund;
    @SerializedName("beneficiaryRelationShip")
    @Expose
    private String beneficiaryRelationShip;
    @SerializedName("purposeOfRemittance")
    @Expose
    private String purposeOfRemittance;

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

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public Integer getSourceOfFund() {
        return sourceOfFund;
    }

    public void setSourceOfFund(Integer sourceOfFund) {
        this.sourceOfFund = sourceOfFund;
    }

    public String getBeneficiaryRelationShip() {
        return beneficiaryRelationShip;
    }

    public void setBeneficiaryRelationShip(String beneficiaryRelationShip) {
        this.beneficiaryRelationShip = beneficiaryRelationShip;
    }

    public String getPurposeOfRemittance() {
        return purposeOfRemittance;
    }

    public void setPurposeOfRemittance(String purposeOfRemittance) {
        this.purposeOfRemittance = purposeOfRemittance;
    }


}