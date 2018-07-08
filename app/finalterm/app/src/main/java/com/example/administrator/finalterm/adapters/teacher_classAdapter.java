package com.example.administrator.finalterm.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.finalterm.R;

import java.util.ArrayList;
import java.util.List;

public class teacher_classAdapter  extends RecyclerView.Adapter<teacher_classAdapter.ViewHolder>{
    public ArrayList<teacher_class_data> mData;

    public teacher_classAdapter(ArrayList<teacher_class_data> data) {
        this.mData = data;
    }

    public void updateData(ArrayList<teacher_class_data> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    public interface OnRecyclerViewLongItemClickListener {
        void onLongItemClick(View view, int position);
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }

    static OnRecyclerViewItemClickListener mOnItemClickListener = null;//点击
    static OnRecyclerViewLongItemClickListener mOnLongItemClickListener = null;//长按
    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
    public void setOnLongItemClickListener(OnRecyclerViewLongItemClickListener listener) {
        this.mOnLongItemClickListener = listener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.teacher_list_self_class_item, parent, false);
        // 实例化viewholder
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        // 绑定数据
        holder.classname.setText(mData.get(position).classname);
        holder.classnumber.setText(String.valueOf(mData.get(position).classnumber));
        holder.teachername.setText(mData.get(position).teachername);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView classname;
        TextView classnumber;
        TextView teachername;

        public ViewHolder(View itemView) {
            super(itemView);
            classname = (TextView) itemView.findViewById(R.id.teacher_list_self_classname);
            classnumber = (TextView) itemView.findViewById(R.id.teacher_list_self_classcount);
            teachername = (TextView) itemView.findViewById(R.id.teacher_list_self_teachername);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(v, getAdapterPosition());
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (mOnLongItemClickListener != null) {
                        mOnLongItemClickListener.onLongItemClick(v, getAdapterPosition());

                    }
                    return true;
                }
            });
        }
    }
}
