package com.example.hsu.customize.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public final class StretchyTextView extends LinearLayout implements View.OnClickListener {
    public static final int DEFAULT_MAX_LINES = 2;
    private int maxLines = DEFAULT_MAX_LINES;

    public boolean isOnClickForSpread;
    private boolean flag;
    private State mState;

    private final Context mContext;
    private StretchyViewAdapter mViewAdapter;

    private TextView mContentView;
    private View mTransView;

    private Drawable mBackground;

    public StretchyTextView(Context context) {
        super(context);
        mContext = context;
    }

    public StretchyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    private void createViewFromResource(View inflate, int contentViewId, int transViewId, String text) {
        mContentView = inflate.findViewById(contentViewId);
        mTransView = inflate.findViewById(transViewId);

        mBackground = mContentView.getBackground();

        if(checkViewIsExistOrLegal()) {
            mTransView.setOnClickListener(this);
            setContentViewText(text);
        }
    }

    private boolean checkViewIsExistOrLegal() {
        if(mContentView == null){
            throw new NullPointerException("ContentViewId is null pointer, please check your content view id");
        }else if(mTransView == null){
            throw new NullPointerException("TransViewId is null pointer, please check your content view id");
        }

        if(!(mTransView instanceof TextView || mTransView instanceof ImageView || mTransView instanceof Button)){
            throw new RuntimeException("TransView only can be a TextView, ImageView or Button");
        }
        return true;
    }

    private void setContentViewText(String text) {
        mState = State.SPREAD;
        mContentView.setText(text);
        requestLayout();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if(!flag){
            flag = true;
            if(mContentView.getLineCount() <= maxLines){
                mState = State.NONE;
                mTransView.setVisibility(GONE);
                mContentView.setMaxLines(maxLines);
            }
            else post(new ActionThread());
        }
    }

    @Override
    public void onClick(View v) {
        flag = false;
        requestLayout();
        mContentView.setEllipsize(null);
    }

    public void setMaxLines(int maxLines) {
        if(maxLines < 0) throw new ArithmeticException("MaxLines must be greater or equal than zero");
        this.maxLines = maxLines;
    }

    public int getMaxLines() {
        return this.maxLines;
    }

    public State getState() {
        return this.mState;
    }

    public void setStretchyViewAdapter(StretchyViewAdapter adapter) {
        if(mViewAdapter == null){
            mViewAdapter = adapter;
        }
        createViewFromResource(inflate(mContext, mViewAdapter.resource, this), mViewAdapter.contentViewId,
                mViewAdapter.transViewId, mViewAdapter.content);
    }

    public final static class StretchyViewAdapter {
        private int resource, contentViewId, transViewId;
        private String content;

        public StretchyViewAdapter(int resource, int contentViewId, int transViewId, String content) {
            this.resource = resource;
            this.contentViewId = contentViewId;
            this.transViewId = transViewId;
            this.content = content;
        }
    }

    private final class ActionThread implements Runnable {
        @Override
        public void run() {
            switch (mState){
                case SPREAD:
                    isOnClickForSpread = false;
                    mContentView.setMaxLines(maxLines);
                    mContentView.setBackground(null);
                    mContentView.setEllipsize(TextUtils.TruncateAt.END);
                    mState = State.SHRINK;
                    break;
                case SHRINK:
                    isOnClickForSpread = true;
                    mContentView.setMaxLines(Integer.MAX_VALUE);
                    mContentView.setBackground(mBackground);
                    mState = State.SPREAD;
                    break;
            }
            mTransView.setVisibility(VISIBLE);
        }
    }

    public enum State {
        NONE, SHRINK, SPREAD
    }
}
