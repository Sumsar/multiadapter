package com.sumsar.multiadapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Register a adapter delegate per view type
 */
public abstract class AdapterDelegate {

    protected abstract RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent);

    protected abstract void onBindViewHolder(RecyclerView.ViewHolder holder, List items, int itemPosition);

    protected void onViewRecycled(@NonNull RecyclerView.ViewHolder viewHolder) {
        //Empty
    }

    protected boolean onFailedToRecycleView(@NonNull RecyclerView.ViewHolder holder) {
        return false;
    }

    protected void onViewAttachedToWindow(@NonNull RecyclerView.ViewHolder holder) {
        //Empty
    }

    protected void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        //Empty
    }

    public abstract boolean isForViewType(List items, int position);
}
