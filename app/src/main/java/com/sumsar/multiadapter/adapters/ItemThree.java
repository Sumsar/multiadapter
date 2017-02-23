package com.sumsar.multiadapter.adapters;

public class ItemThree {

    private final String mTitle;

    public ItemThree(String title) {
        mTitle = title;
    }

    public String getTitle() {
        return getClass().getSimpleName() + " " + mTitle;
    }

}
