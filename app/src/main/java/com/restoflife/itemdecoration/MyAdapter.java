package com.restoflife.itemdecoration;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 余俊魁 on 2017/3/20.
 * Version 1
 * Function
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    public ArrayList<String> datas = null;

    private int itemLayoutId;

    public MyAdapter(ArrayList<String> datas) {
        this.datas = datas;
        itemLayoutId = R.layout.item;
    }

    public MyAdapter(ArrayList<String> datas, int itemLayoutId) {
        this.datas = datas;
        this.itemLayoutId = itemLayoutId;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(itemLayoutId, viewGroup, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.tv.setText(datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv;

        public ViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.tv);
        }
    }
}
