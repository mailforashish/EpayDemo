package com.zeeplivework.app.response.CreateTransaction;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateTransactionBody {
    @SerializedName("epayAccount")
    @Expose
    private String epayAccount;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("notifyUrl")
    @Expose
    private String notifyUrl;
    @SerializedName("merchantOrderNo")
    @Expose
    private String merchantOrderNo;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("receiveAmount")
    @Expose
    private String receiveAmount;
    @SerializedName("settlementCurrency")
    @Expose
    private String settlementCurrency;
    @SerializedName("receiveCurrency")
    @Expose
    private String receiveCurrency;
    @SerializedName("version")
    @Expose
    private String version;
    @SerializedName("transactionType")
    @Expose
    private String transactionType;
    @SerializedName("senderInfo")
    @Expose
    private SenderInfo senderInfo;
    @SerializedName("receiverInfo")
    @Expose
    private ReceiverInfo receiverInfo;

    public String getEpayAccount() {
        return epayAccount;
    }

    public void setEpayAccount(String epayAccount) {
        this.epayAccount = epayAccount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getMerchantOrderNo() {
        return merchantOrderNo;
    }

    public void setMerchantOrderNo(String merchantOrderNo) {
        this.merchantOrderNo = merchantOrderNo;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getReceiveAmount() {
        return receiveAmount;
    }

    public void setReceiveAmount(String receiveAmount) {
        this.receiveAmount = receiveAmount;
    }

    public String getSettlementCurrency() {
        return settlementCurrency;
    }

    public void setSettlementCurrency(String settlementCurrency) {
        this.settlementCurrency = settlementCurrency;
    }

    public String getReceiveCurrency() {
        return receiveCurrency;
    }

    public void setReceiveCurrency(String receiveCurrency) {
        this.receiveCurrency = receiveCurrency;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public SenderInfo getSenderInfo() {
        return senderInfo;
    }

    public void setSenderInfo(SenderInfo senderInfo) {
        this.senderInfo = senderInfo;
    }

    public ReceiverInfo getReceiverInfo() {
        return receiverInfo;
    }

    public void setReceiverInfo(ReceiverInfo receiverInfo) {
        this.receiverInfo = receiverInfo;
    }

}

