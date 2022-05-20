package com.zeeplivework.app.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
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
import com.zeeplivework.app.adapter.RequiredFieldAdapter;
import com.zeeplivework.app.databinding.BankDialogBinding;
import com.zeeplivework.app.response.BankList.Bank;
import com.zeeplivework.app.response.BankList.BankListResponse;
import com.zeeplivework.app.response.BankList.BankRequest;
import com.zeeplivework.app.response.BankList.BankRequestBody;
import com.zeeplivework.app.retrofit.ApiManager;
import com.zeeplivework.app.retrofit.ApiResponseInterface;
import com.zeeplivework.app.utils.BankSelected;
import com.zeeplivework.app.utils.Constant;
import com.zeeplivework.app.utils.PaginationAdapterCallback;
import com.zeeplivework.app.utils.PaginationScrollListener;
import com.zeeplivework.app.utils.SessionManager;
import com.zeeplivework.app.utils.SignUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.zeeplivework.app.utils.SessionManager.COUNTRY_CODE;
import static com.zeeplivework.app.utils.SessionManager.CURRENCY_CODE;
import static com.zeeplivework.app.utils.SessionManager.TRANSACTION_TYPE;


public class BankDialog extends Dialog implements ApiResponseInterface, PaginationAdapterCallback {
    BankDialogBinding binding;

    List<Bank> bankArrayList = new ArrayList<>();
    BankAdapter adapter;
    ApiManager apiManager;
    Context context;
    String sKeyBank = "";
    String epayAccount = "test2020@epay.com";
    String category = "BANK";
    String currency = "COP";
    String version = "V2.0.0";
    String transactionType = "";
    String countryCode = "";
    String pageSize = "10";
    JSONObject parameters = new JSONObject();
    BankSelected bankSelected;
    LinearLayoutManager linearLayoutManager;

    private static final int PAGE_START = 1;
    private boolean isLastPage = false;
    private boolean isLoading = false;
    private int TOTAL_PAGES;
    private int pageNum = PAGE_START;
    SessionManager sessionManager;

    public BankDialog(@NonNull Context context, BankSelected bankSelected) {
        super(context);
        this.context = context;
        this.bankSelected = bankSelected;
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
        sessionManager = new SessionManager(getContext());
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.rvBank.setLayoutManager(linearLayoutManager);

        HashMap<String, String> data = new SessionManager(getContext()).getCountryDetails();
        currency = data.get(CURRENCY_CODE);
        countryCode = data.get(COUNTRY_CODE);
        transactionType = sessionManager.getTransactionType();

        // for get All bank list according to this api
        parameters.put("epayAccount", epayAccount);
        parameters.put("category", category);
        parameters.put("transactionType", transactionType);
        parameters.put("currency", currency);
        parameters.put("countryCode", countryCode);
        parameters.put("pageNum", pageNum);
        parameters.put("pageSize", pageSize);
        parameters.put("version", version);
        Log.e("AddBank", "MapBankDialog=> " + parameters);

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
        bankRequestBody.setPageNum(String.valueOf(pageNum));
        bankRequestBody.setPageSize(pageSize);
        bankRequestBody.setVersion(version);
        bankRequest.setParam(bankRequestBody);
        apiManager.getBankListDetails(bankRequest);

        binding.rvBank.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                pageNum += 1;
                //showProgress();
                // mocking network delay for API call

                parameters.put("epayAccount", epayAccount);
                parameters.put("category", category);
                parameters.put("transactionType", transactionType);
                parameters.put("currency", currency);
                parameters.put("countryCode", countryCode);
                parameters.put("pageNum", pageNum);
                parameters.put("pageSize", pageSize);
                parameters.put("version", version);
                sKeyBank = SignUtil.createSign(parameters, "2d00b386231806ec7e18e2d96dc043aa");
                BankRequest bankRequest = new BankRequest();
                bankRequest.setSign(sKeyBank);
                BankRequestBody bankRequestBody = new BankRequestBody();
                bankRequestBody.setEpayAccount(epayAccount);
                bankRequestBody.setCategory(category);
                bankRequestBody.setTransactionType(transactionType);
                bankRequestBody.setCurrency(currency);
                bankRequestBody.setCountryCode(countryCode);
                bankRequestBody.setPageNum(String.valueOf(pageNum));
                bankRequestBody.setPageSize(pageSize);
                bankRequestBody.setVersion(version);
                bankRequest.setParam(bankRequestBody);

                new Handler().postDelayed(() -> apiManager.getBankListNextPage(bankRequest), 500);
            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });

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
            try {
                Log.e("BankDialog", "BankList=> " + new Gson().toJson(rsp.getData().getBankList()));
                TOTAL_PAGES = rsp.getData().getPage().getTotal();
                bankArrayList = rsp.getData().getBankList();
                if (bankArrayList.size() > 0) {
                    adapter = new BankAdapter(getContext(), bankSelected, this);
                    binding.rvBank.setAdapter(adapter);
                    adapter.addAll(bankArrayList);
                    Log.e("HistListDataList", new Gson().toJson(bankArrayList));
                    if (pageNum < TOTAL_PAGES) {
                        adapter.addLoadingFooter();
                    } else {
                        isLastPage = true;
                    }
                    adapter.notifyDataSetChanged();
                }
            } catch (Exception e) {

            }

        }
        if (ServiceCode == Constant.BANK_LIST_NEXT_PAGE) {
            BankListResponse rsp = (BankListResponse) response;
            adapter.removeLoadingFooter();
            isLoading = false;
            List<Bank> results = rsp.getData().getBankList();
            bankArrayList.addAll(results);
            adapter.addAll(results);
            adapter.notifyDataSetChanged();
            if (pageNum != TOTAL_PAGES) adapter.addLoadingFooter();
            else isLastPage = true;
        }

    }

    @Override
    public void retryPageLoad() {

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
