package com.example.administrator.finalterm.adapters;

import android.support.v7.widget.RecyclerView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.finalterm.R;

import java.util.ArrayList;
import java.util.List;

public class admin_memberAdapter extends RecyclerView.Adapter<admin_memberAdapter.ViewHolder> {
    public ArrayList<admin_member_data> mData;

    public admin_memberAdapter(ArrayList<admin_member_data>data) { this.mData = data; }

    public void updateData(ArrayList<admin_member_data> data) {
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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_id_manage_item, parent, false);
        // 实例化viewholder
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        // 绑定数据
        holder.membername.setText(mData.get(position).name);
        holder.memberid.setText(String.valueOf(mData.get(position).id));
        holder.memberkind.setText(mData.get(position).identity);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView membername;
        TextView memberid;
        TextView memberkind;

        public ViewHolder(View itemView) {
            super(itemView);
            membername = (TextView) itemView.findViewById(R.id.admin_id_manage_membername);
            memberid = (TextView) itemView.findViewById(R.id.admin_id_manage_memberid);
            memberkind = (TextView) itemView.findViewById(R.id.admin_id_manage_memberidentity);
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
