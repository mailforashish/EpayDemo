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
            holder.tv_date.setText(list.get(position).getDate());
            holder.iv_top_1.setImageResource(list.get(position).getImage());
            holder.iv_top_2.setImageResource(list.get(position).getImage());
            holder.iv_top_3.setImageResource(list.get(position).getImage());

            holder.tv_diamond_user_1.setText("30,00,000");
            holder.tv_diamond_user_2.setText("10,00,000");
            holder.tv_diamond_user_3.setText("10,00,000");

            holder.tv_coins_user_1.setText(numberCalculationKMGTPE(152000));
            holder.tv_coins_user_2.setText(numberCalculationKMGTPE(149000));
            holder.tv_coins_user_3.setText(numberCalculationKMGTPE(127000));

        } catch (Exception e) {
        }

    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        public AppCompatTextView tv_diamond_user_1, tv_diamond_user_2, tv_diamond_user_3, tv_date;
        public TextView tv_coins_user_1, tv_coins_user_2, tv_coins_user_3;
        public CircleImageView iv_top_1, iv_top_2, iv_top_3;

        public myViewHolder(View itemView) {
            super(itemView);
            tv_date = itemView.findViewById(R.id.tv_date);
            iv_top_1 = itemView.findViewById(R.id.iv_top_1);
            iv_top_2 = itemView.findViewById(R.id.iv_top_2);
            iv_top_3 = itemView.findViewById(R.id.iv_top_3);
            tv_diamond_user_1 = itemView.findViewById(R.id.tv_diamond_user_1);
            tv_diamond_user_2 = itemView.findViewById(R.id.tv_diamond_user_2);
            tv_diamond_user_3 = itemView.findViewById(R.id.tv_diamond_user_3);
            tv_coins_user_1 = itemView.findViewById(R.id.tv_coins_user_1);
            tv_coins_user_2 = itemView.findViewById(R.id.tv_coins_user_2);
            tv_coins_user_3 = itemView.findViewById(R.id.tv_coins_user_3);
        }

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    private String numberCalculationKMGTPE(long number) {
        if (number < 1000)
            return "" + number + "K";
        int exp = (int) (Math.log(number) / Math.log(1000));

        return String.format("%.1f %c", number / Math.pow(1000, exp), "KMGTPE".charAt(exp - 1));
    }
}