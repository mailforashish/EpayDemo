package com.zeeplivework.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.zeeplivework.app.R;
import com.zeeplivework.app.response.BankList.Bank;
import com.zeeplivework.app.utils.BankSelected;
import com.zeeplivework.app.utils.PaginationAdapterCallback;

import java.util.ArrayList;
import java.util.List;

public class BankAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Bank> list;
    Context context;
    BankSelected bankSelected;
    private PaginationAdapterCallback mCallback;
    private static final int ITEM = 0;
    private static final int LOADING = 1;
    private boolean isLoadingAdded = false;
    private boolean retryPageLoad = false;
    private String errorMsg;

    public BankAdapter(Context context, BankSelected bankSelected, PaginationAdapterCallback mCallback) {
        this.list = new ArrayList<>();
        this.context = context;
        this.bankSelected = bankSelected;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case ITEM:
                View v1 = null;
                v1 = inflater.inflate(R.layout.bank_layout, parent, false);
                viewHolder = new myViewHolder(v1);
                break;

            case LOADING:
                View v2 = inflater.inflate(R.layout.item_progress, parent, false);
                viewHolder = new LoadingVH(v2);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder hld, int position) {

        switch (getItemViewType(position)) {
            case ITEM:
                try {
                    final myViewHolder holder = (myViewHolder) hld;
                    holder.bankName.setText(list.get(position).getBankName());
                    holder.bankId.setText(list.get(position).getBankId());
                    holder.constraint_main_bank.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            bankSelected.getBank(true, list.get(position).getAddress(), list.get(position).getBankId(),
                                    list.get(position).getBankName(), list.get(position).getCity(),
                                    list.get(position).getBankBranch(), list.get(position).getLocationId());
                        }
                    });
                    break;
                } catch (Exception e) {
                }

            case LOADING:
                if (retryPageLoad) {

                } else {

                }
                break;
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == list.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }


    public class myViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout constraint_main_bank;
        TextView bankName, bankId;

        public myViewHolder(View itemView) {
            super(itemView);
            constraint_main_bank = itemView.findViewById(R.id.constraint_main_bank);
            bankName = itemView.findViewById(R.id.bankName);
            bankId = itemView.findViewById(R.id.bankId);
        }
    }

    protected class LoadingVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ProgressBar mProgressBar;
        private ImageButton mRetryBtn;
        private TextView mErrorTxt;
        private LinearLayout mErrorLayout;

        public LoadingVH(View itemView) {
            super(itemView);
            mProgressBar = itemView.findViewById(R.id.loadmore_progress);
            mRetryBtn = itemView.findViewById(R.id.loadmore_retry);
            mErrorTxt = itemView.findViewById(R.id.loadmore_errortxt);
            mErrorLayout = itemView.findViewById(R.id.loadmore_errorlayout);
            mRetryBtn.setOnClickListener(this);
            mErrorLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.loadmore_retry:
                    // Show loader here
                    if (isLoadingAdded) {
                        mProgressBar.setVisibility(View.VISIBLE);
                    }
                case R.id.loadmore_errorlayout:
                    mProgressBar.setVisibility(View.INVISIBLE);
                    showRetry(false, null);
                    mCallback.retryPageLoad();
                    break;
            }
        }

    }

    public void add(Bank results) {
        list.add(results);
        notifyItemInserted(list.size() - 1);
    }

    public void addAll(List<Bank> moveResults) {
        for (Bank result : moveResults) {
            add(result);
        }
    }

    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new Bank());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;
        int position = list.size() - 1;
        Bank result = getItem(position);

        if (result != null) {
            list.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void showRetry(boolean show, @Nullable String errorMsg) {
        retryPageLoad = show;
        notifyItemChanged(list.size() - 1);
        if (errorMsg != null) this.errorMsg = errorMsg;
    }

    public Bank getItem(int position) {
        return list.get(position);
    }
}
