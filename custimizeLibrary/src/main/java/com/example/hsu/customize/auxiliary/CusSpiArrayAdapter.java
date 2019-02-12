package com.example.hsu.customize.auxiliary;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.hsu.customize.R;

//自定義的adapter
public class CusSpiArrayAdapter<T> extends ArrayAdapter<T> {
    private final LayoutInflater mInflater;
    private final Spinner mSpinner;

    public CusSpiArrayAdapter(Context context, int textViewResourceId, T[] objects, Spinner spinner) {
        super(context, textViewResourceId, objects);

        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mSpinner = spinner;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //可透過  position 得知位置，設定每個選項之介面
        View v = mInflater.inflate(R.layout.spinner_dropdown_item, parent, false);

        TextView textView = (TextView) v.findViewById(R.id.txtDropdownItem);
        textView.setText(this.getItem(position).toString());

        int color = v.getContext().getResources().getColor(R.color.colorLightPink);

        if (position == this.mSpinner.getSelectedItemPosition())
            v.setBackgroundColor(color); // 修改被選中的選項顏色
        return v;
    }
}
