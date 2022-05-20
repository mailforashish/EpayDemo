package com.zeeplivework.app.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
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

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.FormatFlagsConversionMismatchException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import static com.zeeplivework.app.utils.SessionManager.ADDRESS;
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
    //parameter for Create transaction
    String SurName = "";
    String GivName = "";
    String MiddleName = "";
    String Phone = "";
    String Email = "";
    String OtherName = "";
    String Nationality = "";
    String AccountNo = "";
    String BankBranch = "";
    String Currency = "";
    String BankBranchCode = "";
    String MrchantOrderNo;
    String RegisteredName = "";
    String TaxId = "";
    String AccountType;
    String IdType;
    String IdNumber;
    String Area = "";
    String ZipCode;
    String States;

    String sKey = "";
    String epayAccount = "test2020@epay.com";
    String category = "BANK";
    String receiveCurrency = "AUD";
    String countryCode = "AU";
    String version = "V2.0.0";
    String transactionType = "C2C";
    SortedMap<String, Object> map = new TreeMap<>();

    String Country = "";
    String LocationId = "";
    String BankName = "";
    String BankId = "";
    String BankBranchName = "";
    String Address = "";
    String City = "";
    private List<String> searchWordList;
    ApiManager apiManager;
    List<RequiredFieldResult> list = new ArrayList<>();
    List<RequiredFieldResult> receiverList = new ArrayList<>();
    RequiredFieldAdapter requiredFieldAdapter;
    SessionManager sessionManager;
    JSONObject SenderInfo = new JSONObject();
    JSONObject ReceiverInfo = new JSONObject();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bank);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_bank);
        binding.setClickListener(new EventHandler(this));
        apiManager = new ApiManager(this, this);
        sessionManager = new SessionManager(this);

        searchWordList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.searchWordsArray)));
        HashMap<String, String> data = sessionManager.getCountryDetails();
        receiveCurrency = data.get(CURRENCY_CODE);
        countryCode = data.get(COUNTRY_CODE);
        Country = data.get(COUNTRY_NAME);
        transactionType = sessionManager.getTransactionType();

        map.put("epayAccount", epayAccount);
        map.put("category", category);
        map.put("receiveCurrency", receiveCurrency);
        map.put("countryCode", countryCode);
        map.put("transactionType", transactionType);
        map.put("version", version);
        Log.e("AddBank", "MapValue=> " + map);

        sKey = SignUtil.createSign(map, "2d00b386231806ec7e18e2d96dc043aa");
        Log.e("AddBank", "RequiredKey=> " + sKey);
        //for get required field data according to this api
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
            try {
                list.addAll(rsp.getData());
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getSenderOrReceiver() == 2 && list.get(i).getRequired() == 1) {
                        receiverList.add(list.get(i));
                    } else if (list.get(i).getSenderOrReceiver() == 1 && list.get(i).getRequired() == 1) {
                        SenderInfo.put("_" + list.get(i).getValue(), "");
                    }
                }

            /* for (int i = 0; i < listnew.size(); i++) {
                for (String search : searchWordList) {
                    listnew.removeIf(item -> item.getValue().equals(search));
                }
            }*/

                requiredFieldAdapter = new RequiredFieldAdapter(AddBankActivity.this, receiverList, Country);
                binding.rvAddBank.setAdapter(requiredFieldAdapter);
                requiredFieldAdapter.notifyDataSetChanged();
            } catch (Exception e) {

            }

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
            Log.e("TestingDaat", "mapsize" + RequiredFieldAdapter.ReceiverInfo.size());
            Log.e("TestingDaat", "mapsize" + receiverList.size());
            if (receiverList.size() == RequiredFieldAdapter.ReceiverInfo.size()) {
                TransferTransaction();
                Toast.makeText(AddBankActivity.this, "Success", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(AddBankActivity.this, "Fill Required Field", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void TransferTransaction() {
        HashMap<String, String> data = new SessionManager(getApplicationContext()).getBankDetails();
        Address = data.get(ADDRESS);
        BankId = data.get(BANK_ID);
        BankName = data.get(BANK_NAME);
        City = data.get(CITY);
        BankBranchName = data.get(BANK_BRANCH);
        LocationId = data.get(LOCATION_ID);


        ReceiverInfo = RequiredFieldAdapter.ReceiverInfo;
        JSONObject jsonResult = new JSONObject();
        try {
            jsonResult.put("country_id", Country);
            jsonResult.put("transaction_type", transactionType);
            jsonResult.put("receiveCurrency", receiveCurrency);
            jsonResult.put("senderInfo", SenderInfo);
            jsonResult.put("receiverInfo", ReceiverInfo);
        } catch (JsonIOException e) {
            e.printStackTrace();
        }
        String msg = jsonResult.toString();
        Log.e("AddBank", "SenderInfoData=> " + new Gson().toJson(SenderInfo));
        Log.e("AddBank", "ReceiverInfoData=> " + new Gson().toJson(ReceiverInfo));
        Log.e("AddBank", "MSGInfoData=> " + msg);



       /*Random rand = new Random();
        MrchantOrderNo = String.valueOf("Mrchant" + rand.nextInt(999999));
        //Sender Param variable define here
        SortedMap<String, Object> transMap = new TreeMap<>();
        String epayAccount = "test2020@epay.com";
        String category = "BANK";
        String notifyUrl = "http://localhost/paymentApi/channel/send.do";
        //String merchantOrderNo = MrchantOrderNo;
        String amount = "5";
        String receiveAmount = "";
        String settlementCurrency = "USD";
        //String receiveCurrency = receiveCurrency;
        String version = "V2.0.0";
        //String transactionType = transactionType;

        //put Param value here in map for sign
        transMap.put("epayAccount", epayAccount);
        transMap.put("category", category);
        transMap.put("notifyUrl", notifyUrl);
        transMap.put("merchantOrderNo", MrchantOrderNo);
        transMap.put("amount", amount);
        transMap.put("receiveAmount", receiveAmount);
        transMap.put("settlementCurrency", settlementCurrency);
        transMap.put("receiveCurrency", receiveCurrency);
        transMap.put("transactionType", transactionType);
        transMap.put("version", version);*/

        //transMap.put("senderInfo", SenderInfo);
        //transMap.put("receiverInfo", RequiredFieldAdapter.ReceiverInfo);

       /* Log.e("AddBank", "TransactionMapValue=> " + transMap);
        String transKey = SignUtil.createSign(transMap, "2d00b386231806ec7e18e2d96dc043aa");
        CreateTransactionRequest createTransactionRequest = new CreateTransactionRequest();
        createTransactionRequest.setSign(transKey);*/

      /* ReceiverInfo receiverInfo = new ReceiverInfo();
        receiverInfo.setGivName(GivName);
        receiverInfo.setSurName(SurName);
        receiverInfo.setLocationId(LocationId);
        receiverInfo.setBankId(BankId);
        receiverInfo.setBankName(BankBranchName);
        receiverInfo.setAccountNo(AccountNo);
        receiverInfo.setBankBranchCode(BankName);
        receiverInfo.setCountry(Country);
        receiverInfo.setAddress(BankBranch);*/
        /*CreateTransactionBody createTransactionBody = new CreateTransactionBody();
        createTransactionBody.setEpayAccount(epayAccount);
        createTransactionBody.setCategory(category);
        createTransactionBody.setNotifyUrl(notifyUrl);
        createTransactionBody.setMerchantOrderNo(MrchantOrderNo);
        createTransactionBody.setAmount(amount);
        createTransactionBody.setReceiveAmount(receiveAmount);
        createTransactionBody.setSettlementCurrency(settlementCurrency);
        createTransactionBody.setReceiveCurrency(receiveCurrency);
        createTransactionBody.setTransactionType(transactionType);
        createTransactionBody.setVersion(version);*/

       /* ArrayList<String> listdata = new ArrayList<String>();
        JSONObject jArray = (JSONObject) SenderInfo;
        if (jArray != null) {
            for (int i = 0; i < jArray.size(); i++) {
                listdata.add(jArray.getString("SenderInfo"));
            }
        }*/

        // createTransactionBody.setSenderInfo(senderInfo);
        // createTransactionBody.setReceiverInfo(receiverInfo);
        //createTransactionRequest.setParam(createTransactionBody);
        //apiManager.createTransaction(createTransactionRequest);
    }


    public void getValueFromAdapter(int Pos) {
        //String category, String value
        requiredFieldAdapter.notifyItemRangeChanged(1, receiverList.size());
        // requiredFieldAdapter.notifyDataSetChanged();
      /*  switch (category) {
            case "surName":
                SurName = value;
                break;
            case "givName":
                GivName = value;
                break;
            case "middleName":
                MiddleName = value;
                break;
            case "accountNo":
                AccountNo = value;
                break;
            case "bankBranchCode":
                BankBranchCode = value;
                break;
            case "phone":
                Phone = value;
                break;
            case "registeredName":
                RegisteredName = value;
                break;
            case "taxId":
                TaxId = value;
                break;
            case "accountType":
                AccountType = value;
                break;
            case "idType":
                IdType = value;
                break;
            case "idNumber":
                IdNumber = value;
                break;
            case "area":
                Area = value;
                break;
            case "zipCode":
                ZipCode = value;
                break;
            case "states":
                States = value;
                break;
            case "city":
                City = value;
                break;
            case "address":
                Address = value;
                break;


        }*/
    }
}




