package io.github.importre.android.instrumenttestdemo.applist;

import android.graphics.drawable.Drawable;

public class AppItem {

    private final Drawable mIcon;
    private final String mName;
    private boolean mSystem;

    public AppItem(Drawable icon, String name, boolean isSystem) {
        mIcon = icon;
        mName = name;
        mSystem = isSystem;
    }

    public Drawable getIcon() {
        return mIcon;
    }

    public String getName() {
        return mName;
    }

    public boolean isSystem() {
        return mSystem;
    }
}
