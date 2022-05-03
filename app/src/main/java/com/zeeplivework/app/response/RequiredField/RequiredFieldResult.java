package com.zeeplivework.app.response.RequiredField;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequiredFieldResult {
    @SerializedName("channelCode")
    @Expose
    private String channelCode;
    @SerializedName("senderOrReceiver")
    @Expose
    private Integer senderOrReceiver;
    @SerializedName("currency")
    @Expose
    private String currency;

    @SerializedName("showName")
    @Expose
    private String showName;
    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("maxLength")
    @Expose
    private Integer maxLength;
    @SerializedName("required")
    @Expose
    private Integer required;

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public Integer getSenderOrReceiver() {
        return senderOrReceiver;
    }

    public void setSenderOrReceiver(Integer senderOrReceiver) {
        this.senderOrReceiver = senderOrReceiver;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

    public Integer getRequired() {
        return required;
    }

    public void setRequired(Integer required) {
        this.required = required;
    }

}
