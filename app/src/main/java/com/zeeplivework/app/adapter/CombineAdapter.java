package com.zeeplivework.app.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.zeeplivework.app.R;
import com.zeeplivework.app.response.DailyList;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CombineAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<DailyList> list;
    String type;


    private static final int ITEM = 0;
    private static final int LOADING = 1;
    private boolean isLoadingAdded = false;
    private boolean retryPageLoad = false;
    private String errorMsg;

    public CombineAdapter(Context context, List<DailyList> list, String type) {
        this.context = context;
        this.list = list;
        this.type = type;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case ITEM:
                View v1 = null;
                if (type.equals("dashboard")) {
                    v1 = inflater.inflate(R.layout.daily_layout, parent, false);
                } else if (type.equals("search")) {
                    v1 = inflater.inflate(R.layout.weekly_layout, parent, false);
                }

                viewHolder = new myViewHolder(v1);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder hld, @SuppressLint("RecyclerView") int position) {

        switch (getItemViewType(position)) {
            case ITEM:
                try {
                    final myViewHolder holder = (myViewHolder) hld;

                    if (type.equals("Daily")) {
                        holder.user_image.setImageResource(list.get(position).getImage());
                        holder.user_name.setText(list.get(position).getUser_Name());
                        holder.tv_total_coin.setText(list.get(position).getCoins());
                        // holder.iv_positions.setImageResource();
                    } else {



                    }

                } catch (Exception e) {
                    Log.e("HomeUserAdapter", "HomeAdaterError=> " + e.getMessage());
                }

        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        AppCompatImageView iv_positions;
        CircleImageView user_image;
        TextView  user_name, tv_total_coin;

        AppCompatImageView tv_coins_user_1,tv_coins_user_2,tv_coins_user_3;
        CircleImageView user_image_1;



        public myViewHolder(View itemView) {
            super(itemView);
            user_image_1 = itemView.findViewById(R.id.user_image_1);
            tv_coins_user_1 = itemView.findViewById(R.id.tv_coins_user_1);
            tv_coins_user_2 = itemView.findViewById(R.id.tv_coins_user_2);
            tv_coins_user_3 = itemView.findViewById(R.id.tv_coins_user_3);

            user_image = itemView.findViewById(R.id.user_image);
            user_name = itemView.findViewById(R.id.user_name);
            tv_total_coin = itemView.findViewById(R.id.tv_total_coin);

        }
    }

}