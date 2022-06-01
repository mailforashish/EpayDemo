package com.zeeplivework.app.adapter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.zeeplivework.app.R;
import com.zeeplivework.app.response.DailyList;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class DailyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<DailyList> list;

    public DailyAdapter(Context context, List<DailyList> list) {
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v1 = null;
        v1 = inflater.inflate(R.layout.daily_layout, parent, false);
        viewHolder = new myViewHolder(v1);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder hld, int position) {
        try {
            final myViewHolder holder = (myViewHolder) hld;
            holder.user_image.setImageResource(list.get(position).getImage());
            holder.user_name.setText(list.get(position).getUser_Name());
            holder.tv_total_coin.setText(list.get(position).getCoins());
            if (position == 0) {
                holder.iv_positions.setImageResource(R.mipmap.gold_medal_1);
                holder.iv_positions.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.ic_pos1));
            } else if (position == 1) {
                holder.iv_positions.setImageResource(R.mipmap.silver_medal_2);
                holder.iv_positions.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.ic_pos2));
            } else if (position == 2) {
                holder.iv_positions.setImageResource(R.mipmap.bronze_medal_3);
                holder.iv_positions.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.ic_pos3));
            } else if (position == 3) {
                holder.iv_positions.setImageResource(R.mipmap.pos_4);
                holder.iv_positions.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.ic_defoult));
            } else if (position == 4) {
                holder.iv_positions.setImageResource(R.mipmap.pos_5);
                holder.iv_positions.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.ic_defoult));
            } else if (position == 5) {
                holder.iv_positions.setImageResource(R.mipmap.pos_6);
                holder.iv_positions.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.ic_defoult));
            } else if (position == 6) {
                holder.iv_positions.setImageResource(R.mipmap.pos_7);
                holder.iv_positions.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.ic_defoult));
            }

        } catch (Exception e) {
        }

    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        AppCompatImageView iv_positions;
        CircleImageView user_image;
        TextView user_name, tv_total_coin;

        public myViewHolder(View itemView) {
            super(itemView);
            iv_positions = itemView.findViewById(R.id.iv_positions);
            user_image = itemView.findViewById(R.id.user_image);
            user_name = itemView.findViewById(R.id.user_name);
            tv_total_coin = itemView.findViewById(R.id.tv_total_coin);
        }

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }


}