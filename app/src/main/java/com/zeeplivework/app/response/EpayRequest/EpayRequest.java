package com.zeeplivework.app.response.EpayRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EpayRequest {
    @SerializedName("param")
    @Expose
    private EpayRequestBody param;
    @SerializedName("sign")
    @Expose
    private String sign;

    public EpayRequestBody getParam() {
        return param;
    }

    public void setParam(EpayRequestBody param) {
        this.param = param;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
