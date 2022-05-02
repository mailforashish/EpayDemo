package com.zeeplivework.app.epay;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;

import android.annotation.SuppressLint;
import android.webkit.WebViewClient;

import java.util.Map;

import android.webkit.WebView;

public class EpayWebView {
    private PaymentResultListener listener;
    boolean firstTimeLoaded;
    private String MD5Key;
    private String urlParameters;
    private String PaymentUrl;
    private String CancelUrl;
    private Boolean ShowCancelButton;
    private WebView myWebView;

    public EpayWebView(final PaymentResultListener listener, final WebView view) {
        this.firstTimeLoaded = true;
        this.MD5Key = "";
        this.urlParameters = "";
        this.PaymentUrl = "https://ssl.ditonlinebetalingssystem.dk/integration/ewindow/";
        this.CancelUrl = "http://www.epay.dk/epay-payment-solutions/?close=1";
        this.ShowCancelButton = true;
        this.listener = listener;
        this.myWebView = view;
    }

    public EpayWebView(final PaymentResultListener listener, final WebView view, final Boolean showCancelButton) {
        this.firstTimeLoaded = true;
        this.MD5Key = "";
        this.urlParameters = "";
        this.PaymentUrl = "https://ssl.ditonlinebetalingssystem.dk/integration/ewindow/";
        this.CancelUrl = "http://www.epay.dk/epay-payment-solutions/?close=1";
        this.ShowCancelButton = true;
        this.ShowCancelButton = showCancelButton;
        this.listener = listener;
        this.myWebView = view;
    }

    public EpayWebView(final PaymentResultListener listener, final WebView view, final Boolean showCancelButton, final String MD5Key) {
        this.firstTimeLoaded = true;
        this.MD5Key = "";
        this.urlParameters = "";
        this.PaymentUrl = "https://ssl.ditonlinebetalingssystem.dk/integration/ewindow/";
        this.CancelUrl = "http://www.epay.dk/epay-payment-solutions/?close=1";
        this.ShowCancelButton = true;
        this.ShowCancelButton = showCancelButton;
        this.MD5Key = MD5Key;
        this.listener = listener;
        this.myWebView = view;
    }

    @SuppressLint({"SetJavaScriptEnabled", "WrongConstant"})
    public WebView LoadPaymentWindow(final Map<String, String> data) {
        this.listener.PaymentWindowLoading();
        try {
            this.CleanParameters(data);
            this.urlParameters = this.GenerateUrlParameters(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.myWebView.setWebViewClient((WebViewClient) new EpayWebView1());
        this.myWebView.getSettings().setJavaScriptEnabled(true);
        this.myWebView.loadUrl(String.valueOf(this.PaymentUrl) + this.urlParameters);
        this.myWebView.setScrollBarStyle(0);
        return this.myWebView;
    }

    private void CleanParameters(final Map<String, String> parameterList) {
        parameterList.remove("windowstate");
        parameterList.remove("mobile");
        parameterList.remove("cssurl");
        parameterList.remove("accepturl");
        parameterList.remove("cancelurl");
        parameterList.remove("ownreceipt");
        parameterList.remove("hash");
        parameterList.remove("smsreceipt");
    }

    private String MD5HashString(final String value) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(value.getBytes());
        final byte[] byteData = md.digest();
        final StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < byteData.length; ++i) {
            final String hex = Integer.toHexString(0xFF & byteData[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    private String GenerateMD5HashKey(final Map<String, String> parameterList) {
        String result = "";
        for (final String key : parameterList.keySet()) {
            try {
                result = String.valueOf(result) + URLDecoder.decode(parameterList.get(key), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if (this.ShowCancelButton) {
            result = String.valueOf(result) + this.CancelUrl;
        }
        result = String.valueOf(result) + "2" + "androidsdk1.0.0";
        result = String.valueOf(result) + this.MD5Key;
        Log.i("md5 values", result);
        return this.MD5HashString(result);
    }

    private Map<String, String> getQueryMap(final String query) {
        final String[] params = query.split("&");
        final Map<String, String> map = new HashMap<String, String>();
        String[] array;
        for (int length = (array = params).length, i = 0; i < length; ++i) {
            final String param = array[i];
            final String name = param.split("=")[0];
            final String value = param.split("=")[1];
            map.put(name, value);
        }
        map.remove("accept");
        map.remove("sessionid");
        map.remove("sessionkey");
        return map;
    }

    private String GenerateUrlParameters(final Map<String, String> parameterList) {
        String res = "?";
        for (final String key : parameterList.keySet()) {
            res = String.valueOf(res) + key + "=" + parameterList.get(key) + "&";
        }
        if (this.ShowCancelButton) {
            try {
                res = String.valueOf(res) + "cancelurl=" + URLEncoder.encode(this.CancelUrl, "UTF-8") + "&";
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        res = String.valueOf(res) + "mobile=2&cms=androidsdk1.0.0";
        if (this.MD5Key.length() > 0) {
            res = String.valueOf(res) + "&hash=" + this.GenerateMD5HashKey(parameterList);
        }
        Log.e("res", res);
        return res;
    }
}
