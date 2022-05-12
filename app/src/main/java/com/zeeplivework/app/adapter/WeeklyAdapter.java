package com.zeeplivework.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.zeeplivework.app.R;
import com.zeeplivework.app.response.DailyList;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class WeeklyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<DailyList> list;

    public WeeklyAdapter(Context context, List<DailyList> list) {
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v1 = null;
        v1 = inflater.inflate(R.layout.weekly_layout, parent, false);
        viewHolder = new myViewHolder(v1);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder hld, int position) {
        try {
            final myViewHolder holder = (myViewHolder) hld;
            holder.user_image_1.setImageResource(list.get(position).getImage());

        } catch (Exception e) {
        }

    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        AppCompatTextView tv_coins_user_1, tv_coins_user_2, tv_coins_user_3;
        CircleImageView user_image_1;

        public myViewHolder(View itemView) {
            super(itemView);
            user_image_1 = itemView.findViewById(R.id.user_image_1);
            tv_coins_user_1 = itemView.findViewById(R.id.tv_coins_user_1);
            tv_coins_user_2 = itemView.findViewById(R.id.tv_coins_user_2);
            tv_coins_user_3 = itemView.findViewById(R.id.tv_coins_user_3);
        }

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }


}