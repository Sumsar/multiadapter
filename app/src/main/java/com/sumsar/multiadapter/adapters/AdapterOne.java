package com.sumsar.multiadapter.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sumsar.multiadapter.AdapterDelegate;
import com.sumsar.multiadapter.R;

import java.util.List;

public class AdapterOne extends AdapterDelegate {


    private final ItemClickListener<ItemOne> mItemClickListener;

    public AdapterOne(ItemClickListener<ItemOne> itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_one, parent, false);
        return new AdapterOneViewHolder(view, mItemClickListener);
    }

    @Override
    protected void onBindViewHolder(RecyclerView.ViewHolder holder, List items, int itemPosition) {
        //Ugly cast
        ((AdapterOneViewHolder) holder).bind((ItemOne) items.get(itemPosition));
    }

    @Override
    public boolean isForViewType(List items, int position) {
        return items.get(position) instanceof ItemOne;
    }

    private static class AdapterOneViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView mTitle;
        private final ItemClickListener mItemClickListener;
        private ItemOne mData;

        public AdapterOneViewHolder(View itemView, ItemClickListener itemClickListener) {
            super(itemView);
            itemView.setOnClickListener(this);
            mTitle = (TextView) itemView.findViewById(R.id.title);
            mItemClickListener = itemClickListener;
        }

        public void bind(ItemOne data) {
            mData = data;
            mTitle.setText(data.getTitle());
        }

        @Override
        public void onClick(View v) {
            mItemClickListener.onClick(v, mData);
        }
    }
}
