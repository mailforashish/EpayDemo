package com.zeeplivework.app.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;
import com.zeeplivework.app.R;
import com.zeeplivework.app.adapter.RequiredFieldAdapter;
import com.zeeplivework.app.databinding.ActivityAddBankBinding;
import com.zeeplivework.app.response.BankList.BankRequest;
import com.zeeplivework.app.response.BankList.BankRequestBody;
import com.zeeplivework.app.response.CreateTransaction.CreateTransactionBody;
import com.zeeplivework.app.response.CreateTransaction.CreateTransactionRequest;
import com.zeeplivework.app.response.CreateTransaction.CreateTransactionResponse;
import com.zeeplivework.app.response.CreateTransaction.SenderInfo;
import com.zeeplivework.app.response.RequiredField.RequiredFieldBody;
import com.zeeplivework.app.response.RequiredField.RequiredFieldRequest;
import com.zeeplivework.app.response.RequiredField.RequiredFieldResponse;
import com.zeeplivework.app.response.RequiredField.RequiredFieldResult;
import com.zeeplivework.app.retrofit.ApiManager;
import com.zeeplivework.app.retrofit.ApiResponseInterface;
import com.zeeplivework.app.utils.Constant;
import com.zeeplivework.app.utils.SessionManager;
import com.zeeplivework.app.utils.SignUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;

import static com.zeeplivework.app.utils.SessionManager.COUNTRY_CODE;
import static com.zeeplivework.app.utils.SessionManager.CURRENCY_CODE;
import static com.zeeplivework.app.utils.SessionManager.TRANSACTION_TYPE;

public class AddBankActivity extends AppCompatActivity implements ApiResponseInterface {
    ActivityAddBankBinding binding;
    String sKey = "";
    String epayAccount = "test2020@epay.com";
    String category = "BANK";
    String receiveCurrency = "AUD";
    String countryCode = "AU";
    String version = "V2.0.0";
    String transactionType = "C2C";
    SortedMap<String, Object> map = new TreeMap<>();
    ApiManager apiManager;
    List<RequiredFieldResult> list = new ArrayList<>();
    List<RequiredFieldResult> listnew = new ArrayList<>();
    RequiredFieldAdapter requiredFieldAdapter;
    private List<String> searchWordList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bank);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_bank);
        binding.setClickListener(new EventHandler(this));
        apiManager = new ApiManager(this, this);

        //searchWordList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.searchWordsArray)));

        HashMap<String, String> data = new SessionManager(getApplicationContext()).getCountryDetails();
        receiveCurrency = data.get(CURRENCY_CODE);
        countryCode = data.get(COUNTRY_CODE);
        transactionType = data.get(TRANSACTION_TYPE);

        map.put("epayAccount", epayAccount);
        map.put("category", category);
        map.put("receiveCurrency", receiveCurrency);
        map.put("countryCode", countryCode);
        map.put("transactionType", transactionType);
        map.put("version", version);
        Log.e("AddBank", "MapValue=> " + map);

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

        binding.rvAddBank.setHasFixedSize(true);
        binding.rvAddBank.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        RequiredFieldAdapter.fillForm.clear();

    }


    @Override
    public void isError(String errorCode) {
        Log.e("AddBank", "BankListError=> " + errorCode);

    }

    @Override
    public void isSuccess(Object response, int ServiceCode) {
        list.clear();
        if (ServiceCode == Constant.REQUIRED_FIELD) {
            RequiredFieldResponse rsp = (RequiredFieldResponse) response;
            Log.e("AddBank", "RequiredList=> " + new Gson().toJson(rsp.getData()));
            list.addAll(rsp.getData());

            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getSenderOrReceiver() == 2 && list.get(i).getRequired() == 1) {
                    listnew.add(list.get(i));
                }
            }

            for (int i = listnew.size() - 1; i >= 0; i--) {
                if (listnew.get(i).getValue().contains("address")) {
                    listnew.remove(i);
                } else if (listnew.get(i).getValue().contains("bankId")) {
                    listnew.remove(i);
                } else if (listnew.get(i).getValue().contains("city")) {
                    listnew.remove(i);
                } else if (listnew.get(i).getValue().contains("bankBranch")) {
                    listnew.remove(i);
                } else if (listnew.get(i).getValue().contains("locationId")) {
                    listnew.remove(i);
                } else if (listnew.get(i).getValue().contains("bankBranchCode")) {
                    listnew.remove(i);
                } else if (listnew.get(i).getValue().contains("accountType")) {
                    listnew.remove(i);
                } else if (listnew.get(i).getValue().contains("country")) {
                    listnew.remove(i);
                } else if (listnew.get(i).getValue().contains("taxId")) {
                    listnew.remove(i);
                } else if (listnew.get(i).getValue().contains("area")) {
                    listnew.remove(i);
                } else if (listnew.get(i).getValue().contains("idType")) {
                    listnew.remove(i);
                } else if (listnew.get(i).getValue().contains("idNumber")) {
                    listnew.remove(i);
                } else if (listnew.get(i).getValue().contains("otherName")) {
                    listnew.remove(i);
                } else if (listnew.get(i).getValue().contains("nationality")) {
                    listnew.remove(i);
                } else if (listnew.get(i).getValue().contains("accountCountry")) {
                    listnew.remove(i);
                } else if (listnew.get(i).getValue().contains("bankBranchName")) {
                    listnew.remove(i);
                } else if (listnew.get(i).getValue().contains("tradingName")) {
                    listnew.remove(i);
                } else if (listnew.get(i).getValue().contains("zipCode")) {
                    listnew.remove(i);
                } else if (listnew.get(i).getValue().contains("states")) {
                    listnew.remove(i);
                } else if (listnew.get(i).getValue().contains("msisdn")) {
                    listnew.remove(i);
                } else if (listnew.get(i).getValue().contains("missdn")) {
                    listnew.remove(i);
                } else if (listnew.get(i).getValue().contains("email")) {
                    listnew.remove(i);
                } else if (listnew.get(i).getValue().contains("registrationNumber")) {
                    listnew.remove(i);
                } else if (listnew.get(i).getValue().contains("dateOfIncorporation")) {
                    listnew.remove(i);
                } else {
                }

            }

            requiredFieldAdapter = new RequiredFieldAdapter(AddBankActivity.this, listnew);
            binding.rvAddBank.setAdapter(requiredFieldAdapter);
            requiredFieldAdapter.notifyDataSetChanged();
        }
        if (ServiceCode == Constant.CREATE_TRANSACTION) {
            CreateTransactionResponse rsp = (CreateTransactionResponse) response;
            Log.e("AddBank", "RequiredList=> " + new Gson().toJson(rsp.getData()));
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
            Log.e("TestingDaat", "mapsize" + RequiredFieldAdapter.fillForm.size());
            Log.e("TestingDaat", "mapsize" + listnew.size());
            HashMap<String, String> data = new SessionManager(getApplicationContext()).getFormDetails();
            Log.e("TestingDaat", "SaveFormData" + data);

            if (listnew.size() == RequiredFieldAdapter.fillForm.size()) {
                TransferTransaction();
                Toast.makeText(AddBankActivity.this, "Success", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(AddBankActivity.this, "Fill Required Field", Toast.LENGTH_SHORT).show();
            }
            int i = 0;
            for (Map.Entry<String, Object> es : RequiredFieldAdapter.fillForm.entrySet()) {
                if (i >= listnew.size()) {
                    break;
                }
                if (RequiredFieldAdapter.fillForm.containsKey(es.getKey())) {
                    Log.e("This does work:", "first=> " + es.getKey() + ":" + RequiredFieldAdapter.fillForm.get(es.getKey()));
                } else {
                    Log.e("This does work: ", "seconds" + es.getKey() + ":" + es.getValue());
                }
                i++;
            }
        }


    }

    public void TransferTransaction() {
        SortedMap<String, Object> transMap = new TreeMap<>();
        String epayAccount = "test2020@epay.com";
        String category = "BANK";
        String notifyUrl = "";
        String merchantOrderNo = "CN0916001";
        String amount = "";
        String receiveAmount = "300";
        String settlementCurrency = "USD";
        String receiveCurrency = "CNY";
        String version = "V2.0.0";
        String transactionType = "C2C";

        transMap.put("epayAccount", epayAccount);
        transMap.put("category", category);
        transMap.put("notifyUrl", notifyUrl);
        transMap.put("merchantOrderNo", merchantOrderNo);
        transMap.put("amount", amount);
        transMap.put("receiveAmount", receiveAmount);
        transMap.put("settlementCurrency", settlementCurrency);
        transMap.put("receiveCurrency", receiveCurrency);
        transMap.put("transactionType", transactionType);
        transMap.put("version", version);
        Log.e("AddBank", "TransactionMapValue=> " + transMap);
        String transKey = SignUtil.createSign(transMap, "2d00b386231806ec7e18e2d96dc043aa");
        CreateTransactionRequest createTransactionRequest = new CreateTransactionRequest();
        createTransactionRequest.setSign(transKey);

        SenderInfo senderInfo = new SenderInfo();
        senderInfo.setSurName("Joe");
        senderInfo.setGivName("Chang");
        senderInfo.setIdNumber("A199267867");
        senderInfo.setIdType("1");
        senderInfo.setBirthday("1986-09-11");
        senderInfo.setCountry("TW");
        senderInfo.setNationality("TW");
        senderInfo.setAddress("shenzhen");
        senderInfo.setPurposeOfRemittance("20");

        CreateTransactionBody createTransactionBody = new CreateTransactionBody();
        createTransactionBody.setEpayAccount(epayAccount);
        createTransactionBody.setCategory(category);
        createTransactionBody.setNotifyUrl(notifyUrl);
        createTransactionBody.setMerchantOrderNo(merchantOrderNo);
        createTransactionBody.setAmount(amount);
        createTransactionBody.setReceiveAmount(receiveAmount);
        createTransactionBody.setSettlementCurrency(settlementCurrency);
        createTransactionBody.setReceiveCurrency(receiveCurrency);
        createTransactionBody.setTransactionType(transactionType);
        createTransactionBody.setVersion(version);

        /*BankRequest bankRequest = new BankRequest();
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
        apiManager.getBankListDetails(bankRequest);*/

    }


}





   /* public static String getCurrencySymbol(String countryCode) {
        String currencySymbol = "";
        Locale locale = null;
        Currency currency = null;
        try {
            locale = new Locale("", countryCode);
            currency = Currency.getInstance(locale);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        if (currency != null) {
            currencySymbol = currency.getCurrencyCode();
        }
        Log.e("CurrencyCode", "is " + currencySymbol);
        return currencySymbol;
    }*/

