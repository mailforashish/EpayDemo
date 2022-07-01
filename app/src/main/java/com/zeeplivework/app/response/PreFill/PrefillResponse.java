package com.zeeplivework.app.response.Prefill;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.zeeplivework.app.response.Prefill.PreFillResult;

public class PrefillResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("result")
    @Expose
    private PreFillResult result;
    @SerializedName("error")
    @Expose
    private String error;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public PreFillResult getResult() {
        return result;
    }

    public void setResult(PreFillResult result) {
        this.result = result;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
