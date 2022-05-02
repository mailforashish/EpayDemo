package com.zeeplivework.app.epay;

import java.util.Map;

public interface PaymentResultListener {
    void PaymentAccepted(final Map<String, String> p0);

    void PaymentWindowLoading();

    void PaymentWindowLoaded();

    void PaymentWindowCancelled();

    void ErrorOccurred(final int p0, final String p1, final String p2);

    void Debug(final String p0);

    void PaymentLoadingAcceptPage();
}