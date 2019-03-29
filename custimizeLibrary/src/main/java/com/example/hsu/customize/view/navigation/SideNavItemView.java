package com.example.hsu.customize.view.navigation;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hsu.customize.R;
import com.example.hsu.customize.view.navigation.SideNavItem;

public final class SideNavItemView extends LinearLayout {
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

    private void initView(Context context) {
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER);
        LayoutInflater.from(context).inflate(R.layout.side_navigation_item, this, true);
        mImgItemIcon = (ImageView) findViewById(R.id.item_icon);
        mTxtItemText = (TextView) findViewById(R.id.item_text);
    }

    void initItemData(SideNavItem item) {
        if (mImgItemIcon != null && mTxtItemText != null && colorStateList != null) {
            mImgItemIcon.setImageResource(item.getImageResId());
            mTxtItemText.setTextColor(colorStateList);
            mTxtItemText.setText(item.getTextResId());
        }
    }
}
