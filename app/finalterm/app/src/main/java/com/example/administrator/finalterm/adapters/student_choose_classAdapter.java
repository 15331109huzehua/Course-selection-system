package com.example.administrator.finalterm.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.finalterm.R;

import java.util.ArrayList;

public class student_choose_classAdapter extends RecyclerView.Adapter<student_choose_classAdapter.ViewHolder>{
    public ArrayList<student_class_data> mData;

    public student_choose_classAdapter(ArrayList<student_class_data> data) {
        this.mData = data;
    }

    public void updateData(ArrayList<student_class_data> data) {
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
    public student_choose_classAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_class_view_choose_item, parent, false);
        // 实例化viewholder
        student_choose_classAdapter.ViewHolder viewHolder = new student_choose_classAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(student_choose_classAdapter.ViewHolder holder, int position) {
        // 绑定数据
        holder.class_name.setText(mData.get(position).class_name);
        holder.choose_num.setText(String.valueOf(mData.get(position).choose_num));
        holder.teachername.setText(mData.get(position).teachername);

    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView class_name;
        TextView choose_num;
        TextView teachername;


        public ViewHolder(View itemView) {
            super(itemView);
            class_name = (TextView) itemView.findViewById(R.id.student_chooseclass_classname);
            choose_num = (TextView) itemView.findViewById(R.id.student_chooseclass_classnumber);
            teachername = (TextView) itemView.findViewById(R.id.student_chooseclass_teachername);

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
