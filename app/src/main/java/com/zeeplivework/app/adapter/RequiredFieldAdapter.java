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
import com.zeeplivework.app.response.RequiredField.RequiredFieldResult;
import com.zeeplivework.app.utils.BankSelected;

import java.util.List;

public class RequiredFieldAdapter extends RecyclerView.Adapter<RequiredFieldAdapter.MyViewHolder> {
    List<RequiredFieldResult> arrayList;
    Context context;
    BankSelected bankSelected;

    public RequiredFieldAdapter(Context context, List<RequiredFieldResult> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.required_field_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        //           try {
        //               list = rsp.getResult();
        //               Log.e("AgencyCenterAc", "AgencyCenter3 " + list);
        //
        //               for (int i = 0; i < list.size(); i++) {
        //                   String[] selectedDate = list.get(i).getSettlementCycle().split(" ~ ");
        //                   String[] start = selectedDate[0].split("\\s+");
        //                   String[] end = selectedDate[1].split("\\s+");
        //                   String startDate = start[1] + "." + start[2];
        //                   String endDate = end[1] + "." + end[2];
        //                   listDate.add(startDate + " - " + endDate);
        //                   }
        for (int i = 0; i < arrayList.size(); i++) {
            String[] ShowName = arrayList.get(position).getShowName().split(":");
            String[] ShowName_start = ShowName[0].split("\\s+");
            String[] ShowName_end = ShowName[1].split("\\s+");
            String startName = ShowName_end[1] ;
            holder.tv_showName.setText(startName);
           // holder.et_showName.setText(arrayList.get(position).getShowName());
        }



       // holder.tv_showName.setText(arrayList.get(position).getShowName());

        holder.cl_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // bankSelected.getBank(true, arrayList.get(position).getBankName());
            }
        });

        try {
        } catch (
                Exception e) {
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
        public ConstraintLayout cl_name;
        public TextView tv_showName;
        public androidx.appcompat.widget.SearchView et_showName;

        public MyViewHolder(View view) {
            super(view);
            cl_name = view.findViewById(R.id.cl_name);
            tv_showName = view.findViewById(R.id.tv_showName);
            et_showName = view.findViewById(R.id.et_showName);

        }
    }
}
