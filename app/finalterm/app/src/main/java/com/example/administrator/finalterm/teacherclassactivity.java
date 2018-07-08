package com.example.administrator.finalterm;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.finalterm.adapters.*;
import com.example.administrator.finalterm.datainterface.*;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class teacherclassactivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private teacher_classAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    int id=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_list_self_class);    //加载查看课程界面
        Bundle bundle=this.getIntent().getExtras();
        id=bundle.getInt("id");    //获取上一个页面传递的参数，即账号
        initData();    //从服务器接收数据
    }

    //从服务器接收数据
    private void initData() {
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //mAdapter = new teacher_classAdapter(classInterface.getData(id));
        String mid = String.valueOf(id);
        String url_getmyclass = "http://172.18.156.214:8000/classinterface_getData/" + mid + "/";
        AsynNetUtils.get(url_getmyclass, new AsynNetUtils.Callback() {
            @Override
            public void onResponse(String response) {
                //重写onResponse(String response)方法，response为服务器的响应字符串，即JSON数据
                //解析response中的JSON数据获取已创建课程的ArrayList并设置adapter
                ArrayList<teacher_class_data> mlist = analysisArray(response, teacher_class_data.class);
                mAdapter = new teacher_classAdapter(mlist);
                initView();    //加载列表
            }
        });
    }

    //解析字符串中的JSON数据，并返回JSON数组
    public static ArrayList<teacher_class_data> analysisArray(String json, Type type) {
        ArrayList<teacher_class_data> mlist = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                teacher_class_data o = new Gson().fromJson(String.valueOf(jsonArray.get(i)), type);
                mlist.add(o);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mlist;
    }

    //加载列表
    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.teacher_class_recycleview);
        mRecyclerView.setLayoutManager(mLayoutManager);    // 设置布局管理器
        mRecyclerView.setHasFixedSize(true);
        // 设置adapter
        mAdapter.setOnItemClickListener(new teacher_classAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                setClick(position);
            }
        });
        //item的长按事件
        mAdapter.setOnLongItemClickListener(new teacher_classAdapter.OnRecyclerViewLongItemClickListener() {
            @Override
            public void onLongItemClick(View view, final int position) {
                setDialog(position);
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    private void setClick(int position){
        int rid=mAdapter.mData.get(position).getClassid();
        Intent intent=new Intent();
        Bundle bundle=new Bundle();
        //传递课程ID
        bundle.putInt("id",rid);
        intent.setClass(teacherclassactivity.this,resultactivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void setDialog(final int position){
        final AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(teacherclassactivity.this);
        alertDialogBuilder.setTitle("删除这个课程？")
                .setIcon(android.R.drawable.ic_dialog_info)
                .setPositiveButton("确定",  new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setLongClick(position);
                    }
                }).setNegativeButton("取消", null).show();
    }

    private void setLongClick(final int position){
        //删除课程时要同时删除schedule的选课结果。
        int rid=mAdapter.mData.get(position).getClassid();
        String url_deletemyclass = "http://172.18.156.214:8000/classinterface_delete/" + rid + "/";
        AsynNetUtils.get(url_deletemyclass, new AsynNetUtils.Callback() {
            @Override
            public void onResponse(String response) {
                mAdapter.mData.remove(position);
                mAdapter.notifyItemRemoved(position);
                Toast.makeText(getApplicationContext(), ""+ "您删除了该课程",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
