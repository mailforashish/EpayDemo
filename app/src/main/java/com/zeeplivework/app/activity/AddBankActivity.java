package com.zeeplivework.app.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.zeeplivework.app.R;
import com.zeeplivework.app.adapter.CountryAdapter;
import com.zeeplivework.app.adapter.RequiredFieldAdapter;
import com.zeeplivework.app.databinding.ActivityAddBankBinding;
import com.zeeplivework.app.dialog.BankDialog;
import com.zeeplivework.app.response.CurrencyList.CurrenciesResult;
import com.zeeplivework.app.response.RequiredField.RequiredFieldBody;
import com.zeeplivework.app.response.RequiredField.RequiredFieldRequest;
import com.zeeplivework.app.response.RequiredField.RequiredFieldResponse;
import com.zeeplivework.app.response.RequiredField.RequiredFieldResult;
import com.zeeplivework.app.retrofit.ApiManager;
import com.zeeplivework.app.retrofit.ApiResponseInterface;
import com.zeeplivework.app.utils.Constant;
import com.zeeplivework.app.utils.SignUtil;

import java.util.ArrayList;
import java.util.SortedMap;
import java.util.TreeMap;

public class AddBankActivity extends AppCompatActivity implements ApiResponseInterface {
    ActivityAddBankBinding binding;
    String sKey = "";
    String epayAccount = "test2020@epay.com";
    String category = "BANK";
    String receiveCurrency = "INR";
    String countryCode = "IN";
    String version = "V2.0.0";
    String transactionType = "C2C";
    SortedMap<String, Object> map = new TreeMap<>();
    ApiManager apiManager;
    ArrayList<RequiredFieldResult> list = new ArrayList<>();
    RequiredFieldAdapter requiredFieldAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bank);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_bank);
        binding.setClickListener(new EventHandler(this));
        apiManager = new ApiManager(this, this);

        map.put("epayAccount", epayAccount);
        map.put("category", category);
        map.put("receiveCurrency", receiveCurrency);
        map.put("countryCode", countryCode);
        map.put("transactionType", transactionType);
        map.put("version", version);

        sKey = SignUtil.createSign(map, "2d00b386231806ec7e18e2d96dc043aa");
        Log.e("AddBank", "RequiredKey=> " + sKey);

        // for get required field data according to this api
        RequiredFieldRequest requiredFieldRequest = new RequiredFieldRequest();
        requiredFieldRequest.setSign(sKey);

        RequiredFieldBody requiredFieldBody = new RequiredFieldBody();
        requiredFieldBody.setEpayAccount(epayAccount);
        requiredFieldBody.setCategory(category);
        requiredFieldBody.setReceiveCurrency(receiveCurrency);
        requiredFieldBody.setCountryCode(countryCode);
        requiredFieldBody.setTransactionType(transactionType);
        requiredFieldBody.setVersion(version);
        requiredFieldRequest.setParam(requiredFieldBody);
        apiManager.getRequiredField(requiredFieldRequest);

        binding.rvAddBank.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

    }

    @Override
    public void isError(String errorCode) {
        Log.e("AddBank", "BankListError=> " + errorCode);

    }

    @Override
    public void isSuccess(Object response, int ServiceCode) {
        if (ServiceCode == Constant.REQUIRED_FIELD) {
            RequiredFieldResponse rsp = (RequiredFieldResponse) response;
            Log.e("AddBank", "RequiredList=> " + new Gson().toJson(rsp.getData()));
            list.addAll(rsp.getData());
            
            requiredFieldAdapter = new RequiredFieldAdapter(AddBankActivity.this, list);
            binding.rvAddBank.setAdapter(requiredFieldAdapter);
            requiredFieldAdapter.notifyDataSetChanged();
        }

    }

    public class EventHandler {
        Context mContext;

        public EventHandler(Context mContext) {
            this.mContext = mContext;
        }

        public void backPage() {
            onBackPressed();
        }

        public void chooseBank() {
            new BankDialog(AddBankActivity.this);
        }

        public void saveContinue() {

        }
    }

   /* public void setBankName(String bankName) {
        binding.etBankName.setText(bankName);
    }*/


}