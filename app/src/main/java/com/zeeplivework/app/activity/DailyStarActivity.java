package com.zeeplivework.app.activity;

import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.os.Bundle;

import com.zeeplivework.app.R;
import com.zeeplivework.app.databinding.ActivityDailyStarBinding;
import com.zeeplivework.app.utils.BaseActivity;

public class DailyStarActivity extends BaseActivity {
    ActivityDailyStarBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatusBar(getWindow(), true);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_daily_star);
        binding.setClickListener(new EventHandler(this));

    }

    public class EventHandler {
        Context mContext;
        public EventHandler(Context mContext) {
            this.mContext = mContext;
        }
        public void backPage() {
            onBackPressed();
        }
        public void pressTvOne() {
            binding.tv1.setBackgroundResource(R.drawable.text_1_bg);
            binding.tv1.setTextColor(getResources().getColor(R.color.white));
            binding.tv2.setBackgroundColor(getResources().getColor(R.color.transparent));
            binding.tv2.setTextColor(getResources().getColor(R.color.text_color));
            binding.tv3.setBackgroundColor(getResources().getColor(R.color.transparent));
            binding.tv3.setTextColor(getResources().getColor(R.color.text_color));
            binding.tv4.setBackgroundColor(getResources().getColor(R.color.transparent));
            binding.tv4.setTextColor(getResources().getColor(R.color.text_color));

        }

        public void pressTvTwo() {
            binding.tv2.setBackgroundResource(R.drawable.text_2_bg);
            binding.tv2.setTextColor(getResources().getColor(R.color.white));
            binding.tv1.setBackgroundColor(getResources().getColor(R.color.transparent));
            binding.tv1.setTextColor(getResources().getColor(R.color.text_color));
            binding.tv3.setBackgroundColor(getResources().getColor(R.color.transparent));
            binding.tv3.setTextColor(getResources().getColor(R.color.text_color));
            binding.tv4.setBackgroundColor(getResources().getColor(R.color.transparent));
            binding.tv4.setTextColor(getResources().getColor(R.color.text_color));

        }

        public void pressThree() {
            binding.tv3.setBackgroundResource(R.drawable.text_3_bg);
            binding.tv3.setTextColor(getResources().getColor(R.color.white));
            binding.tv2.setBackgroundColor(getResources().getColor(R.color.transparent));
            binding.tv2.setTextColor(getResources().getColor(R.color.text_color));
            binding.tv1.setBackgroundColor(getResources().getColor(R.color.transparent));
            binding.tv1.setTextColor(getResources().getColor(R.color.text_color));
            binding.tv4.setBackgroundColor(getResources().getColor(R.color.transparent));
            binding.tv4.setTextColor(getResources().getColor(R.color.text_color));
        }

        public void pressTvFour() {
            binding.tv4.setBackgroundResource(R.drawable.text_4_bg);
            binding.tv4.setTextColor(getResources().getColor(R.color.white));
            binding.tv1.setBackgroundColor(getResources().getColor(R.color.transparent));
            binding.tv1.setTextColor(getResources().getColor(R.color.text_color));
            binding.tv2.setBackgroundColor(getResources().getColor(R.color.transparent));
            binding.tv2.setTextColor(getResources().getColor(R.color.text_color));
            binding.tv3.setBackgroundColor(getResources().getColor(R.color.transparent));
            binding.tv3.setTextColor(getResources().getColor(R.color.text_color));
        }
    }

}
