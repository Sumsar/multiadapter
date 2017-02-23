package com.sumsar.multiadapter.adapters;

import android.view.View;

public interface ItemClickListener<T> {

    void onClick(View view, T item);
}
