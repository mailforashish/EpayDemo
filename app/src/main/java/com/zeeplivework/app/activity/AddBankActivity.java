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
import com.zeeplivework.app.response.CreateTransaction.ReceiverInfo;
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
import java.util.FormatFlagsConversionMismatchException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import static com.zeeplivework.app.utils.SessionManager.BANK_BRANCH;
import static com.zeeplivework.app.utils.SessionManager.BANK_ID;
import static com.zeeplivework.app.utils.SessionManager.BANK_NAME;
import static com.zeeplivework.app.utils.SessionManager.CITY;
import static com.zeeplivework.app.utils.SessionManager.COUNTRY_CODE;
import static com.zeeplivework.app.utils.SessionManager.COUNTRY_NAME;
import static com.zeeplivework.app.utils.SessionManager.CURRENCY_CODE;
import static com.zeeplivework.app.utils.SessionManager.LOCATION_ID;
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

    String SurName = "";
    String GivName = "";
    String middleName = "";
    String Phone = "";
    String Country = "";
    String LocationId = "";
    String BankName = "";
    String bankId = "";
    String Email = "";
    String Area = "";
    String otherName = "";
    String nationality = "";
    String accountNo = "";
    String bankBranch = "";
    String bankBranchName = "";
    String address = "";
    String city = "";
    String currency = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bank);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_bank);
        binding.setClickListener(new EventHandler(this));
        apiManager = new ApiManager(this, this);

        searchWordList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.searchWordsArray)));

        HashMap<String, String> data = new SessionManager(getApplicationContext()).getCountryDetails();
        receiveCurrency = data.get(CURRENCY_CODE);
        countryCode = data.get(COUNTRY_CODE);
        transactionType = data.get(TRANSACTION_TYPE);
        Country = data.get(COUNTRY_NAME);

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

            for (int i = 0; i < listnew.size(); i++) {
                for (String search : searchWordList) {
                    listnew.removeIf(item -> item.getValue().equals(search));
                }
            }

           /* for (int i = listnew.size() - 1; i >= 0; i--) {
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

            }*/

            requiredFieldAdapter = new RequiredFieldAdapter(AddBankActivity.this, listnew);
            binding.rvAddBank.setAdapter(requiredFieldAdapter);
            requiredFieldAdapter.notifyDataSetChanged();
        }
        if (ServiceCode == Constant.CREATE_TRANSACTION) {
            CreateTransactionResponse rsp = (CreateTransactionResponse) response;
            Log.e("AddBank", "TransactiData=> " + new Gson().toJson(rsp.getData()));
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
           /* for (int i = 0; i < listnew.size(); i++) {
                for (Map.Entry<String, String> entry : RequiredFieldAdapter.fillForm.entrySet()) {
                    Log.e("FormValue", "AllValue=> " + entry.getKey() + ":" + RequiredFieldAdapter.fillForm.get(entry.getKey()));
                    if (listnew.get(i).getValue().equals(entry.getKey())) {
                        SurName = String.valueOf(entry.getValue());
                        Log.e("surNamelOG", "SURNAME=> " + entry.getKey() + ":" + RequiredFieldAdapter.fillForm.get(entry.getKey()));
                    } else if (listnew.get(i).getValue().equals(entry.getKey())) {
                        GivName = String.valueOf(entry.getValue());
                        Log.e("GivNamelOG", "GivName=> " + entry.getKey() + ":" + RequiredFieldAdapter.fillForm.get(entry.getKey()));
                    } else if (listnew.get(i).getValue().equals(entry.getKey())) {
                        Phone = String.valueOf(entry.getValue());
                        Log.e("PhonelOG", "Phone=> " + entry.getKey() + ":" + RequiredFieldAdapter.fillForm.get(entry.getKey()));
                    } else if (listnew.get(i).getValue().equals(entry.getKey())) {
                        Account = String.valueOf(entry.getValue());
                        Log.e("AccountlOG", "Account=> " + entry.getKey() + ":" + RequiredFieldAdapter.fillForm.get(entry.getKey()));
                    }
                }

            }*/
            // int i = 0 ;
            /*for (Map.Entry<String, String> es : RequiredFieldAdapter.fillForm.entrySet()) {
                if (i >= listnew.size()) {
                    break;
                }
                if (RequiredFieldAdapter.fillForm.containsKey(es.getKey())) {
                    Log.e("This does work:", "first=> " + es.getKey() + ":" + RequiredFieldAdapter.fillForm.get(es.getKey()));
                     if (listnew.get(i).getValue().equals(es.getKey())) {
                        SurName = String.valueOf(es.getValue());
                    } else if (listnew.get(i).getValue().equals(es.getKey())) {
                        GivName = String.valueOf(es.getValue());
                        // break;
                    } else if (listnew.get(i).getValue().equals(es.getKey())) {
                        Account = String.valueOf(es.getValue());
                    } else if (listnew.get(i).getValue().equals(es.getKey())) {
                        Phone = String.valueOf(es.getValue());
                    }
                } else {
                    Log.e("This does work: ", "seconds" + es.getKey() + ":" + es.getValue());
                }
                i++;
            }*/

            Log.e("TestingDaat", "mapsize" + RequiredFieldAdapter.fillForm.size());
            Log.e("TestingDaat", "mapsize" + listnew.size());
            // Log.e("TestingDaat", "SaveFormData" + data);
            if (listnew.size() == RequiredFieldAdapter.fillForm.size()) {
                TransferTransaction();
                Toast.makeText(AddBankActivity.this, "Success", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(AddBankActivity.this, "Fill Required Field", Toast.LENGTH_SHORT).show();
            }

        }
    }

    public void getValueFromAdapter(String category, String value) {
        switch (category) {
            case "surName":
                SurName = value;
                break;
            case "givName":
                GivName = value;
                break;
            case "middleName":
                middleName = value;
                break;
            case "accountNo":
                accountNo = value;
                break;
            case "phone":
                Phone = value;
                break;

        }
    }

    public void TransferTransaction() {
        HashMap<String, String> data = new SessionManager(getApplicationContext()).getFormDetails();
        LocationId = data.get(LOCATION_ID);
        BankName = data.get(BANK_NAME);
        bankId = data.get(BANK_ID);
        bankBranchName = data.get(BANK_BRANCH);
        bankBranch = data.get(BANK_BRANCH);

        SortedMap<String, Object> transMap = new TreeMap<>();
        String epayAccount = "test2020@epay.com";
        String category = "BANK";
        String notifyUrl = "http://localhost/paymentApi/channel/send.do";
        String merchantOrderNo = "IN09160020";
        String amount = "1";
        String receiveAmount = "1";
        String settlementCurrency = "USD";
        //String receiveCurrency = "";
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
        senderInfo.setAccountNo("");
        senderInfo.setIdNumber("A199267867");
        senderInfo.setIdType("1");
        senderInfo.setBirthday("1986-09-11");
        senderInfo.setCountry("INDIA");
        senderInfo.setNationality("IN");
        senderInfo.setCity("noida");
        senderInfo.setAddress("H 161, bsi building, noida 63");
        senderInfo.setIdNumber("T2091272");
        senderInfo.setBirthday("1976-02-27");
        senderInfo.setPurposeOfRemittance("1");

        ReceiverInfo receiverInfo = new ReceiverInfo();
        receiverInfo.setSurName(SurName);
        receiverInfo.setGivName(GivName);
        receiverInfo.setCountry(Country);
        receiverInfo.setIdType("1");
        receiverInfo.setOccupation("1");
        receiverInfo.setArea("63");
        receiverInfo.setPhone(Phone);
        receiverInfo.setAccountNo(accountNo);
        receiverInfo.setNationality(countryCode);
        receiverInfo.setAddress(bankBranch);

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
        createTransactionBody.setSenderInfo(senderInfo);
        createTransactionBody.setReceiverInfo(receiverInfo);
        createTransactionRequest.setParam(createTransactionBody);

        apiManager.createTransaction(createTransactionRequest);

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

