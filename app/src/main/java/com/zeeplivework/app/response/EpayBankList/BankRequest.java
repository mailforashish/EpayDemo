package com.zeeplivework.app.response.EpayBankList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BankRequest {
    @SerializedName("param")
    @Expose
    private BankRequestBody param;
    @SerializedName("sign")
    @Expose
    private String sign;

    public BankRequestBody getParam() {
        return param;
    }

    public void setParam(BankRequestBody param) {
        this.param = param;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
