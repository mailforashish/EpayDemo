package com.zeeplivework.app.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.zeeplivework.app.R;
import com.zeeplivework.app.activity.AddBankActivity;
import com.zeeplivework.app.adapter.BankAdapter;
import com.zeeplivework.app.databinding.BankDialogBinding;
import com.zeeplivework.app.response.BankList.Bank;
import com.zeeplivework.app.response.BankList.BankListResponse;
import com.zeeplivework.app.response.BankList.BankRequest;
import com.zeeplivework.app.response.BankList.BankRequestBody;
import com.zeeplivework.app.retrofit.ApiManager;
import com.zeeplivework.app.retrofit.ApiResponseInterface;
import com.zeeplivework.app.utils.BankSelected;
import com.zeeplivework.app.utils.Constant;
import com.zeeplivework.app.utils.SignUtil;

import java.util.ArrayList;


public class BankDialog extends Dialog implements ApiResponseInterface, BankSelected {
    BankDialogBinding binding;

    ArrayList<Bank> bankArrayList = new ArrayList<>();
    BankAdapter adapter;
    ApiManager apiManager;
    Context context;
    String sKeyBank = "";
    String epayAccount = "test2020@epay.com";
    String category = "BANK";
    String currency = "INR";
    String version = "V2.0.0";
    String transactionType = "C2C";
    String countryCode = "IN";
    String pageNum = "1";
    String pageSize = "10";
    JSONObject parameters = new JSONObject();

    public BankDialog(@NonNull Context context) {
        super(context);
        this.context = context;
        init();
    }

    void init() {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.bank_dialog, null, false);
        setContentView(binding.getRoot());
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        this.setCancelable(true);

        binding.setClickListener(new EventHandler(getContext()));
        apiManager = new ApiManager(getContext(), this);
        binding.rvBank.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        // for get All bank list according to this api
        parameters.put("epayAccount", epayAccount);
        parameters.put("category", category);
        parameters.put("transactionType", transactionType);
        parameters.put("currency", currency);
        parameters.put("countryCode", countryCode);
        parameters.put("pageNum", pageNum);
        parameters.put("pageSize", pageSize);
        parameters.put("version", version);
        sKeyBank = SignUtil.createSign(parameters, "2d00b386231806ec7e18e2d96dc043aa");

        Log.e("BankDialog", "BankListKey=> " + sKeyBank);

        BankRequest bankRequest = new BankRequest();
        bankRequest.setSign(sKeyBank);
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

        show();
    }

    @Override
    public void isError(String errorCode) {
        Log.e("BankDialog", "BankListError=> " + errorCode);
    }

    @Override
    public void isSuccess(Object response, int ServiceCode) {
        if (ServiceCode == Constant.BANK_LIST) {
            BankListResponse rsp = (BankListResponse) response;
            Log.e("BankDialog", "BankList=> " + new Gson().toJson(rsp.getData()));
            bankArrayList.addAll(rsp.getData().getBankList());

            adapter = new BankAdapter(context, bankArrayList, this);
            binding.rvBank.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            try {
            } catch (Exception e) {


            }
        }


    }


    @Override
    public void getBank(boolean select, String bank) {
        if (select) {
           // ((AddBankActivity) context).setBankName(bank);
            dismiss();
        }


    }

    public class EventHandler {
        Context mContext;

        public EventHandler(Context mContext) {
            this.mContext = mContext;
        }

        public void backDialog() {
            dismiss();
        }

    }


}
