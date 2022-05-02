package com.zeeplivework.app.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.databinding.DataBindingUtil;

import com.zeeplivework.app.R;
import com.zeeplivework.app.activity.DiamondActivity;
import com.zeeplivework.app.databinding.DiamondDialogBinding;

public class DiamondDialog extends Dialog {
    Context context;
    DiamondDialogBinding binding;

    public DiamondDialog(@NonNull Context context) {
        super(context);
        this.context = context;
        init();
    }


    void init() {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.diamond_dialog, null, false);
        setContentView(binding.getRoot());
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        show();
        binding.setClickListener(new EventHandler(getContext()));
    }


    public class EventHandler {
        Context mContext;

        public EventHandler(Context mContext) {
            this.mContext = mContext;
        }

        public void topUp() {
            binding.btnTopPop.setBackgroundResource(R.drawable.btn_select);
            binding.tvTopUp.setTextColor(context.getResources().getColor(R.color.white));
            binding.btnGetFree.setBackgroundResource(R.drawable.btn_unselect);
            binding.tvGetFree.setTextColor(context.getResources().getColor(R.color.text_color));
            context.startActivity(new Intent(context, DiamondActivity.class));
            dismiss();
        }

        public void getFree() {
            binding.tvGetFree.setTextColor(context.getResources().getColor(R.color.white));
            binding.btnGetFree.setBackgroundResource(R.drawable.btn_select);
            binding.btnTopPop.setBackgroundResource(R.drawable.btn_unselect);
            binding.tvTopUp.setTextColor(context.getResources().getColor(R.color.text_color));
            context.startActivity(new Intent(context, DiamondActivity.class));
            dismiss();

        }
    }


}
