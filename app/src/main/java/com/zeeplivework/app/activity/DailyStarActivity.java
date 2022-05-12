package com.zeeplivework.app.activity;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.os.Bundle;

import com.zeeplivework.app.R;
import com.zeeplivework.app.adapter.DailyAdapter;
import com.zeeplivework.app.adapter.WeeklyAdapter;
import com.zeeplivework.app.databinding.ActivityDailyStarBinding;
import com.zeeplivework.app.response.DailyList;
import com.zeeplivework.app.utils.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class DailyStarActivity extends BaseActivity {
    ActivityDailyStarBinding binding;
    DailyAdapter dailyAdapter;
    WeeklyAdapter weeklyAdapter;
    List<DailyList> list = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatusBar(getWindow(), true);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_daily_star);
        binding.setClickListener(new EventHandler(this));

        binding.recyclerViewToday.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        dailyAdapter = new DailyAdapter(this, list);
        binding.recyclerViewToday.setHasFixedSize(true);
        binding.recyclerViewToday.setAdapter(dailyAdapter);
        binding.recyclerViewToday.setNestedScrollingEnabled(false);

        binding.recyclerViewTopDate.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        weeklyAdapter = new WeeklyAdapter(this, list);
        binding.recyclerViewTopDate.setHasFixedSize(true);
        binding.recyclerViewTopDate.setAdapter(weeklyAdapter);
        binding.recyclerViewTopDate.setNestedScrollingEnabled(false);
        setData();
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

    public void setData() {
        DailyList list1 = new DailyList(R.drawable.female, "Archana", "Won 6,189,107");
        list.add(list1);
        list1 = new DailyList(R.drawable.female, "Pooja", "Won 4,189,107");
        list.add(list1);
        list1 = new DailyList(R.drawable.female, "Neha", "Won 3,189,107");
        list.add(list1);
        list1 = new DailyList(R.drawable.female, "Marry Smith", "Won 2,159,107");
        list.add(list1);
        list1 = new DailyList(R.drawable.female, "Joshana Fernadis", "Won 1,149,107");
        list.add(list1);
        list1 = new DailyList(R.drawable.female, "Malika", "Won 1,129,107");
        list.add(list1);
        list1 = new DailyList(R.drawable.female, "Priya Singh", "Won 1,119,107");
        list.add(list1);
        dailyAdapter.notifyDataSetChanged();
        weeklyAdapter.notifyDataSetChanged();
    }

}
