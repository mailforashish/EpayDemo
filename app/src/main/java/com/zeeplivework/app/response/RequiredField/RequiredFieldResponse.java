package com.zeeplivework.app.response.RequiredField;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RequiredFieldResponse {
    @SerializedName("sign")
    @Expose
    private String sign;
    @SerializedName("epayAccount")
    @Expose
    private String epayAccount;
    @SerializedName("version")
    @Expose
    private String version;
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<RequiredFieldResult> data = null;

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getEpayAccount() {
        return epayAccount;
    }

    public void setEpayAccount(String epayAccount) {
        this.epayAccount = epayAccount;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<RequiredFieldResult> getData() {
        return data;
    }

    public void setData(List<RequiredFieldResult> data) {
        this.data = data;
    }
}