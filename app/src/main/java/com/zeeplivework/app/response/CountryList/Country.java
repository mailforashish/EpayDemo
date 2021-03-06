package com.zeeplivework.app.response.CountryList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Country {
    @SerializedName("countryCode")
    @Expose
    private String countryCode;
    @SerializedName("enName")
    @Expose
    private String enName;
    @SerializedName("cnName")
    @Expose
    private String cnName;

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

}




