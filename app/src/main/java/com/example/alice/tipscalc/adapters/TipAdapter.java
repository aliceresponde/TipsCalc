package com.example.alice.tipscalc.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.alice.tipscalc.R;
import com.example.alice.tipscalc.models.TipRecord;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by alice onItemClickListener 6/5/16.
 */

public class TipAdapter extends RecyclerView.Adapter<TipAdapter.ViewHolder> {
    private Context context;
    private List<TipRecord> dataset;
    private OnItemClickListener onItemClickListener;

    public TipAdapter(Context context, OnItemClickListener listener) {
        this.dataset = new ArrayList<TipRecord>();
        this.context = context;
        this.onItemClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //asocio layout al recyclerView per  row
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tips_history_row , parent , false);
        //creo el  viewHolder
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TipRecord tip  = dataset.get(position);
        String strTip = String.format(
                            context.getString(R.string.global_message_tip),
                            tip.getTip());

        holder.txtContent.setText(strTip);
        holder.setOnItemClickListener(tip, onItemClickListener);
    }

    public void add(TipRecord tip){
        dataset.add(0, tip);
        notifyDataSetChanged();
    }

    public void clear(){
        dataset.clear();
        notifyDataSetChanged();
    }



    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.txtContent)
        TextView  txtContent;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setOnItemClickListener(final TipRecord tip, final OnItemClickListener onItemClickListener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClicked(tip);
                }
            });
        }
    }
}
