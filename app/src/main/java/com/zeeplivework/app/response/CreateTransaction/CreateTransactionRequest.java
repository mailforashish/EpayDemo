package com.zeeplivework.app.response.CreateTransaction;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateTransactionRequest {
    @SerializedName("sign")
    @Expose
    private String sign;
    @SerializedName("param")
    @Expose
    private CreateTransactionBody param;

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public CreateTransactionBody getParam() {
        return param;
    }

    public void setParam(CreateTransactionBody param) {
        this.param = param;
    }




}
