package com.sumsar.multiadapter.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sumsar.multiadapter.AdapterDelegate;
import com.sumsar.multiadapter.R;

import java.util.List;

public class AdapterThree extends AdapterDelegate {

    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_three, parent, false);
        return new AdapterThree.AdapterThreeViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(RecyclerView.ViewHolder holder, List items, int itemPosition) {
        //Ugly cast
        ((AdapterThree.AdapterThreeViewHolder) holder).bind((ItemThree) items.get(itemPosition));
    }

    @Override
    public boolean isForViewType(List items, int position) {
        return items.get(position) instanceof ItemThree;
    }

    public static class AdapterThreeViewHolder extends RecyclerView.ViewHolder {

        private final TextView mTitle;

        public AdapterThreeViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.title);
        }

        public void bind(ItemThree data) {
            mTitle.setText(data.getTitle());
        }
    }
}
