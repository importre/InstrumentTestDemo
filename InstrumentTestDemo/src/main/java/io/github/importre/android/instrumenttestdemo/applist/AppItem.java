package io.github.importre.android.instrumenttestdemo.applist;

import android.graphics.drawable.Drawable;

public class AppItem {

    private final Drawable mIcon;
    private final String mName;

    public AppItem(Drawable icon, String name) {
        mIcon = icon;
        mName = name;
    }

    public Drawable getIcon() {
        return mIcon;
    }

    public String getName() {
        return mName;
    }
}
