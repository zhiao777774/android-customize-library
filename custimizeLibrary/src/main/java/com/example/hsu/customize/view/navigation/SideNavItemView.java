package com.example.hsu.customize.view.navigation;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hsu.customize.R;


public final class SideNavItemView extends LinearLayout {
    private int mResource, mImgResId, mTextResId;

    private ColorStateList colorStateList;
    private ImageView mImgItemIcon;
    private TextView mTxtItemText;

    public SideNavItemView(Context context) {
        this(context, null, R.layout.side_navigation_item, R.id.item_icon, R.id.item_text);
    }

    public SideNavItemView(Context context, AttributeSet attrs) {
        this(context, attrs, R.layout.side_navigation_item, R.id.item_icon, R.id.item_text);
    }

    public SideNavItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, R.layout.side_navigation_item, R.id.item_icon, R.id.item_text);
    }

    public SideNavItemView(Context context, int resource, int itemIconId, int itemTextId) {
        this(context, null, resource, itemIconId, itemTextId);
    }

    public SideNavItemView(Context context, AttributeSet attrs, int resource, int itemIconId, int itemTextId) {
        this(context, attrs, 0, resource, itemIconId, itemTextId);
    }

    public SideNavItemView(Context context, AttributeSet attrs, int defStyleAttr, int resource, int itemIconId, int itemTextId) {
        super(context, attrs, defStyleAttr);
        setResourceView(resource, itemIconId, itemTextId);
        initView(context);
    }

    void setDefaultTextColor(ColorStateList colorStateList) {
        this.colorStateList = colorStateList;
    }

    private void setResourceView(int resource, int itemIconId, int itemTextId) {
        this.mResource = resource;
        this.mImgResId = itemIconId;
        this.mTextResId = itemTextId;
    }

    private void initView(Context context) {
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER);
        if (mResource == R.layout.side_navigation_item) {
            mImgItemIcon = (ImageView) findViewById(mImgResId);
            mTxtItemText = (TextView) findViewById(mTextResId);
        } else {
            View inflate = inflate(context, mResource, this);
            mImgItemIcon = (ImageView) inflate.findViewById(mImgResId);
            mTxtItemText = (TextView) inflate.findViewById(mTextResId);
        }
    }

    void initItemData(SideNavItem item) {
        if (mImgItemIcon != null && mTxtItemText != null && colorStateList != null) {
            mImgItemIcon.setImageResource(item.getImageResId());
            mTxtItemText.setTextColor(colorStateList);
            mTxtItemText.setText(item.getTextResId());
        }
    }
}
