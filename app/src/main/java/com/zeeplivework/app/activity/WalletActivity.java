package com.zeeplivework.app.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.zeeplivework.app.R;
import com.zeeplivework.app.databinding.ActivityWalletBinding;
import com.zeeplivework.app.dialog.CountryDialog;
import com.zeeplivework.app.utils.SessionManager;

public class WalletActivity extends AppCompatActivity {
    ActivityWalletBinding binding;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_wallet);
        binding.setClickListener(new EventHandler(this));
        sessionManager = new SessionManager(this);


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
            startActivity(new Intent(WalletActivity.this, AddBankActivity.class));
        }
    }

    public void setCountry(String country ) {
        binding.tvCountryNameInput.setText(country);

    }


}