package com.zeeplivework.app.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import com.google.android.gms.ads.rewarded.RewardedAd;
import com.zeeplivework.app.R;
import com.zeeplivework.app.databinding.ActivityMainBinding;
import com.zeeplivework.app.dialog.DiamondDialog;
import com.zeeplivework.app.epay.CreatePaymentActivity;
import com.zeeplivework.app.epay.EpayWebView;
import com.zeeplivework.app.epay.MyPaymentResultListener;
import com.zeeplivework.app.epay.PaymentDataConstructor;
import com.zeeplivework.app.utils.Constants;
import com.zeeplivework.app.utils.NetworkCheck;

@SuppressLint("SetJavaScriptEnabled")
public class MainActivity extends Activity {
    ActivityMainBinding binding;

    private NetworkCheck networkCheck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setClickListener(new EventHandler(this));

        networkCheck = new NetworkCheck();
       // Constants.loadRewardedAdd(this);
        //new DiamondDialog(MainActivity.this);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        this.getApplicationContext();
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

    public void TestPayment(View v) {

        // Check for Internet connection
        if (haveNetworkConnection()) {
            Intent intent = new Intent(this, CreatePaymentActivity.class);
            startActivity(intent);
        }

        else {
            Toast.makeText(this, "No internet connection found", Toast.LENGTH_LONG).show();
        }

    }

    public void ShowPaymentWindow(View v) {

        // Check for Internet connection
        if (haveNetworkConnection()) {
            setContentView(R.layout.activity_payment);
            WebView view = (WebView) findViewById(R.id.webView1);

            EpayWebView paymentView = new EpayWebView(new MyPaymentResultListener(this), view, true, "ePayTest1234");

            view = paymentView.LoadPaymentWindow(PaymentDataConstructor.ConstructDummyData());
        }

        else {
            Toast.makeText(this, "No internet connection found", Toast.LENGTH_LONG).show();
        }

    }




    public class EventHandler {
        Context mContext;

        public EventHandler(Context mContext) {
            this.mContext = mContext;
        }

        public void tabHome() {
            unselectAllMenu();
            ((ImageView) findViewById(R.id.iv_home_tab)).setImageResource(R.drawable.ic_tv_fill);

        }

        public void tabHealth() {
            unselectAllMenu();
            ((ImageView) findViewById(R.id.iv_health_tab)).setImageResource(R.drawable.ic_video_fill);

        }

        public void tabLab() {
            unselectAllMenu();
            ((ImageView) findViewById(R.id.iv_lab_tab)).setImageResource(R.drawable.ic_tv_fill);


        }


        public void tabAccount() {
            unselectAllMenu();
            ((ImageView) findViewById(R.id.iv_account_tab)).setImageResource(R.drawable.ic_video_fill);
        }

    }

    private void unselectAllMenu() {
        ((ImageView) findViewById(R.id.iv_home_tab)).setImageResource(R.drawable.ic_tv_blank);
        ((ImageView) findViewById(R.id.iv_health_tab)).setImageResource(R.drawable.ic_video_blank);
        ((ImageView) findViewById(R.id.iv_lab_tab)).setImageResource(R.drawable.ic_tv_blank);
        ((ImageView) findViewById(R.id.iv_account_tab)).setImageResource(R.drawable.ic_video_blank);

    }



}
