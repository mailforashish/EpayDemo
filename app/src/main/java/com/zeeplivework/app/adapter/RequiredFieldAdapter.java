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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.zeeplivework.app.R;
import com.zeeplivework.app.activity.AddBankActivity;
import com.zeeplivework.app.dialog.BankDialog;
import com.zeeplivework.app.response.RequiredField.RequiredFieldResult;
import com.zeeplivework.app.utils.BankSelected;
import com.zeeplivework.app.utils.JsonParse;
import com.zeeplivework.app.utils.SHAUtils;
import com.zeeplivework.app.utils.SessionManager;

import org.json.JSONException;

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

        if (arrayList.get(position).getValue().equals("bankId")) {
            holder.et_name_input1.setVisibility(View.VISIBLE);
            holder.et_name_input1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_next_acc, 0);
        }
        if (arrayList.get(position).getValue().equals("country")) {
            holder.et_name_input.setText(CountryCode);
        }

        if (arrayList.get(position).getValue().equals("bankSelectId")) {
            holder.et_name_input.setText(BankID);
        } else if (arrayList.get(position).getValue().equals("locationId")) {
            holder.et_name_input.setText(LocationId);
        } else if (arrayList.get(position).getValue().equals("bankBranchCode")) {
            holder.et_name_input.setText(BankBranch);
        } else if (arrayList.get(position).getValue().equals("bankName")) {
            holder.et_name_input.setText(BankName);
        } else if (arrayList.get(position).getValue().equals("address")) {
            holder.et_name_input.setText(Address);
        } else if (arrayList.get(position).getValue().equals("nationality")) {
            holder.et_name_input.setText(CountryCode);
        }


        //String sign = SHAUtils.sha256(sbkey.toString()).toUpperCase();
        String parseValue = JsonParse.jsonDecode(arrayList.get(position).getShowName());
        //holder.tv_Name.setText(capitalize(arrayList.get(position).getShowName()));
        holder.tv_Name.setText(parseValue);
        holder.et_name_input.setHint(parseValue);
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
                    if (arrayList.get(getAdapterPosition()).getValue().equals("bankId")) {
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
