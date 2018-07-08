package com.example.administrator.finalterm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.EditText;


import com.example.administrator.finalterm.adapters.*;
import com.example.administrator.finalterm.datainterface.*;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Administrator on 2018/6/25.
 */

public class resultactivity extends Activity {
    private RecyclerView mRecyclerView;
    private result_classAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private scheduleinterface scheduleInterface;
    int id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_list_all_class);    //进入修改成绩界面
        //返回result数据接口
        scheduleInterface=new scheduleinterface(getBaseContext());
        Bundle bundle=this.getIntent().getExtras();
        id=bundle.getInt("id");
        initData();
    }

    private void initData() {
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //mAdapter = new result_classAdapter(scheduleInterface.getData(id));
        String mid = String.valueOf(id);
        String url_getmyclass = "http://172.18.156.214:8000/scheduleinterface_getData/" + mid + "/";
        AsynNetUtils.get(url_getmyclass, new AsynNetUtils.Callback() {
            @Override
            public void onResponse(String response) {
                ArrayList<result_class_data> mlist = analysisArray(response, result_class_data.class);
                mAdapter = new result_classAdapter(mlist);
                initView();
            }
        });
    }

    public static ArrayList<result_class_data> analysisArray(String json, Type type) {
        ArrayList<result_class_data> mlist = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                result_class_data o = new Gson().fromJson(String.valueOf(jsonArray.get(i)), type);
                mlist.add(o);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mlist;
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.teacher_list_all_class_recycleview);
        mRecyclerView.setLayoutManager(mLayoutManager);    // 设置布局管理器
        mAdapter.setOnItemClickListener(new result_classAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {
                final EditText editText= new EditText(resultactivity.this);
                final AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(resultactivity.this);
                alertDialogBuilder.setTitle("请输入成绩：")
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setView(editText)
                        .setPositiveButton("确定",  new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                setClick(position, editText);
                            }
                        }).setNegativeButton("取消", null).show();
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    private void setClick(final int position, final EditText editText){
        int studentid=mAdapter.mData.get(position).getStudentnum();
        String result = editText.getText().toString();
        if(result.equals("")) {
            Toast.makeText(resultactivity.this, "成绩不能为空！", Toast.LENGTH_SHORT).show();
        }
        else {
            int res=Integer.parseInt(result);
            mAdapter.mData.get(position).setStudentgrade(res);
            mAdapter.notifyItemChanged(position);
            scheduleInterface.changeresult( studentid,id,res);
        }
    }
}
