package com.zeeplivework.app.response.Prefill;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PreFillResult {
    @SerializedName("epay_sender_info")
    @Expose
    private String epaySenderInfo;
    @SerializedName("epay_receiver_info")
    @Expose
    private String epayReceiverInfo;

    public String getEpaySenderInfo() {
        return epaySenderInfo;
    }

    public void setEpaySenderInfo(String epaySenderInfo) {
        this.epaySenderInfo = epaySenderInfo;
    }

    public String getEpayReceiverInfo() {
        return epayReceiverInfo;
    }

    public void setEpayReceiverInfo(String epayReceiverInfo) {
        this.epayReceiverInfo = epayReceiverInfo;
    }

}
