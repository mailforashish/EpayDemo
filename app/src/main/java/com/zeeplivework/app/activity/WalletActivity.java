package com.zeeplivework.app.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zeeplivework.app.R;

import com.zeeplivework.app.databinding.ActivityWalletBinding;
import com.zeeplivework.app.dialog.CountryDialog;
import com.zeeplivework.app.response.CountryList.CountryResponse;
import com.zeeplivework.app.response.CountryList.CountryResult;
import com.zeeplivework.app.retrofit.ApiManager;
import com.zeeplivework.app.retrofit.ApiResponseInterface;
import com.zeeplivework.app.utils.Constant;
import com.zeeplivework.app.utils.SessionManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class WalletActivity extends AppCompatActivity implements ApiResponseInterface {
    ActivityWalletBinding binding;
    SessionManager sessionManager;
    ApiManager apiManager;
    String currency = "";
    String transactionType = "";
    List<String> transaction_type_list = new ArrayList<>();
    ArrayList<CountryResult> countryList = new ArrayList<>();
    String trType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_wallet);
        binding.setClickListener(new EventHandler(this));
        sessionManager = new SessionManager(this);
        apiManager = new ApiManager(this, this);

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
            if (TextUtils.isEmpty(binding.tvCountryNameInput.getText().toString()) && TextUtils.isEmpty(trType)) {
                Toast.makeText(WalletActivity.this, "Please Select Country", Toast.LENGTH_SHORT).show();
            } else {
                startActivity(new Intent(WalletActivity.this, AddBankActivity.class));
                finishAffinity();
            }

        }
    }

    @Override
    public void isError(String errorCode) {
        Log.e("EPAYLOG", "CurrencyListError=> " + errorCode);
    }

    @Override
    public void isSuccess(Object response, int ServiceCode) {
        if (ServiceCode == Constant.CURRENCY_LIST) {
            CountryResponse rsp = (CountryResponse) response;
            countryList.clear();
            transaction_type_list.clear();
            try {
                countryList.addAll(rsp.getData());
                Log.e("EPAYLOG", "CurrencyListP2=> " + new Gson().toJson(rsp));
                for (int i = 0; i < countryList.size(); i++) {
                    transaction_type_list.add(countryList.get(i).getTransactionType());
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_transaction, transaction_type_list);
                binding.spinnerTransactionType.setAdapter(adapter);
                binding.spinnerTransactionType.setDropDownVerticalOffset(22);

                binding.spinnerTransactionType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Object item = parent.getItemAtPosition(position);
                        String text = binding.spinnerTransactionType.getSelectedItem().toString();
                        transactionType = String.valueOf(text);
                        sessionManager.setTransactionType(text);
                        Log.e("EPAYLOG", "transactionTypenew=> " + transactionType);
                        adapter.notifyDataSetChanged();
                    }
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });

            } catch (Exception e) {

            }

        }
    }
    public void setCountry(String country, String currency_code) {
        binding.tvCountryNameInput.setText(country);
        currency = currency_code;
        apiManager.getCurrencyListDetails(currency);
    }


}