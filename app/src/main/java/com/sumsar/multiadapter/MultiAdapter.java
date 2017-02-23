package com.sumsar.multiadapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MultiAdapter extends RecyclerView.Adapter {

    private final AdapterDelegateManager mDelegateManager;

    private List mItems = new ArrayList<>();

    public MultiAdapter() {
        mDelegateManager = new AdapterDelegateManager();
    }

    public <A extends AdapterDelegate> void register(A adapterDelegate) {
        mDelegateManager.register(adapterDelegate);
    }

    public <A extends AdapterDelegate> void unregister(A adapterDelegate) {
        mDelegateManager.unregister(adapterDelegate);
    }

    @Override
    public int getItemViewType(int position) {
        return mDelegateManager.getItemViewType(mItems, position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return mDelegateManager.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        mDelegateManager.onBindViewHolder(holder, mItems, position);
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        mDelegateManager.onViewRecycled(holder);
    }

    @Override
    public boolean onFailedToRecycleView(RecyclerView.ViewHolder holder) {
        return mDelegateManager.onFailedToRecycleView(holder);
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        mDelegateManager.onViewAttachedToWindow(holder);
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        mDelegateManager.onViewDetachedFromWindow(holder);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public List<Object> getItems() {
        return mItems;
    }

    public void addAndNotify(@NonNull List items) {
        final int itemCount = getItemCount();
        mItems.addAll(items);
        notifyItemRangeInserted(itemCount, items.size());
    }

    public void removeAndNotify(@NonNull Object item) {
        final int indexOf = mItems.indexOf(item);
        mItems.remove(indexOf);
        notifyItemRangeRemoved(indexOf, 1);
    }

}




