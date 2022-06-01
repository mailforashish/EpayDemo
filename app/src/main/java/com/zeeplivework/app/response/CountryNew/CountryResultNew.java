package com.zeeplivework.app.response.CountryNew;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CountryResultNew {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("country_name")
    @Expose
    private String countryName;
    @SerializedName("country_code")
    @Expose
    private String countryCode;
    @SerializedName("flag")
    @Expose
    private String flag;
    @SerializedName("country_code_epay")
    @Expose
    private String countryCodeEpay;
    @SerializedName("country_currency_epay")
    @Expose
    private String countryCurrencyEpay;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCountryCodeEpay() {
        return countryCodeEpay;
    }

    public void setCountryCodeEpay(String countryCodeEpay) {
        this.countryCodeEpay = countryCodeEpay;
    }

    public String getCountryCurrencyEpay() {
        return countryCurrencyEpay;
    }

    public void setCountryCurrencyEpay(String countryCurrencyEpay) {
        this.countryCurrencyEpay = countryCurrencyEpay;
    }
}
