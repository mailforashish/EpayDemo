package com.zeeplivework.app.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.zeeplivework.app.R;
import com.zeeplivework.app.dialog.CurrencyListDialog;
import com.zeeplivework.app.response.CurrencyList.Country;
import com.zeeplivework.app.response.CurrencyList.CurrenciesResult;
import com.zeeplivework.app.utils.CountrySelect;

import java.util.List;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.MyViewHolder> {
    List<Country> arrayList;
    Context context;
    CountrySelect countrySelect;

    public CurrencyAdapter(Context context, List<Country> arrayList, CountrySelect countrySelect) {
        this.arrayList = arrayList;
        this.context = context;
        this.countrySelect = countrySelect;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

            holder.countryName.setText(arrayList.get(position).getEnName());
            holder.countryCode.setText(arrayList.get(position).getCountryCode());
           // holder.currencyCode.setText(arrayList.get(position));
            holder.constraint_main.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    countrySelect.getCountry(true, arrayList.get(position).getEnName());
                }
            });

        try {
        } catch (Exception e) {
        }
    }

    @Override
    public int getItemViewType(int position) {
        return arrayList.size();

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout constraint_main;
        TextView countryName, currencyCode, countryCode;

        public MyViewHolder(View view) {
            super(view);
            constraint_main = view.findViewById(R.id.constraint_main);
            countryName = view.findViewById(R.id.countryName);
            currencyCode = view.findViewById(R.id.currencyCode);
            countryCode = view.findViewById(R.id.countryCode);

        }
    }
}
