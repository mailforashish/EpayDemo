package com.zeeplivework.app.response;

public class CountryListNew {

    String country_name;
    String country_code;
    String currency_code;
    String transactionType;

    public CountryListNew(String country_name, String country_code, String currency_code, String transactionType) {
        this.country_name = country_name;
        this.country_code = country_code;
        this.currency_code = currency_code;
        this.transactionType = transactionType;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getCurrency_code() {
        return currency_code;
    }

    public void setCurrency_code(String currency_code) {
        this.currency_code = currency_code;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
}
