package com.hyperx.wlworktools.test;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.hyperx.wlworktools.R;

import java.util.ArrayList;

/**
 * Created by WYH
 * on2020/1/15
 */
public class TestAdapter extends RecyclerView.Adapter{
    private Context context;
    private ArrayList<String> list;

    public TestAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Holder holder = new Holder(View.inflate(context,R.layout.recy_item,null));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Holder holder = (Holder) viewHolder;
        holder.labelLayout.upData(list.get(i));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder{
        private LabelLayout labelLayout;
        public Holder(@NonNull View itemView) {
            super(itemView);
            labelLayout = itemView.findViewById(R.id.group_view);
        }
    }
}
