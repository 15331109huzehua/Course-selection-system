package com.example.administrator.finalterm;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Toast;

import com.example.administrator.finalterm.adapters.*;
import com.example.administrator.finalterm.datainterface.classinterface;
import com.example.administrator.finalterm.datainterface.scheduleinterface;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class studentclassactivity extends AppCompatActivity {
    private RecyclerView mRecycleView1;
    private RecyclerView.LayoutManager mLayoutManager1;
    private student_choose_classAdapter mAdapter1;
    private RecyclerView mRecycleView2;
    private RecyclerView.LayoutManager mLayoutManager2;
    private student_drop_classAdapter mAdapter2;
    int id=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_class_view);    //加载查选课程界面
        Bundle bundle=this.getIntent().getExtras();
        id=bundle.getInt("id");    //获取上一个页面传递的参数，即账号
        initData();    //从服务器接收数据
    }

    //从服务器接收数据
    private void initData() {
        mLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mLayoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        final String mid = String.valueOf(id);
        //先接收可选课程
        getchoosedata(mid);
    }

    //接收可选课程。参数为：String型的账号
    private void getchoosedata(final String mid){
        String url_canchooseclass = "http://172.18.156.214:8000/classinterface_getchoosedata/" + mid + "/";
        AsynNetUtils.get(url_canchooseclass, new AsynNetUtils.Callback() {
            @Override
            public void onResponse(String response) {
                //重写onResponse(String response)方法，response为服务器的响应字符串，即JSON数据
                //解析response中的JSON数据获取可选课程的ArrayList
                final ArrayList<student_class_data> mlist = analysisArray(response, student_class_data.class);
                //然后接收已选课程
                getdropdata(mid, mlist);
            }
        });
    }

    //接收已选课程。参数分别为：String型的账号，ArrayList型的可选课程
    private void getdropdata(final String mid, final ArrayList<student_class_data> mlist){
        String url_havechoosedclass = "http://172.18.156.214:8000/classinterface_getdropdata/" + mid + "/";
        AsynNetUtils.get(url_havechoosedclass, new AsynNetUtils.Callback() {
            @Override
            public void onResponse(String response) {
                //重写onResponse(String response)方法，response为服务器的响应字符串，即JSON数据
                //解析response中的JSON数据获取已选课程的ArrayList并设置adapter
                ArrayList<student_class_data> dlist = analysisArray(response, student_class_data.class);
                mAdapter1 = new student_choose_classAdapter(mlist);
                mAdapter2 = new student_drop_classAdapter(dlist);
                initView();    //加载列表
            }
        });
    }

    //解析字符串中的JSON数据，并返回JSON数组
    public static ArrayList<student_class_data> analysisArray(String json, Type type) {
        ArrayList<student_class_data> mlist = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                student_class_data o = new Gson().fromJson(String.valueOf(jsonArray.get(i)), type);
                mlist.add(o);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mlist;
    }

    //加载列表
    private void initView() {
        //设置布局管理器
        initRecycleViews();
        //设置可选课程列表item的点击事件
        setAdapter1();
        //设置已选课程列表item的点击事件
        setAdapter2();
    }

    //设置布局管理器
    private void initRecycleViews(){
        mRecycleView1 = (RecyclerView) findViewById(R.id.student_choose_class_recycleview);
        // 设置布局管理器
        mRecycleView1.setLayoutManager(mLayoutManager1);
        mRecycleView2 = (RecyclerView) findViewById(R.id.student_drop_class_recycleview);
        // 设置布局管理器
        mRecycleView2.setLayoutManager(mLayoutManager2);
    }

    //设置可选课程列表item的点击事件
    private void setAdapter1(){
        mAdapter1.setOnItemClickListener(new student_choose_classAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {
                final AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(studentclassactivity.this);
                alertDialogBuilder.setTitle("确定要选课？")
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setPositiveButton("确定",  new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                chooseClass(position);    //选课
                            }
                        }).setNegativeButton("取消", null).show();
            }
        });
        mRecycleView1.setAdapter(mAdapter1);    // 设置adapter
    }

    //设置已选课程列表item的点击事件
    private void setAdapter2(){
        mAdapter2.setOnItemClickListener(new student_choose_classAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {
                final AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(studentclassactivity.this);
                alertDialogBuilder.setTitle("确定要退课？")
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setPositiveButton("确定",  new DialogInterface.OnClickListener() {// 积极
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dropClass(position);    //退课
                            }
                        }).setNegativeButton("取消", null).show();
            }
        });
        mRecycleView2.setAdapter(mAdapter2);    // 设置adapter
    }

    //选课。参数为int型的item的位置
    private void chooseClass(final int position){
        if(mAdapter1.mData.get(position).choose_num == 0) Toast.makeText(studentclassactivity.this, "人数已满,选课失败!", Toast.LENGTH_SHORT).show();
        else{
            String mid = String.valueOf(id);    //账号
            String classid = String.valueOf(mAdapter1.mData.get(position).classid);    //课程id
            String url_choosedclass = "http://172.18.156.214:8000/scheduleinterface_adddata/" + mid + "/" + classid + "/" + "0/";
            AsynNetUtils.get(url_choosedclass, new AsynNetUtils.Callback() {
                @Override
                public void onResponse(String response) {
                    mAdapter1.mData.get(position).choose_num = mAdapter1.mData.get(position).choose_num -1;    //课程容量减一
                    mAdapter1.notifyItemChanged(position);    //更新可选课程列表
                    mAdapter2.mData.add(mAdapter1.mData.get(position));    //已选课程列表的item数量加一
                    mAdapter1.mData.remove(position);    //可选课程列表的item数量减一
                    mAdapter2.notifyDataSetChanged();    //更新已选课程列表
                    mAdapter1.notifyDataSetChanged();    //更新可选课程列表
                    Toast.makeText(studentclassactivity.this, "选课成功!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    //退课。参数为int型的item的位置
    private void dropClass(final int position){
        String mid = String.valueOf(id);
        String classid = String.valueOf(mAdapter2.mData.get(position).classid);
        String url_choosedclass = "http://172.18.156.214:8000/scheduleinterface_dropclass/" + mid + "/" + classid + "/";
        AsynNetUtils.get(url_choosedclass, new AsynNetUtils.Callback() {
            @Override
            public void onResponse(String response) {
                mAdapter2.mData.get(position).choose_num = mAdapter2.mData.get(position).choose_num +1;    //课程容量加一
                mAdapter2.notifyItemChanged(position);    //更新已选课程列表
                mAdapter1.mData.add(mAdapter2.mData.get(position));    //可选课程列表的item数量加一
                mAdapter2.mData.remove(position);    //已选课程列表的item数量减一
                mAdapter1.notifyDataSetChanged();    //更新可选课程列表
                mAdapter2.notifyDataSetChanged();    //更新已选课程列表
                Toast.makeText(studentclassactivity.this, "退课成功!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
