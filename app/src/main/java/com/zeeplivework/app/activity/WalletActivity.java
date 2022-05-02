package com.zeeplivework.app.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.SortedList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.alibaba.fastjson.JSONObject;
import com.zeeplivework.app.R;
import com.zeeplivework.app.databinding.ActivityWalletBinding;
import com.zeeplivework.app.dialog.CurrencyListDialog;
import com.zeeplivework.app.utils.SignUtil;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;

public class WalletActivity extends AppCompatActivity {
    ActivityWalletBinding binding;
    String sha256;
    String sha256New;
    //SortedMap<String, Object> map = new TreeMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_wallet);
        binding.setClickListener(new EventHandler(this));

       /* $params =["currency"=>"usd","epayaccount"=>"zeeplive09@gmail.com","version"=>"v2.0.0"];
        $key = "920c3cb4ff6f6ab80456b461bf5b9766";*/

       // map.put("epayAccount", "zeeplive09@gmail.com");
       // map.put("currency", "USD");
        //map.put("version", "v2.0.0");
       // SignUtil.createSign(map, "920c3cb4ff6f6ab80456b461bf5b9766");
       // Log.e("EPAYLOG", "SHA256Map" + map);

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
            CurrencyListDialog currencyListDialog = new CurrencyListDialog(mContext);
            currencyListDialog.show();
        }

        public void addBankDetails() {
            startActivity(new Intent(WalletActivity.this, AddBankActivity.class));
        }
    }

    public void setCountry(String country) {
        binding.tvCountryNameInput.setText(country);
    }


}