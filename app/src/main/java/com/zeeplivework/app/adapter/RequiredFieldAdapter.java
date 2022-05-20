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
import com.zeeplivework.app.activity.AddBankActivity;
import com.zeeplivework.app.dialog.BankDialog;
import com.zeeplivework.app.response.RequiredField.RequiredFieldResult;
import com.zeeplivework.app.utils.BankSelected;
import com.zeeplivework.app.utils.SessionManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class RequiredFieldAdapter extends RecyclerView.Adapter<RequiredFieldAdapter.MyViewHolder> {
    public List<RequiredFieldResult> arrayList;
    Context context;
    BankDialog bankDialog;
    SessionManager sessionManager;
    public static JSONObject ReceiverInfo = new JSONObject();
    String BankName = "";
    String LocationId = "";
    String BankId = "";
    String City = "";
    String BankBranch = "";
    String Address = "";
    String Country = "";

    public RequiredFieldAdapter(Context context, List<RequiredFieldResult> arrayList, String Country) {
        this.arrayList = arrayList;
        this.context = context;
        this.Country = Country;
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
        if (arrayList.get(position).getValue().equals("bankName")) {
            holder.et_name_input1.setVisibility(View.VISIBLE);
            holder.et_name_input1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_next_acc, 0);
        }

        if (arrayList.get(position).getValue().equals("bankName")) {
            holder.et_name_input1.setText(BankName);
        } else if (arrayList.get(position).getValue().equals("bankSelectId")) {
            holder.et_name_input.setText(BankId);
        } else if (arrayList.get(position).getValue().equals("country")) {
            holder.et_name_input.setText(Country);
        } else if (arrayList.get(position).getValue().equals("locationId")) {
            holder.et_name_input.setText(LocationId);
        } else if (arrayList.get(position).getValue().equals("bankBranchCode")) {
            holder.et_name_input.setText(BankBranch);
        } else if (arrayList.get(position).getValue().equals("bankId")) {
            holder.et_name_input.setText(BankId);
        } else if (arrayList.get(position).getValue().equals("address")) {
            holder.et_name_input.setText(Address);
        }

        holder.tv_Name.setText(capitalize(arrayList.get(position).getValue()));
        holder.et_name_input.setHint(arrayList.get(position).getValue());


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
                    if (arrayList.get(getAdapterPosition()).getValue().equals("bankName")) {
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
                    //((AddBankActivity) context).getValueFromAdapter(arrayList.get(position).getValue(), String.valueOf(s));
                } else {
                    //Log.e("inAdapterLog", "editTxtName = " + arrayList.get(position).getValue() + " value = " + s);
                    ReceiverInfo.put(arrayList.get(position).getValue(), String.valueOf(s));
                    //((AddBankActivity) context).getValueFromAdapter(arrayList.get(position).getValue(), String.valueOf(s));
                }
            }
        }

        private boolean isAllEditTextsFilled(int currentIndex, AppCompatEditText editText) {
            //Log.e("currentIndex", "valueIndex" + currentIndex);
            //Log.e("currentIndex", "editTextvalue" + editText);
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
        public void getBank(boolean select, String address, String bankId, String bankName, String city, String bankBranch, String locationId) {
            Log.e("selectedBankName", "AdapterClass=> " + bankName);
            if (select) {
                et_name_input1.setText(bankName);
                sessionManager.saveBankData(address, bankId, bankName, city, bankBranch, locationId);
                BankName = bankName;
                Address = address;
                BankId = bankId;
                City = city;
                BankBranch = bankBranch;
                LocationId = locationId;

                /*for (int i = 0; i < arrayList.size(); i++) {
                    if (arrayList.get(getAdapterPosition()).getValue().equals("country")) {
                        et_name_input.setText(city);
                    } else if (arrayList.get(getAdapterPosition()).getValue().equals("locationId")) {
                        et_name_input.setText(locationId);
                    } else if (arrayList.get(getAdapterPosition()).getValue().equals("bankBranchCode")) {
                        et_name_input.setText(bankBranch);
                    } else if (arrayList.get(getAdapterPosition()).getValue().equals("bankId")) {
                        et_name_input.setText(bankId);
                    } else if (arrayList.get(getAdapterPosition()).getValue().equals("address")) {
                        et_name_input.setText(address);
                    } else {

                    }
                }*/

                ((AddBankActivity) context).getValueFromAdapter(getAdapterPosition());
                ReceiverInfo.put(arrayList.get(getAdapterPosition()).getValue(), bankName);
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
