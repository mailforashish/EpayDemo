package com.zeeplivework.app.response.RequiredField;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequiredFieldRequest {
    @SerializedName("param")
    @Expose
    private RequiredFieldBody param;
    @SerializedName("sign")
    @Expose
    private String sign;

    public RequiredFieldBody getParam() {
        return param;
    }

    public void setParam(RequiredFieldBody param) {
        this.param = param;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

}
