package com.zeeplivework.app.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.zeeplivework.app.R;
import com.zeeplivework.app.activity.AddBankActivity;
import com.zeeplivework.app.dialog.BankDialog;
import com.zeeplivework.app.response.BankList.Bank;
import com.zeeplivework.app.response.RequiredField.RequiredFieldResult;
import com.zeeplivework.app.utils.BankSelected;

import java.security.AccessController;
import java.util.ArrayList;
import java.util.List;

public class RequiredFieldAdapter extends RecyclerView.Adapter<RequiredFieldAdapter.MyViewHolder> {
    public List<RequiredFieldResult> arrayList;
    Context context;
    BankDialog bankDialog;

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
        if (arrayList.get(position).getValue().equals("bankName")){
            holder.et_name_input1.setVisibility(View.VISIBLE);
            holder.et_name_input1.setCompoundDrawablesWithIntrinsicBounds( 0, 0, R.drawable.ic_next_acc, 0);
        }
        holder.tv_Name.setText(getCapsSentences(arrayList.get(position).getValue()));
        holder.et_name_input.setActivated(true);
        holder.et_name_input.onActionViewExpanded();
        holder.et_name_input.setIconified(false);
        holder.et_name_input.clearFocus();
        holder.et_name_input.setQueryHint(arrayList.get(position).getValue());
    }


    @Override
    public int getItemViewType(int position) {
        return arrayList.size();
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements BankSelected {
        public ConstraintLayout cl_form;
        public TextView tv_Name, tv_name_error;
        public SearchView et_name_input;
        public AppCompatTextView et_name_input1;

        public MyViewHolder(View view) {
            super(view);
            cl_form = view.findViewById(R.id.cl_form);
            tv_Name = view.findViewById(R.id.tv_Name);
            et_name_input = view.findViewById(R.id.et_name_input);
            tv_name_error = view.findViewById(R.id.tv_name_error);
            et_name_input1 = view.findViewById(R.id.et_name_input1);

            et_name_input1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (arrayList.get(getAdapterPosition()).getValue().equals("bankName")) {
                        bankDialog = new BankDialog(view.getContext(), MyViewHolder.this);
                    }
                }
            });


        }

        @Override
        public void getBank(boolean select, String bank) {
            Log.e("valigysgy", "" + bank);
            if (select) {
                et_name_input1.setText(bank);
                bankDialog.dismiss();
            }
        }
    }


    private String getCapsSentences(String tagName) {
        String[] splits = tagName.toLowerCase().split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < splits.length; i++) {
            String eachWord = splits[i];
            if (i > 0 && eachWord.length() > 0) {
                sb.append(" ");
            }
            String cap = eachWord.substring(0, 1).toUpperCase()
                    + eachWord.substring(1);
            sb.append(cap);
        }
        return sb.toString();
    }

}
