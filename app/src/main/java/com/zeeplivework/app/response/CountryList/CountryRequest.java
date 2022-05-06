package com.zeeplivework.app.response.CountryList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CountryRequest {
    @SerializedName("param")
    @Expose
    private CountryRequestBody param;
    @SerializedName("sign")
    @Expose
    private String sign;

    public CountryRequestBody getParam() {
        return param;
    }

    public void setParam(CountryRequestBody param) {
        this.param = param;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
