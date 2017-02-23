package com.sumsar.multiadapter.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sumsar.multiadapter.AdapterDelegate;
import com.sumsar.multiadapter.R;

import java.util.List;

public class AdapterTwo extends AdapterDelegate {
    private final ItemClickListener<ItemTwo> mItemClickListener;

    public AdapterTwo(ItemClickListener<ItemTwo> itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_two, parent, false);
        return new AdapterTwo.AdapterTwoViewHolder(view, mItemClickListener);
    }

    @Override
    protected void onBindViewHolder(RecyclerView.ViewHolder holder, List items, int itemPosition) {
        //Ugly cast
        ((AdapterTwo.AdapterTwoViewHolder) holder).bind((ItemTwo) items.get(itemPosition));
    }

    @Override
    public boolean isForViewType(List items, int position) {
        return items.get(position) instanceof ItemTwo;
    }


    public static class AdapterTwoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView mTitle;
        private final ItemClickListener<ItemTwo> mItemTwoItemClickListener;
        private ItemTwo mData;

        public AdapterTwoViewHolder(View itemView, ItemClickListener<ItemTwo> itemTwoItemClickListener) {
            super(itemView);
            mItemTwoItemClickListener = itemTwoItemClickListener;
            itemView.setOnClickListener(this);
            mTitle = (TextView) itemView.findViewById(R.id.title);
        }

        public void bind(ItemTwo data) {
            mData = data;
            mTitle.setText(data.getTitle());
        }

        @Override
        public void onClick(View v) {
            mItemTwoItemClickListener.onClick(v, mData);
        }
    }
}
