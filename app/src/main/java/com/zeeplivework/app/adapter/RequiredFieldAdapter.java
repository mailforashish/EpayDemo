package com.zeeplivework.app.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;
import com.zeeplivework.app.R;

import com.zeeplivework.app.dialog.BankDialog;
import com.zeeplivework.app.response.RequiredField.RequiredFieldResult;
import com.zeeplivework.app.utils.BankSelected;
import com.zeeplivework.app.utils.JsonParse;
import com.zeeplivework.app.utils.SessionManager;
import java.util.List;


public class RequiredFieldAdapter extends RecyclerView.Adapter<RequiredFieldAdapter.MyViewHolder> {
    public List<RequiredFieldResult> arrayList;
    Context context;
    BankDialog bankDialog;
    SessionManager sessionManager;
    public static JSONObject ReceiverInfo = new JSONObject();
    String LocationId = "";
    String BankName = "";
    String BankID = "";
    String City = "";
    String BankBranch = "";
    String Address = "";
    String CountryCode = "";

    public RequiredFieldAdapter(Context context, List<RequiredFieldResult> arrayList, String CountryCode) {
        this.arrayList = arrayList;
        this.context = context;
        this.CountryCode = CountryCode;
        sessionManager = new SessionManager(context);

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.required_field_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.et_name_input.setTag(position);
        holder.et_name_input.setFocusable(true);

        if (JsonParse.jsonDecode(arrayList.get(position).getShowName()).equalsIgnoreCase("Bank ID")) {
            holder.et_name_input1.setVisibility(View.VISIBLE);
            holder.et_name_input1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_next_acc, 0);
        }
        if (arrayList.get(position).getValue().equals("country")) {
            holder.et_name_input.setText(CountryCode);
        }

        if (JsonParse.jsonDecode(arrayList.get(position).getShowName()).equalsIgnoreCase("location ID")) {
            holder.et_name_input.setText(LocationId);
        } else if (JsonParse.jsonDecode(arrayList.get(position).getShowName()).equalsIgnoreCase("Bank Name")) {
            holder.et_name_input.setText(BankName);
        } else if (JsonParse.jsonDecode(arrayList.get(position).getShowName()).trim().equalsIgnoreCase("Bank Branch Code")) {
            holder.et_name_input.setText(BankBranch);
        } else if (JsonParse.jsonDecode(arrayList.get(position).getShowName()).equalsIgnoreCase("Address")) {
            holder.et_name_input.setText(Address);
        } else if (JsonParse.jsonDecode(arrayList.get(position).getShowName()).equalsIgnoreCase("Nationality")) {
            holder.et_name_input.setText(CountryCode);
        }
        holder.tv_Name.setText(JsonParse.jsonDecode(arrayList.get(position).getShowName()));
        holder.et_name_input.setHint(JsonParse.jsonDecode(arrayList.get(position).getShowName()));
    }


    @Override
    public int getItemViewType(int position) {
        //remove duplicate
        return position;
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements BankSelected {
        public ConstraintLayout cl_form;
        public TextView tv_Name, tv_name_error;
        public AppCompatEditText et_name_input;
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
                    if (JsonParse.jsonDecode(arrayList.get(getAdapterPosition()).getShowName()).equalsIgnoreCase("Bank ID")) {
                        bankDialog = new BankDialog(view.getContext(), MyViewHolder.this);
                    }
                }
            });
            MyTextWatcher textWatcher = new MyTextWatcher(et_name_input);
            et_name_input.addTextChangedListener(textWatcher);

        }

        public class MyTextWatcher implements TextWatcher {
            private AppCompatEditText editText;
            int position;

            public MyTextWatcher(AppCompatEditText editText) {
                this.editText = editText;
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                position = (int) editText.getTag();
                isAllEditTextsFilled(position, editText);
                // Do whatever you want with position

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)) {
                    ReceiverInfo.remove(arrayList.get(position).getValue(), String.valueOf(s));
                } else {
                    ReceiverInfo.put(arrayList.get(position).getValue(), String.valueOf(s));

                }
            }
        }

        private boolean isAllEditTextsFilled(int currentIndex, AppCompatEditText editText) {
            if (currentIndex == currentIndex) {
                if (editText.length() >= 2) {
                    tv_name_error.setVisibility(View.INVISIBLE);
                } else if (et_name_input1.getText().toString().length() >= 2) {
                    tv_name_error.setVisibility(View.INVISIBLE);
                } else {
                    tv_name_error.setVisibility(View.VISIBLE);
                }
            }
            return true;
        }

        @Override
        public void getBank(boolean select, String address, String bankId, String bankName, String city, String bankBranch, String locationId, String countryCode) {
            Log.e("selectedBankName", "AdapterClass=> " + bankName);
            if (select) {
                et_name_input1.setText(bankId);
                sessionManager.saveBankData(address, bankId, bankName, city, bankBranch, locationId);
                Address = address;
                BankName = bankName;
                BankID = bankId;
                City = city;
                BankBranch = bankBranch;
                LocationId = locationId;

                ReceiverInfo.put(arrayList.get(getAdapterPosition()).getValue(), bankId);
                notifyDataSetChanged();
                bankDialog.dismiss();

            }
        }
    }

    public static String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
