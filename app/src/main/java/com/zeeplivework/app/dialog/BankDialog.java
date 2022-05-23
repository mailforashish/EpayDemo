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
import com.google.gson.Gson;
import com.zeeplivework.app.R;
import com.zeeplivework.app.adapter.BankAdapter;
import com.zeeplivework.app.databinding.BankDialogBinding;
import com.zeeplivework.app.response.BankList.Bank;
import com.zeeplivework.app.response.BankList.BankListResponse;
import com.zeeplivework.app.retrofit.ApiManager;
import com.zeeplivework.app.retrofit.ApiResponseInterface;
import com.zeeplivework.app.utils.BankSelected;
import com.zeeplivework.app.utils.Constant;
import com.zeeplivework.app.utils.PaginationAdapterCallback;
import com.zeeplivework.app.utils.PaginationScrollListener;
import com.zeeplivework.app.utils.SessionManager;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.zeeplivework.app.utils.SessionManager.COUNTRY_CODE;
import static com.zeeplivework.app.utils.SessionManager.CURRENCY_CODE;


public class BankDialog extends Dialog implements ApiResponseInterface, PaginationAdapterCallback {
    BankDialogBinding binding;

    List<Bank> bankArrayList = new ArrayList<>();
    BankAdapter adapter;
    ApiManager apiManager;
    Context context;
    String currency = "";
    String transactionType = "";
    String countryCode = "";
    String pageSize = "10";
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

        apiManager.getBankListDetails(countryCode, currency, transactionType);

        binding.rvBank.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                pageNum += 1;
                new Handler().postDelayed(() -> apiManager.getBankListNextPage(countryCode, currency, transactionType), 500);
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
