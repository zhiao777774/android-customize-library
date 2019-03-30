package com.example.hsu.customize.view.navigation;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.hsu.customize.R;

import java.util.List;

public class SideNavLayout extends LinearLayout implements View.OnClickListener  {
    private List<SideNavItem> mNavItemList;
    private View mSelectedView;
    private ColorStateList colorStateList;

    public SideNavLayout(Context context) {
        this(context, null);
    }

    public SideNavLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SideNavLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(context, attrs);
    }

    private void initAttr(Context context, AttributeSet attrs) {
        setOrientation(VERTICAL);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SideNavLayout);
        colorStateList = typedArray.getColorStateList(R.styleable.SideNavLayout_itemSelectedColor);
        typedArray.recycle();
    }

    @Override
    public void onClick(View v) {
        mItemClickListener.onItemClick((SideNavItem) v.getTag());
    }

    public void setNavigationItems(List<SideNavItem> navItemList, int resource, int itemIconId, int itemTextId) {
        this.mNavItemList = navItemList;

        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(12,10,12,10);

        if (this.mNavItemList != null && this.mNavItemList.size() > 0) {
            for (int i = 0; i < navItemList.size(); i++) {
                SideNavItemView itemView = new SideNavItemView(getContext(), resource, itemIconId, itemTextId);
                itemView.setDefaultTextColor(colorStateList);
                itemView.setTag(navItemList.get(i));
                itemView.initItemData(navItemList.get(i));
                itemView.setOnClickListener(this);
                addView(itemView, params);
            }
            setSelectionItem(0);
        } else {
            throw new IllegalArgumentException("NavItems can not be empty");
        }
    }

    public void setSelectionItem(int position) {
        if (position >= 0 && position < mNavItemList.size()) {
            View view = getChildAt(position);
            if (mSelectedView != view) {
                view.setSelected(true);
                if (mSelectedView != null) {
                    mSelectedView.setSelected(false);
                }
                mSelectedView = view;
            }
        }
    }

    public View getItem(int position) {
        return getChildAt(position);
    }

    public int getItemId(int position) {
        return getChildAt(position).getId();
    }

    public View getSelectionItem() {
        return this.mSelectedView;
    }

    public int getSelectionItemPosition() {
        return indexOfChild(mSelectedView);
    }

    public void setOnItemSelectedColor(ColorStateList colorStateList) {
        this.colorStateList = colorStateList;
    }

    OnItemClickListener mItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(SideNavItem item);
    }
}
