package com.example.administrator.finalterm.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.finalterm.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/6/26.
 */

public class result_classAdapter extends RecyclerView.Adapter<result_classAdapter.ViewHolder> {
    public ArrayList<result_class_data> mData;

    public result_classAdapter(ArrayList<result_class_data> data) {
        this.mData = data;
    }

    public void updateData(ArrayList<result_class_data> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }
    static OnRecyclerViewItemClickListener mOnItemClickListener = null;//点击

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.teacher_list_all_class_item, parent, false);
        // 实例化viewholder
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        // 绑定数据
        holder.studentnum.setText(String.valueOf(mData.get(position).studentnum));
        holder.studentname.setText(mData.get(position).studentname);
        holder.studentgrade.setText(String.valueOf(mData.get(position).studentgrade));
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView studentnum;
        TextView studentname;
        TextView studentgrade;

        public ViewHolder(View itemView) {
            super(itemView);
            studentnum = (TextView) itemView.findViewById(R.id.teacher_list_all_class_item_number);
            studentname = (TextView) itemView.findViewById(R.id.teacher_list_all_class_item_name);
            studentgrade = (TextView) itemView.findViewById(R.id.teacher_list_all_class_item_grade);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(v, getAdapterPosition());
                    }
                }
            });
        }
    }
}
