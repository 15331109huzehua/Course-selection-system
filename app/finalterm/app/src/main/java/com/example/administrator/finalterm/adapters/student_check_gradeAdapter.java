package com.example.administrator.finalterm.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.finalterm.R;

import java.util.ArrayList;

public class student_check_gradeAdapter  extends RecyclerView.Adapter<student_check_gradeAdapter.ViewHolder>{
    private ArrayList<student_grade_data> mData;

    public student_check_gradeAdapter(ArrayList<student_grade_data> data) {
        this.mData = data;
    }

    public void updateData(ArrayList<student_grade_data> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_list_grade_item, parent, false);
        // 实例化viewholder
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // 绑定数据
        holder.class_name.setText(mData.get(position).class_name);
        holder.teacher_name.setText(mData.get(position).teacher_name);
        holder.result.setText(String.valueOf(mData.get(position).result));
        
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView class_name;
        TextView teacher_name;
        TextView result;
        

        public ViewHolder(View itemView) {
            super(itemView);
            class_name = (TextView) itemView.findViewById(R.id.student_grade_classname);
            teacher_name = (TextView) itemView.findViewById(R.id.student_grade_teachername);
            result = (TextView) itemView.findViewById(R.id.student_grade_result);
            
        }
    }

}
