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

import com.zeeplivework.app.R;
import com.zeeplivework.app.dialog.BankDialog;
import com.zeeplivework.app.response.RequiredField.RequiredFieldResult;
import com.zeeplivework.app.utils.BankSelected;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class RequiredFieldAdapter extends RecyclerView.Adapter<RequiredFieldAdapter.MyViewHolder> {
    public List<RequiredFieldResult> arrayList;
    Context context;
    BankDialog bankDialog;
    public static SortedMap<String, Object> fillForm = new TreeMap<>();
    public static boolean flag = false;
    private int indexValue = 1;

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
        holder.et_name_input.setTag(position);
        holder.et_name_input.setFocusable(true);

        if (arrayList.get(position).getValue().equals("bankName")) {
            holder.et_name_input1.setVisibility(View.VISIBLE);
            holder.et_name_input1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_next_acc, 0);
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
                arrayList.get(position).setValue(s.toString());
                // Do whatever you want with position
            }

            @Override
            public void afterTextChanged(Editable s) {
                fillForm.put(arrayList.get(position).getValue(), s);
                Log.e("currentIndex", "formevalue" + fillForm);
            }
        }

        private boolean isAllEditTextsFilled(int currentIndex, AppCompatEditText editText) {
            Log.e("currentIndex", "valueIndex" + currentIndex);
            Log.e("currentIndex", "editTextvalue" + editText);

            if (currentIndex == currentIndex) {
                if (editText.length() >= 4) {
                    tv_name_error.setVisibility(View.INVISIBLE);
                } else {
                    tv_name_error.setVisibility(View.VISIBLE);
                }

            }

            /*else if (currentIndex == currentIndex) {
                if (editText.length() >= 10) {
                    tv_name_error.setVisibility(View.INVISIBLE);
                } else {
                    tv_name_error.setVisibility(View.VISIBLE);
                }

            } else if (currentIndex == 2) {
                if (editText.length() >= 6) {
                    tv_name_error.setVisibility(View.INVISIBLE);
                } else {
                    tv_name_error.setVisibility(View.VISIBLE);
                }

            } else if (currentIndex == 3) {
                if (editText.length() >= 4) {
                    tv_name_error.setVisibility(View.INVISIBLE);
                } else {
                    tv_name_error.setVisibility(View.VISIBLE);
                }

            } else if (currentIndex == 4) {
                if (editText.length() >= 4) {
                    tv_name_error.setVisibility(View.INVISIBLE);
                } else {
                    tv_name_error.setVisibility(View.VISIBLE);
                }

            }
            else if (currentIndex == 5) {
                if (editText.length() >= 4) {
                    tv_name_error.setVisibility(View.INVISIBLE);
                } else {
                    tv_name_error.setVisibility(View.VISIBLE);
                }

            }
            else if (currentIndex == 6) {
                if (editText.length() >= 4) {
                    tv_name_error.setVisibility(View.INVISIBLE);
                } else {
                    tv_name_error.setVisibility(View.VISIBLE);
                }

            }
            else if (currentIndex == 7) {
                if (editText.length() >= 4) {
                    tv_name_error.setVisibility(View.INVISIBLE);
                } else {
                    tv_name_error.setVisibility(View.VISIBLE);
                }

            }
            else if (currentIndex == 8) {
                if (editText.length() >= 4) {
                    tv_name_error.setVisibility(View.INVISIBLE);
                } else {
                    tv_name_error.setVisibility(View.VISIBLE);
                }

            }
            else if (currentIndex == 9) {
                if (editText.length() >= 4) {
                    tv_name_error.setVisibility(View.INVISIBLE);
                } else {
                    tv_name_error.setVisibility(View.VISIBLE);
                }

            }
            else if (currentIndex == 10) {
                if (editText.length() >= 4) {
                    tv_name_error.setVisibility(View.INVISIBLE);
                } else {
                    tv_name_error.setVisibility(View.VISIBLE);
                }

            }
            else if (currentIndex == 11) {
                if (editText.length() >= 4) {
                    tv_name_error.setVisibility(View.INVISIBLE);
                } else {
                    tv_name_error.setVisibility(View.VISIBLE);
                }

            }
            else if (currentIndex == 12) {
                if (editText.length() >= 4) {
                    tv_name_error.setVisibility(View.INVISIBLE);
                } else {
                    tv_name_error.setVisibility(View.VISIBLE);
                }

            }
            else if (currentIndex == 13) {
                if (editText.length() >= 4) {
                    tv_name_error.setVisibility(View.INVISIBLE);
                } else {
                    tv_name_error.setVisibility(View.VISIBLE);
                }

            }
            else if (currentIndex == 14) {
                if (editText.length() >= 4) {
                    tv_name_error.setVisibility(View.INVISIBLE);
                } else {
                    tv_name_error.setVisibility(View.VISIBLE);
                }

            }
            else if (currentIndex == 15) {
                if (editText.length() >= 4) {
                    tv_name_error.setVisibility(View.INVISIBLE);
                } else {
                    tv_name_error.setVisibility(View.VISIBLE);
                }

            }
            else if (currentIndex == 16) {
                if (editText.length() >= 4) {
                    tv_name_error.setVisibility(View.INVISIBLE);
                } else {
                    tv_name_error.setVisibility(View.VISIBLE);
                }

            }*/
            return true;
        }


        @Override
        public void getBank(boolean select, String bank) {
            Log.e("valigysgy", "" + bank);
            if (select) {
                et_name_input1.setText(bank);
                fillForm.put(arrayList.get(getAdapterPosition()).getValue(), bank);
                bankDialog.dismiss();
            }
        }
    }

    public List<RequiredFieldResult> getArrayList() {
        return arrayList;
    }


    public static String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
