package com.zeeplivework.app.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;

import com.zeeplivework.app.R;
import com.zeeplivework.app.databinding.ActivityTabBinding;

public class TabActivity extends AppCompatActivity {
    ActivityTabBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_tab);
        binding.setClickListener(new EventHandler(this));

    }


    public class EventHandler {
        Context mContext;

        public EventHandler(Context mContext) {
            this.mContext = mContext;
        }

        public void tabDiscover() {
            unselectAllMenu();
            binding.imageDiscover.setImageResource(R.drawable.nav_icon_popular_s);

        }

        public void tabLive() {
            unselectAllMenu();
            binding.imageLiveList.setImageResource(R.drawable.nav_icon_live_s);
        }

        public void tabParty() {
            unselectAllMenu();
            binding.imageParty.setImageResource(R.mipmap.nav_icon_lobby_checked);

        }

        public void tabGroup() {
            unselectAllMenu();
            binding.imageGroup.setImageResource(R.drawable.nav_icon_match_s);
        }

        public void tabMine() {
            unselectAllMenu();
            binding.imageMine.setImageResource(R.drawable.nav_icon_me_s);
        }

    }

    private void unselectAllMenu() {
        binding.imageDiscover.setImageResource(R.drawable.bottom_nav_icon_pairing_n);
        binding.imageLiveList.setImageResource(R.drawable.nav_icon_live_n);
        binding.imageParty.setImageResource(R.mipmap.nav_icon_lobby_uncheck);
        binding.imageGroup.setImageResource(R.drawable.nav_icon_match_n);
        binding.imageMine.setImageResource(R.drawable.nav_icon_me_n);

    }

}