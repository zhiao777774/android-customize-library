package com.example.hsu.customize.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.support.v7.app.AlertDialog;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.example.hsu.customize.R;

@SuppressLint("AppCompatCustomView")
public class SpinnerTextView extends TextView implements View.OnClickListener {
    private int mSelection;
    private String mTitle;
    private String mPrompt;
    private CharSequence[] mEntries;

    protected OnItemSelectedListener mListener;

    public SpinnerTextView(Context context) {
        super(context);
        initAttr(null);
    }

    public SpinnerTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttr(attrs);
    }

    public SpinnerTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initAttr(attrs);
    }

    private void initAttr(AttributeSet attrs) {
        //添加自定義的屬性
        if (attrs != null) {
            //透過obtainStyledAttributes方法獲取TypedArray
            TypedArray typedArray = this.getContext().obtainStyledAttributes(attrs, R.styleable.SpinnerTextView);
            //使用TypedArray獲取對應的屬性
            this.mPrompt = typedArray.getString(R.styleable.SpinnerTextView_android_prompt);
            this.mEntries = typedArray.getTextArray(R.styleable.SpinnerTextView_android_entries);
            typedArray.recycle(); //回收TypedArray
        }

        mSelection = -1;
        mTitle = mPrompt = (mPrompt == null) ? "" : mPrompt;

        setText(mPrompt);
        setOnClickListener(this);
    }

    public String getSelectedItem() {
        if (mSelection < 0 || mSelection >= mEntries.length) {
            return null;
        } else {
            return mEntries[mSelection].toString();
        }
    }

    public int getSelectedItemPosition() {
        return this.mSelection;
    }

    public void setSelection(int selection) {
        this.mSelection = selection;

        if (selection < 0) {
            setText(mPrompt);
        } else if (selection < mEntries.length) {
            setText(mEntries[mSelection]);
        }
    }

    public void setOnItemSelectedListener(OnItemSelectedListener listener) {
        this.mListener = listener;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    @Override
    public void onClick(View v) {
        new AlertDialog.Builder(getContext())
                .setTitle(mTitle)
                .setItems(mEntries, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mSelection = which;
                        setText(mEntries[mSelection]);
                        if (mListener != null) {
                            mListener.onItemSelected(which);
                        }
                    }
                }).create().show();
    }

    //Listener
    public interface OnItemSelectedListener {
        void onItemSelected(int position);
    }
}
