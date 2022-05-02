package com.zeeplivework.app.epay;

import java.util.Map;
import java.net.MalformedURLException;
import java.net.URL;
import android.webkit.WebView;
import android.webkit.WebViewClient;

class EpayWebView1 extends WebViewClient {
    public void onPageFinished(final WebView view, final String url) {
        /*if (EpayWebView.this.firstTimeLoaded) {
           // EpayWebView.access$0(EpayWebView.this).PaymentWindowLoaded();
            EpayWebView.this.firstTimeLoaded = false;
        }*/
        if (url.contains("accept=1")) {
            URL aURL = null;
            try {
                aURL = new URL(url);
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            }
           // EpayWebView.access$0(EpayWebView.this).PaymentAccepted(EpayWebView.access$1(EpayWebView.this, aURL.getQuery()));
        }
    }

    public void onLoadResource(final WebView view, final String url) {
      /*  if (!url.equals(EpayWebView.(EpayWebView.this)) && !url.contains(EpayWebView.(EpayWebView.this))) {
            //EpayWebView.access$0(EpayWebView.this).PaymentLoadingAcceptPage();
        }
        if (url.equals(new EpayWebView())) {
           // EpayWebView.access$0(EpayWebView.this).PaymentWindowCancelled();
            view.stopLoading();
        }*/
    }

    public void onReceivedError(final WebView view, final int errorCode, final String description, final String failingUrl) {
        view.stopLoading();
       // EpayWebView.access$0(EpayWebView.this).ErrorOccurred(errorCode, description, failingUrl);
    }
}