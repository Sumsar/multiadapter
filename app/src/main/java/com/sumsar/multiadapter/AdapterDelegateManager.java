package com.sumsar.multiadapter;

import android.support.annotation.Nullable;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;
import java.util.Objects;

public class AdapterDelegateManager {

    private final SparseArrayCompat<AdapterDelegate> mDelegates = new SparseArrayCompat();

    public <A extends AdapterDelegate> void register(A adapter) {
        addDelegate(adapter);
    }

    public <A extends AdapterDelegate> void unregister(A adapter) {
        removeDelegate(adapter);
    }

    private void removeDelegate(AdapterDelegate delegate) {
        int indexToRemove = mDelegates.indexOfValue(delegate);

        if (indexToRemove >= 0) {
            mDelegates.removeAt(indexToRemove);
        }

    }

    private void addDelegate(AdapterDelegate delegate) {
        Objects.requireNonNull(delegate, "delegate is null");
        int viewType = mDelegates.size();
        while (mDelegates.get(viewType) != null) {
            viewType++;
        }
        addDelegate(viewType, delegate);
    }


    private void addDelegate(int viewType, AdapterDelegate delegate) {
        mDelegates.put(viewType, delegate);
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, List items, int itemPosition) {
        AdapterDelegate delegate = getDelegateForViewType(holder.getItemViewType());
        //Calculate itemPosition
        delegate.onBindViewHolder(holder, items, itemPosition);
    }


    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AdapterDelegate delegate = getDelegateForViewType(viewType);
        return delegate.onCreateViewHolder(parent);
    }

    @Nullable
    protected AdapterDelegate getDelegateForViewType(int viewType) {
        return mDelegates.get(viewType);
    }

    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        AdapterDelegate delegate = getDelegateForViewType(holder.getItemViewType());
        delegate.onViewRecycled(holder);
    }

    public boolean onFailedToRecycleView(RecyclerView.ViewHolder holder) {
        AdapterDelegate delegate = getDelegateForViewType(holder.getItemViewType());
        return delegate.onFailedToRecycleView(holder);
    }

    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        AdapterDelegate delegate = getDelegateForViewType(holder.getItemViewType());
        delegate.onViewAttachedToWindow(holder);
    }

    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        AdapterDelegate delegate = getDelegateForViewType(holder.getItemViewType());
        delegate.onViewDetachedFromWindow(holder);
    }

    public int getItemViewType(List<Object> items, int position) {
        Objects.requireNonNull(items, "Items is null");
        int delegatesCount = mDelegates.size();
        for (int i = 0; i < delegatesCount; i++) {
            AdapterDelegate delegate = mDelegates.valueAt(i);
            if (delegate.isForViewType(items, position)) {
                return mDelegates.keyAt(i);
            }
        }

        throw new NullPointerException(
                "No AdapterDelegate matches position=" + position + " in data source");
    }
}
