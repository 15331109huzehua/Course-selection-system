package com.example.administrator.finalterm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.administrator.finalterm.adapters.admin_memberAdapter;
import com.example.administrator.finalterm.adapters.admin_member_data;
import com.example.administrator.finalterm.adapters.student_check_gradeAdapter;
import com.example.administrator.finalterm.adapters.student_grade_data;
import com.example.administrator.finalterm.datainterface.scheduleinterface;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class studentgradeactivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    int id=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_list_grade);    //加载查看成绩界面
        Bundle bundle=this.getIntent().getExtras();
        id=bundle.getInt("id");    //获取上一个页面传递的参数，即账号
        initData();    //从服务器接收数据
    }

    //从服务器接收成绩数据
    private void initData() {
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        String mid = String.valueOf(id);
        String url_getresult = "http://172.18.156.214:8000/scheduleinterface_getresultdata/" + mid + "/";
        AsynNetUtils.get(url_getresult, new AsynNetUtils.Callback() {
            @Override
            public void onResponse(String response) {
                //重写onResponse(String response)方法，response为服务器的响应字符串，即JSON数据
                //解析response中的JSON数据并设置adapter
                ArrayList<student_grade_data> mlist = analysisArray(response, student_grade_data.class);
                mAdapter = new student_check_gradeAdapter(mlist);
                initView();    //加载列表
            }
        });
    }

    //解析字符串中的JSON数据，并返回JSON数组
    public static ArrayList<student_grade_data> analysisArray(String json, Type type) {
        ArrayList<student_grade_data> mlist = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                student_grade_data o = new Gson().fromJson(String.valueOf(jsonArray.get(i)), type);
                mlist.add(o);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mlist;
    }

    //加载列表
    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.student_grade_recycleview);
        // 设置布局管理器
        mRecyclerView.setLayoutManager(mLayoutManager);
        // 设置adapter
        mRecyclerView.setAdapter(mAdapter);
    }
}
