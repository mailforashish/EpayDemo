package com.zeeplivework.app.activity;


import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.zeeplivework.app.R;
import com.zeeplivework.app.adapter.DailyAdapter;
import com.zeeplivework.app.adapter.WeeklyAdapter;
import com.zeeplivework.app.databinding.ActivityDailyStarBinding;
import com.zeeplivework.app.response.DailyList;
import com.zeeplivework.app.utils.BaseActivity;
import com.zeeplivework.app.utils.ProgressDrawable;
import com.zeeplivework.app.utils.SegmentedBar;

import java.util.ArrayList;
import java.util.List;

public class DailyStarActivity extends BaseActivity {
    ActivityDailyStarBinding binding;
    DailyAdapter dailyAdapter;
    WeeklyAdapter weeklyAdapter;
    List<DailyList> list = new ArrayList<>();
    private int progressStatus = 0;
    private int currentStatus = 80;
    private Handler handler = new Handler();

    ProgressBar horizontalProgressBar;
    //SegmentedBar horizontalProgressBar;
    //ArrayList<Integer> arrayList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatusBar(getWindow(), true);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_daily_star);
        binding.setClickListener(new EventHandler(this));

        horizontalProgressBar = findViewById(R.id.horizontalProgressBar);
        Drawable d = new ProgressDrawable(0xFFFEAA76, 0xd6d6d6);
        horizontalProgressBar.setProgressDrawable(d);

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
        setProgressData();
    }

    private void setProgressData() {
        Point maxSizePoint = new Point();
        getWindowManager().getDefaultDisplay().getSize(maxSizePoint);
        final int maxX = maxSizePoint.x;

        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < currentStatus) {
                    progressStatus += 1;
                   /* if (progressStatus == 25) {
                        arrayList.add(0);
                        horizontalProgressBar.setEnabledDivisions(arrayList);
                    } else if (progressStatus == 50) {
                        arrayList.add(1);
                        horizontalProgressBar.setEnabledDivisions(arrayList);
                    } else if (progressStatus == 75) {
                        arrayList.add(2);
                        horizontalProgressBar.setEnabledDivisions(arrayList);
                    } else if (progressStatus == 100) {
                        arrayList.add(3);
                        horizontalProgressBar.setEnabledDivisions(arrayList);
                    }*/
                    handler.post(new Runnable() {
                        public void run() {
                            horizontalProgressBar.setProgress(progressStatus);
                            int val = (progressStatus * (horizontalProgressBar.getWidth() - 2)) / horizontalProgressBar.getMax();
                            Log.e("valueofProgress", "" + String.valueOf(val));
                            int textViewX = val - (binding.tvTopBar.getWidth() / 2);
                            int textViewX1 = val - (binding.tvBottomBar.getWidth() / 2);
                            int finalX = binding.tvTopBar.getWidth() + textViewX > maxX ? (maxX - binding.tvTopBar.getWidth() - 16) : textViewX + 16 - 56 /*your margin*/;
                            int finalX1 = binding.tvBottomBar.getWidth() + textViewX1 > maxX ? (maxX - binding.tvBottomBar.getWidth() - 46) : textViewX1 + 16 - 56 /*your margin*/;
                            binding.tvTopBar.setX(finalX < 0 ? 16/*your margin*/ : finalX);
                            binding.tvBottomBar.setX(finalX1 < 0 ? 16  /*your margin*/ : finalX1);
                        }
                    });
                    try {
                        //Sleep for 200 milliseconds.
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


    }


    public class EventHandler {
        Context mContext;

        public EventHandler(Context mContext) {
            this.mContext = mContext;
        }

        public void backPage() {
            startActivity(new Intent(DailyStarActivity.this, WalletActivity.class));
            onBackPressed();
        }

        public void pressTvOne() {
            unSelectedLevel();
            binding.tv1.setBackgroundResource(R.drawable.text_1_bg);
            binding.tv1.setTextColor(getResources().getColor(R.color.white));
        }

        public void pressTvTwo() {
            unSelectedLevel();
            binding.tv2.setBackgroundResource(R.drawable.text_2_bg);
            binding.tv2.setTextColor(getResources().getColor(R.color.white));
        }

        public void pressThree() {
            unSelectedLevel();
            binding.tv3.setBackgroundResource(R.drawable.text_3_bg);
            binding.tv3.setTextColor(getResources().getColor(R.color.white));
        }

        public void pressTvFour() {
            unSelectedLevel();
            binding.tv4.setBackgroundResource(R.drawable.text_4_bg);
            binding.tv4.setTextColor(getResources().getColor(R.color.white));
        }

        public void countyOne() {
            unSelectCountry();
            binding.countryOne.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_country_bg));
            binding.countryOne.setTextColor(Color.WHITE);
        }

        public void countyTwo() {
            unSelectCountry();
            binding.countryTwo.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_country_bg));
            binding.countryTwo.setTextColor(Color.WHITE);
        }

        public void countyThree() {
            unSelectCountry();
            binding.countryThree.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_country_bg));
            binding.countryThree.setTextColor(Color.WHITE);
        }

        public void countyFour() {
            unSelectCountry();
            binding.countryFour.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_country_bg));
            binding.countryFour.setTextColor(Color.WHITE);
        }

        public void countyFive() {
            unSelectCountry();
            binding.countyFive.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_country_bg));
            binding.countyFive.setTextColor(Color.WHITE);
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

    private void unSelectCountry() {
        binding.countryOne.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_country_unselected));
        binding.countryOne.setTextColor(Color.BLACK);
        binding.countryTwo.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_country_unselected));
        binding.countryTwo.setTextColor(Color.BLACK);
        binding.countryThree.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_country_unselected));
        binding.countryThree.setTextColor(Color.BLACK);
        binding.countryFour.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_country_unselected));
        binding.countryFour.setTextColor(Color.BLACK);
        binding.countyFive.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_country_unselected));
        binding.countyFive.setTextColor(Color.BLACK);


    }
    private void unSelectedLevel(){
        binding.tv1.setBackgroundColor(getResources().getColor(R.color.transparent));
        binding.tv1.setTextColor(getResources().getColor(R.color.text_color));
        binding.tv2.setBackgroundColor(getResources().getColor(R.color.transparent));
        binding.tv2.setTextColor(getResources().getColor(R.color.text_color));
        binding.tv3.setBackgroundColor(getResources().getColor(R.color.transparent));
        binding.tv3.setTextColor(getResources().getColor(R.color.text_color));
        binding.tv4.setBackgroundColor(getResources().getColor(R.color.transparent));
        binding.tv4.setTextColor(getResources().getColor(R.color.text_color));
    }


}
