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


import com.google.gson.Gson;
import com.zeeplivework.app.R;

import com.zeeplivework.app.activity.WalletActivity;
import com.zeeplivework.app.adapter.CountryAdapter;
import com.zeeplivework.app.databinding.CountryDialogBinding;
import com.zeeplivework.app.response.CountryListNew;
import com.zeeplivework.app.utils.CountrySelect;
import com.zeeplivework.app.utils.SessionManager;

import java.util.ArrayList;


public class CountryDialog extends Dialog implements CountrySelect {
    CountryDialogBinding binding;
    ArrayList<CountryListNew> countryListNews = new ArrayList<>();
    CountryAdapter adapter;
    Context context;
    SessionManager sessionManager;

    public CountryDialog(@NonNull Context context) {
        super(context);
        this.context = context;
        init();
    }

    void init() {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.country_dialog, null, false);
        setContentView(binding.getRoot());
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        this.setCancelable(true);

        binding.setClickListener(new EventHandler(getContext()));
        sessionManager = new SessionManager(getContext());
        binding.rvCountry.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new CountryAdapter(context, countryListNews, this);
        binding.rvCountry.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        setData();
        show();
    }

    @Override
    public void getCountry(boolean select, String country_name, String country_code, String currency_code) {
        Log.e("Epay", "SelectedValue=> " + country_name + "\n" + country_code + "\n" + currency_code);
        if (select) {
            ((WalletActivity) context).setCountry(country_name, currency_code);
            sessionManager.createCountrySession(country_name, country_code, currency_code);
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

    private void setData() {
        CountryListNew list1 = new CountryListNew("INDIA", "IN", "INR", "C2C");
        countryListNews.add(list1);
        list1 = new CountryListNew("Argentina", "AR", "ARS", "C2C");
        countryListNews.add(list1);
        list1 = new CountryListNew("Australia", "AU", "AUD", "C2C");
        countryListNews.add(list1);
        list1 = new CountryListNew("Brazil", "BR", "BRL", "C2C");
        countryListNews.add(list1);
        list1 = new CountryListNew("Bangladesh", "BD", "BDT", "C2C");
        countryListNews.add(list1);
        list1 = new CountryListNew("Colombia", "CO", "COP", "C2C");
        countryListNews.add(list1);
        list1 = new CountryListNew("Canada", "CA", "CAD", "C2C");
        countryListNews.add(list1);
        list1 = new CountryListNew("Fiji", "FJ", "FJD", "C2C");
        countryListNews.add(list1);
        list1 = new CountryListNew("Jordan", "JO", "JOD", "C2C");
        countryListNews.add(list1);
        list1 = new CountryListNew("Indonesia", "ID", "IDR", "C2C");
        countryListNews.add(list1);
        list1 = new CountryListNew("Nepal", "NP", "NPR", "C2C");
        countryListNews.add(list1);
        list1 = new CountryListNew("New Zealand", "NZ", "NZD", "C2C");
        countryListNews.add(list1);
        list1 = new CountryListNew("Nigeria", "NG", "NGN", "C2C");
        countryListNews.add(list1);
        list1 = new CountryListNew("Pakistan", "PK", "PKR", "C2C");
        countryListNews.add(list1);
        list1 = new CountryListNew("Kuwait", "KW", "KWD", "C2C");
        countryListNews.add(list1);
        list1 = new CountryListNew("Kenya", "KE", "KES", "C2C");
        countryListNews.add(list1);
        list1 = new CountryListNew("Peru", "PE", "PEN", "C2C");
        countryListNews.add(list1);
        list1 = new CountryListNew("Srilanka", "LK", "LKR", "C2C");
        countryListNews.add(list1);
        list1 = new CountryListNew("Singapore", "SG", "SGD", "C2C");
        countryListNews.add(list1);
        list1 = new CountryListNew("South Africa", "ZA", "ZAR", "C2C");
        countryListNews.add(list1);
        list1 = new CountryListNew("Thailand", "TH", "THB", "C2C");
        countryListNews.add(list1);
        list1 = new CountryListNew("Turkey", "TR", "TRY", "C2C");
        countryListNews.add(list1);
        list1 = new CountryListNew("United Arab Emirates", "AE", "AED", "C2C");
        countryListNews.add(list1);
        list1 = new CountryListNew("Vietnam", "VN", "VND", "C2C");
        countryListNews.add(list1);


    }


}
