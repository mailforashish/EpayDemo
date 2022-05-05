package com.zeeplivework.app.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.zeeplivework.app.R;
import com.zeeplivework.app.databinding.ActivityWalletBinding;
import com.zeeplivework.app.dialog.CountryDialog;
import com.zeeplivework.app.utils.SessionManager;
import com.zeeplivework.app.utils.SignUtil;

import java.util.SortedMap;
import java.util.TreeMap;

public class WalletActivity extends AppCompatActivity {
    ActivityWalletBinding binding;
    SessionManager sessionManager;

    String epayAccount = "zeeplive09@gmail.com";
    String version = "V2.0.0";
    String merchantName = "Zeeplive";
    String notifyUrl = "http://localhost/paymentApi/channel/send.do";
    String successUrl = "";
    String failUrl = "";
    String merchantOrderNo = "765432456";
    String receiveEpayAccount = "shahovevgen4@gmail.com";
    String amount = "1";
    String currency = "USD";
    String extendFields = "test : test";
    String sKey = "";
    SortedMap<String, Object> map = new TreeMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_wallet);
        binding.setClickListener(new EventHandler(this));
        sessionManager = new SessionManager(this);

        /*map.put("epayAccount", epayAccount);
        map.put("version", version);
        map.put("merchantName", merchantName);
        map.put("notifyUrl", notifyUrl);
        map.put("successUrl", successUrl);
        map.put("failUrl", failUrl);
        map.put("merchantOrderNo", merchantOrderNo);
        map.put("receiveEpayAccount", receiveEpayAccount);
        map.put("amount", amount);
        map.put("currency", currency);
        map.put("extendFields", extendFields);
        Log.e("AddBank", "MapValueRAjeshSir=> " + map);
        sKey = SignUtil.createSign(map, "e09de196ce7abf131659655a605c1864");
        Log.e("AddBank", "RequiredKeyNew=> " + sKey);*/

    }

    public class EventHandler {
        Context mContext;

        public EventHandler(Context mContext) {
            this.mContext = mContext;
        }

        public void backPage() {
            onBackPressed();
        }

        public void chooseCountry() {
            CountryDialog countryDialog = new CountryDialog(mContext);
            countryDialog.show();
        }

        public void addBankDetails() {
            if (TextUtils.isEmpty(binding.tvCountryNameInput.getText().toString())) {
                Toast.makeText(WalletActivity.this, "Please Select Country", Toast.LENGTH_SHORT).show();
            } else {
                startActivity(new Intent(WalletActivity.this, AddBankActivity.class));
            }


        }
    }

    public void setCountry(String country) {
        binding.tvCountryNameInput.setText(country);

    }


}