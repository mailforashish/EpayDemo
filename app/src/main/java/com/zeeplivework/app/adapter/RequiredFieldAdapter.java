package com.zeeplivework.app.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.PopupMenu;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;
import com.zeeplivework.app.R;
import com.zeeplivework.app.dialog.BankDialog;
import com.zeeplivework.app.response.RequiredField.RequiredFieldResult;
import com.zeeplivework.app.utils.BankSelected;
import com.zeeplivework.app.utils.IDFiller;
import com.zeeplivework.app.utils.JsonParse;
import com.zeeplivework.app.utils.SessionManager;

import java.util.ArrayList;
import java.util.HashMap;
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
    String AreaCode = "";
    ArrayAdapter<String> adapter = null;
    public int oldPosition;
    HashMap<String, Object> FormMap;
    String MatchVal;

    public RequiredFieldAdapter(Context context, List<RequiredFieldResult> arrayList, String MatchVal,HashMap<String, Object> FormMap, String CountryCode, String AreaCode) {
        this.arrayList = arrayList;
        this.context = context;
        this.CountryCode = CountryCode;
        this.AreaCode = AreaCode;
        this.FormMap = FormMap;
        this.MatchVal = MatchVal;
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
            HideView(holder.tv_Name, holder.et_name_input, holder.tv_name_error);
        }
        if (arrayList.get(position).getValue().equals("area")) {
            holder.et_name_input.setText(AreaCode);
            holder.et_name_input.setFocusable(false);
            //HideView(holder.tv_Name, holder.et_name_input, holder.tv_name_error);
        } else if (JsonParse.jsonDecode(arrayList.get(position).getShowName()).equalsIgnoreCase("location ID")) {
            holder.et_name_input.setText(LocationId);
            HideView(holder.tv_Name, holder.et_name_input, holder.tv_name_error);
            //holder.et_name_input.setEnabled(false);
        } else if (JsonParse.jsonDecode(arrayList.get(position).getShowName()).equalsIgnoreCase("Bank Name")) {
            holder.et_name_input.setText(BankName);
            HideView(holder.tv_Name, holder.et_name_input, holder.tv_name_error);
        } else if (arrayList.get(position).getValue().trim().equalsIgnoreCase("bankBranchCode")) {
            holder.et_name_input.setText(BankBranch);
            holder.et_name_input.setEnabled(false);
        } else if (JsonParse.jsonDecode(arrayList.get(position).getShowName()).equalsIgnoreCase("Address")) {
            holder.et_name_input.setText(Address);
            HideView(holder.tv_Name, holder.et_name_input, holder.tv_name_error);
        } else if (JsonParse.jsonDecode(arrayList.get(position).getShowName()).equalsIgnoreCase("Nationality")) {
            holder.et_name_input.setText(CountryCode);
            HideView(holder.tv_Name, holder.et_name_input, holder.tv_name_error);
        } else if (arrayList.get(position).getValue().equalsIgnoreCase("bankBranchName")) {
            holder.et_name_input.setText(BankBranch);
            HideView(holder.tv_Name, holder.et_name_input, holder.tv_name_error);
        } else if (arrayList.get(position).getValue().equals("accountType")) {
            holder.et_name_input.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_down, 0);
            holder.et_name_input.setFocusable(false);
            holder.et_name_input.setOnClickListener((View view) -> {
                ShowAccountType(holder.et_name_input, view);
            });
        }
        if (arrayList.get(position).getValue().equals("idType")) {
            holder.spinner_idType.setVisibility(View.VISIBLE);
            holder.et_name_input.setVisibility(View.GONE);
            holder.tv_name_error.setVisibility(View.GONE);
            FillIDTypeData(holder.spinner_idType, CountryCode, position);
        }
        holder.tv_Name.setText(JsonParse.jsonDecode(arrayList.get(position).getShowName()));
        holder.et_name_input.setHint(capitalize(arrayList.get(position).getValue()));

        if (MatchVal.equals("YES")){
            holder.tv_Name.setText(capitalize(arrayList.get(position).getValue()));
            String preFill = String.valueOf(FormMap.get(arrayList.get(position).getValue()));
            holder.et_name_input.setText(preFill);
            holder.tv_name_error.setVisibility(View.GONE);
        }

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
        public Spinner spinner_idType;

        public MyViewHolder(View view) {
            super(view);
            cl_form = view.findViewById(R.id.cl_form);
            tv_Name = view.findViewById(R.id.tv_Name);
            et_name_input = view.findViewById(R.id.et_name_input);
            tv_name_error = view.findViewById(R.id.tv_name_error);
            et_name_input1 = view.findViewById(R.id.et_name_input1);
            spinner_idType = view.findViewById(R.id.spinner_idType);
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
                    ReceiverInfo.remove(arrayList.get(position).getValue());
                    Log.e("ReceiverInfo", "AdapterClassEf=> " + ReceiverInfo);
                } else {
                    ReceiverInfo.put(arrayList.get(position).getValue(), String.valueOf(s));
                    Log.e("ReceiverInfo", "AdapterClassElse=> " + ReceiverInfo);
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

    private void FillIDTypeData(Spinner spinner, String val, int position) {
        ArrayList<String> IDList = new ArrayList<>();
        if (val.equals("BR")) {
            IDList = IDFiller.FillIdTypeBrazil();
        } else if (val.equals("MY")) {
            IDList = IDFiller.FillIdTypeMalaysia();
        } else if (val.equals("CO")) {
            IDList = IDFiller.FillIdTypeColombia();
        } else if (val.equals("BD")) {
            IDList = IDFiller.FillIdTypeBangladesh();
        } else if (val.equals("PH")) {
            IDList = IDFiller.FillIdTypePhilippines();
        } else if (val.equals("VN")) {
            IDList = IDFiller.FillIdTypeVietnam();
        } else if (val.equals("ID")) {
            IDList = IDFiller.FillIdTypeIndonesia();
        } else if (val.equals("NP")) {
            IDList = IDFiller.FillIdTypeNepal();
        }
        adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, IDList);
        spinner.setAdapter(adapter);
        spinner.setSelection(oldPosition);

        if (spinner != null && spinner.getSelectedItem() != null) {
            String idValue = spinner.getSelectedItem().toString();
            Log.e("selectedID", "Spinnervalue=> " + idValue);
            ReceiverInfo.put(arrayList.get(position).getValue(), "1");
        } else {
            Log.e("selectedID", "Spinnervalue=> " + "Novalue");
        }
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Object item = parent.getItemAtPosition(pos);
                Log.e("AddBank", "TransactionSpinnerpos=> " + String.valueOf(pos));
                String idTypeSelected = spinner.getSelectedItem().toString();
                oldPosition = spinner.getSelectedItemPosition();
                ReceiverInfo.put(arrayList.get(position).getValue(), "1");
                adapter.notifyDataSetChanged();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void HideView(TextView tv_Name, AppCompatEditText et_name_input, TextView tv_name_error) {
        tv_Name.setVisibility(View.GONE);
        et_name_input.setVisibility(View.GONE);
        tv_name_error.setVisibility(View.GONE);
    }

    public static String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public void ShowAccountType(AppCompatEditText et_name_input, View view) {
        PopupMenu popup = new PopupMenu(context, view);
        if (CountryCode.equals("BR")) {
            popup.getMenu().add("SAVING");
            popup.getMenu().add("CHECKING");
        } else if (CountryCode.equals("CO")) {
            popup.getMenu().add("OTHERS");
            popup.getMenu().add("DEPOSIT");
            popup.getMenu().add("SAVINGS");
            popup.getMenu().add("CHECKING");
        }else if (CountryCode.equals("PE")) {
            popup.getMenu().add("OTHERS");
        }
        popup.setOnMenuItemClickListener(item -> {
            et_name_input.setText(item.getTitle());
            return false;
        });
        popup.show();
    }


}
