package com.example.hsu.customize.view.navigation;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public final class SideNavItemView extends LinearLayout {
    private int mResource, mItemIconId, mItemTextId;

    private ColorStateList colorStateList;
    private ImageView mImgItemIcon;
    private TextView mTxtItemText;

    public SideNavItemView(Context context) {
        this(context, null);
    }

    public SideNavItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SideNavItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    void setDefaultTextColor(ColorStateList colorStateList) {
        this.colorStateList = colorStateList;
    }

    void setResourceView(int resource, int itemIconId, int itemTextId) {
        this.mResource = resource;
        this.mItemIconId = itemIconId;
        this.mItemTextId = itemTextId;
    }

    private void initView(Context context) {
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER);
        View inflate = inflate(context, mResource, this);
        mImgItemIcon = (ImageView) inflate.findViewById(mItemIconId);
        mTxtItemText = (TextView) inflate.findViewById(mItemTextId);
    }

    void initItemData(SideNavItem item) {
        if (mImgItemIcon != null && mTxtItemText != null && colorStateList != null) {
            mImgItemIcon.setImageResource(item.getImageResId());
            mTxtItemText.setTextColor(colorStateList);
            mTxtItemText.setText(item.getTextResId());
        }
    }
}
