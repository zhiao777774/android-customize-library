package com.example.hsu.customize.auxiliary.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import java.util.List;

public class TableAdapter<T> extends BaseAdapter {
    private final List<T> mList;
    private final int mResource;

    private final LayoutInflater mInflater;

    protected ViewHolder<T> mViewHolder;

    public TableAdapter(Context context, List<T> data, int resource) {
        this.mInflater = LayoutInflater.from(context);
        this.mList = data;
        this.mResource = resource;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final T data = (T) this.getItem(position);
        ViewHolder holder;

        if(convertView == null){
            holder = this.mViewHolder;
            convertView = mInflater.inflate(mResource, null);
            holder.initView(convertView);
            convertView.setTag(holder);
            Log.d("holder", "new");
        }else{
            holder = (ViewHolder) convertView.getTag();
            Log.d("holder", "reusing");
        }
        holder.createViewText(data, position);
        return convertView;
    }

    public void setViewHolder(ViewHolder<T> h){
        this.mViewHolder = h;
    }

    public interface ViewHolder<T>{
        //should  implements this interface and  override methods
        void initView(View view);
        void createViewText(T obj, int position);
    };
}
