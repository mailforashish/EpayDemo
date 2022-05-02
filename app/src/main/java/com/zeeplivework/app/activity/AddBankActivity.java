package com.zeeplivework.app.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.zeeplivework.app.R;
import com.zeeplivework.app.databinding.ActivityAddBankBinding;
import com.zeeplivework.app.response.BankList.BankListResponse;
import com.zeeplivework.app.response.EpayBankList.BankRequest;
import com.zeeplivework.app.response.EpayBankList.BankRequestBody;
import com.zeeplivework.app.response.EpayRequest.EpayRequest;
import com.zeeplivework.app.response.EpayRequest.EpayRequestBody;
import com.zeeplivework.app.retrofit.ApiManager;
import com.zeeplivework.app.retrofit.ApiResponseInterface;
import com.zeeplivework.app.utils.Constant;
import com.zeeplivework.app.utils.SignUtil;

import java.util.SortedMap;
import java.util.TreeMap;

public class AddBankActivity extends AppCompatActivity implements ApiResponseInterface {
    ActivityAddBankBinding binding;
    String sKey = "";
    String epayAccount = "test2020@epay.com";
    String category = "BANK";
    String currency = "INR";
    String version = "V2.0.0";
    String transactionType = "C2C";
    String countryCode = "IN";
    String pageNum = "1";
    String pageSize = "10";
    String sign = "2d00b386231806ec7e18e2d96dc043aa";
    SortedMap<String, Object> map = new TreeMap<>();
    ApiManager apiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bank);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_bank);
        binding.setClickListener(new EventHandler(this));
        apiManager = new ApiManager(this, this);

        map.put("epayAccount", epayAccount);
        map.put("category", category);
        map.put("transactionType", transactionType);
        map.put("currency", currency);
        map.put("CountryCode", countryCode);
        map.put("pageNum", pageNum);
        map.put("pageSize", pageSize);
        map.put("version", version);

        sKey = SignUtil.createSign(map,sign);
        Log.e("AddBankActivity", "AddBankSignKey=> " + sKey);

        BankRequest bankRequest = new BankRequest();
        bankRequest.setSign(sKey);

        BankRequestBody bankRequestBody = new BankRequestBody();
        bankRequestBody.setEpayAccount(epayAccount);
        bankRequestBody.setCategory(category);
        bankRequestBody.setTransactionType(transactionType);
        bankRequestBody.setCurrency(currency);
        bankRequestBody.setCountryCode(countryCode);
        bankRequestBody.setPageNum(pageNum);
        bankRequestBody.setPageSize(pageSize);
        bankRequestBody.setVersion(version);
        bankRequest.setParam(bankRequestBody);
        apiManager.getBankListDetails(bankRequest);


    }

    @Override
    public void isError(String errorCode) {
        Log.e("EPAYLOG", "BankListError=> " + errorCode);

    }

    @Override
    public void isSuccess(Object response, int ServiceCode) {
        if (ServiceCode == Constant.BANK_LIST) {
            BankListResponse rsp = (BankListResponse) response;
            Log.e("EPAYLOG", "BankList=> " + rsp.getData());

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

        public void saveContinue() {

        }
    }
}