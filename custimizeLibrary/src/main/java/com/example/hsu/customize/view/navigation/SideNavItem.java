package com.example.hsu.customize.view.navigation;

import android.app.Activity;

public final class SideNavItem {
    private int imageResId;
    private int textResId;
    private Class<? extends Activity> actCls;

    public SideNavItem(int imageResId, int textResId) {
        this.imageResId = imageResId;
        this.textResId = textResId;
    }

    public SideNavItem(int imageResId, int textResId, Class<? extends Activity> actCls) {
        this.imageResId = imageResId;
        this.textResId = textResId;
        this.actCls = actCls;
    }

    public int getImageResId() {
        return this.imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public int getTextResId() {
        return this.textResId;
    }

    public void setTextResId(int textResId) {
        this.textResId = textResId;
    }

    public Class<? extends Activity> getActCls() {
        return this.actCls;
    }

    public void setActCls(Class<? extends Activity> actCls) {
        this.actCls = actCls;
    }
}
