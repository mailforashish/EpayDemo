package com.zeeplivework.app.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.zeeplivework.app.R;
import com.zeeplivework.app.response.BankList.Bank;
import com.zeeplivework.app.response.CurrencyList.Country;
import com.zeeplivework.app.utils.BankSelected;
import com.zeeplivework.app.utils.CountrySelect;

import java.util.List;

public class BankAdapter extends RecyclerView.Adapter<BankAdapter.MyViewHolder> {
    List<Bank> arrayList;
    Context context;
    BankSelected bankSelected;

    public BankAdapter(Context context, List<Bank> arrayList, BankSelected bankSelected) {
        this.arrayList = arrayList;
        this.context = context;
        this.bankSelected = bankSelected;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bank_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.bankName.setText(arrayList.get(position).getBankName());
        holder.bankId.setText(arrayList.get(position).getBankId());
        holder.constraint_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bankSelected.getBank(true, arrayList.get(position).getBankName());
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
        Log.e("BankAdapter", "BankSize " + arrayList.size());
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout constraint_main;
        TextView bankName, bankId;

        public MyViewHolder(View view) {
            super(view);
            constraint_main = view.findViewById(R.id.constraint_main);
            bankName = view.findViewById(R.id.bankName);
            bankId = view.findViewById(R.id.bankId);

        }
    }
}
