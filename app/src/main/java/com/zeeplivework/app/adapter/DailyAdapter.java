package com.zeeplivework.app.adapter;

import android.content.Context;
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
           // holder.iv_positions.setImageResource();

        } catch (Exception e) {
        }

    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        AppCompatImageView iv_positions;
        CircleImageView user_image;
        TextView  user_name, tv_total_coin;
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