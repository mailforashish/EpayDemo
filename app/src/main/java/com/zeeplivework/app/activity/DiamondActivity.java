package com.zeeplivework.app.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAdCallback;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.zeeplivework.app.R;
import com.zeeplivework.app.databinding.ActivityDiamondBinding;
import com.zeeplivework.app.utils.Constants;
import com.zeeplivework.app.utils.NetworkCheck;

public class DiamondActivity extends AppCompatActivity {
    ActivityDiamondBinding binding;
    private final String TAG = "MainActivity";
    private NetworkCheck networkCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_diamond);
        binding.setClickListener(new EventHandler(this));
        networkCheck = new NetworkCheck();


    }


    public class EventHandler {
        Context mContext;

        public EventHandler(Context mContext) {
            this.mContext = mContext;
        }

        public void backDiamondPage() {
            onBackPressed();
        }

        public void playVideo() {
            if (Constants.rewardedAd.isLoaded()) {
                Constants.rewardedAd.show(DiamondActivity.this, new RewardedAdCallback() {
                    @Override
                    public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                        Log.e("DiamondActivity", "RewardItem = " + rewardItem);
                        Toast.makeText(DiamondActivity.this, rewardItem.getAmount() + " Coins your Reward", Toast.LENGTH_SHORT).show();
                    }
                });

            } else {
                Constants.rewardedAd.loadAd(
                        new AdRequest.Builder().build(),
                        new RewardedAdLoadCallback() {
                            @Override
                            public void onRewardedAdLoaded() {
                                Log.e("DiamondActivity", "Error0" + "succses");
                            }

                            @Override
                            public void onRewardedAdFailedToLoad(LoadAdError loadAdError) {
                                Log.e("DiamondActivity", "Error1" + loadAdError);
                            }
                        });

            }


        }


    }
 /* private void loadRewardedVideoAd(){
        AdRequest request = new AdRequest.Builder().build();
        rewardedVideoAd.loadAd("ca-app-pub-3940256099942544/5224354917", request);
        if (rewardedVideoAd.isLoaded()) {
            rewardedVideoAd.show();
        }
    }*/


}