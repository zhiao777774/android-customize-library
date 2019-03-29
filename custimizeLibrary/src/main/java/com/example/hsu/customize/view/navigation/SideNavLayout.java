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
    private List<SideNavItem> navItemList;
    private View selectedView;
    private ColorStateList colorStateList;
    //private ViewPager mViewPager;

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
        setOrientation(HORIZONTAL);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SideNavLayout);
        colorStateList = typedArray.getColorStateList(R.styleable.SideNavLayout_itemSelectedColor);
        /*for (int i = 0; i < typedArray.getIndexCount(); i++) {
            int attr = typedArray.getIndex(i);
            if (attr == R.styleable.SideNavLayout_itemSelectedColor) {
                colorStateList = typedArray.getColorStateList(attr);
                break;
            }
        }*/
        typedArray.recycle();
    }

    @Override
    public void onClick(View v) {
        mItemClickListener.onItemClick((SideNavItem) v.getTag());
    }

    public void setNavigationItems(List<SideNavItem> navItemList, int resource, int itemIconId, int itemTextId) {
        this.navItemList = navItemList;

        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.setMargins(12,9,12,10);

        if (this.navItemList != null && this.navItemList.size() > 0) {
            for (int index = 0; index < navItemList.size(); index++) {
                SideNavItemView itemView = new SideNavItemView(getContext(), resource, itemIconId, itemTextId);
                itemView.setDefaultTextColor(colorStateList);
                itemView.setTag(navItemList.get(index));
                itemView.initItemData(navItemList.get(index));
                itemView.setOnClickListener(this);
                addView(itemView, params);
            }
            setSelectionItem(0);
        } else {
            throw new IllegalArgumentException("NavItems can not be empty");
        }
    }

    public void setSelectionItem(int position) {
        if (position >= 0 && position < navItemList.size()) {
            View view = getChildAt(position);
            if (selectedView != view) {
                view.setSelected(true);
                if (selectedView != null) {
                    selectedView.setSelected(false);
                }
                selectedView = view;
            }
        }
    }

    public void setItemSelectedColor(ColorStateList colorStateList){
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
