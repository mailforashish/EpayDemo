package com.zeeplivework.app.response.BankList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BankListResponse {
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
    private BankResult data;

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

    public BankResult getData() {
        return data;
    }

    public void setData(BankResult data) {
        this.data = data;
    }

}

