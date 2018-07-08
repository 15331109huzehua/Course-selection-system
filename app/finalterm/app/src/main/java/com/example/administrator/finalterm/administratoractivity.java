package com.example.administrator.finalterm;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.administrator.finalterm.adapters.admin_memberAdapter;
import com.example.administrator.finalterm.adapters.admin_member_data;
import com.example.administrator.finalterm.datainterface.classinterface;
import com.example.administrator.finalterm.datainterface.memberinterface;
import com.example.administrator.finalterm.datainterface.scheduleinterface;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class administratoractivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private admin_memberAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private classinterface classInterface;
    private memberinterface memberInterface;
    private scheduleinterface scheduleInterface;
    int id=0;
    String iden="";
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_id_manage);//进入管理员界面
        classInterface=new classinterface(getBaseContext());
        memberInterface=new memberinterface(getBaseContext());
        scheduleInterface=new scheduleinterface(getBaseContext());
        Bundle bundle=this.getIntent().getExtras();
        id=bundle.getInt("id");
        initData();
    }
    private void initData() {
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //mAdapter = new admin_memberAdapter(classInterface.admindata());//数据库加获取函数
        String url_getmembers = "http://172.18.156.214:8000/classinterface_admindata/";
        AsynNetUtils.get(url_getmembers, new AsynNetUtils.Callback() {
            @Override
            public void onResponse(String response) {
                ArrayList<admin_member_data> mlist = analysisArray(response, admin_member_data.class);
                mAdapter = new admin_memberAdapter(mlist);
                initView();
            }
        });
    }

    public static ArrayList<admin_member_data> analysisArray(String json, Type type) {
        ArrayList<admin_member_data> mlist = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                admin_member_data o = new Gson().fromJson(String.valueOf(jsonArray.get(i)), type);
                mlist.add(o);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mlist;
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.admin_member_recyclerview);
        mRecyclerView.setLayoutManager(mLayoutManager);    // 设置布局管理器
        mRecyclerView.setHasFixedSize(true);
        setClick();
        setLongClick();
        mRecyclerView.setAdapter(mAdapter);
    }

    private void setClick(){
        mAdapter.setOnItemClickListener(new admin_memberAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {
                final View change=(LinearLayout) getLayoutInflater().inflate(R.layout.admin_id_change,null);
                final EditText name=(EditText)change.findViewById(R.id.admin_id_change_name);
                final EditText password=(EditText)change.findViewById(R.id.admin_id_change_password);
                final AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(administratoractivity.this);
                alertDialogBuilder.setTitle("修改个人信息")
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setView(change)
                        .setPositiveButton("确定",  new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                setClick1(name, password, position);
                            }
                        }).setNegativeButton("取消", null).show();
            }
        });
    }

    private void setClick1(final EditText name, final EditText password, final int position){
        String na=String.valueOf(name.getText());
        String pass=String.valueOf(password.getText());
        if(na.equals("")) Toast.makeText(administratoractivity.this, "姓名不能为空！", Toast.LENGTH_SHORT).show();
        else if(pass.equals("")) Toast.makeText(administratoractivity.this, "密码不能为空！", Toast.LENGTH_SHORT).show();
        else {
            String mid = String.valueOf(mAdapter.mData.get(position).id);
            String url_change = "http://172.18.156.214:8000/memberinterface_change/" + mid + "/" + na +  "/" + pass +  "/";
            AsynNetUtils.get(url_change, new AsynNetUtils.Callback() {
                @Override
                public void onResponse(String response) {
                    mAdapter.mData.get(position).name=String.valueOf(name.getText());
                    mAdapter.notifyItemChanged(position);
                    Toast.makeText(administratoractivity.this, "修改成功！", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void setLongClick(){
        mAdapter.setOnLongItemClickListener(new admin_memberAdapter.OnRecyclerViewLongItemClickListener() {
            @Override
            public void onLongItemClick(View view,final int position) {
                final AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(administratoractivity.this);
                alertDialogBuilder.setTitle("删除这个账号？")
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setPositiveButton("确定",  new DialogInterface.OnClickListener() {// 积极
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                setLongClick1(position);
                            }
                        }).setNegativeButton("取消", null).show();
            }
        });
    }

    private void setLongClick1(final int position){
        String id_string = mAdapter.mData.get(position).id;
        if(mAdapter.mData.get(position).identity.equals("administrator")){
            Toast.makeText(administratoractivity.this, "不可以删除管理员", Toast.LENGTH_SHORT).show();
        }else if(mAdapter.mData.get(position).identity.equals("teacher")){
            deleteteacher(id_string, position);
        }else if(mAdapter.mData.get(position).identity.equals("student")){
            String url_deletestudent = "http://172.18.156.214:8000/memberinterface_deletestudent/" + id_string + "/";
            AsynNetUtils.get(url_deletestudent, new AsynNetUtils.Callback() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(administratoractivity.this, "删除成功！", Toast.LENGTH_SHORT).show();
                    mAdapter.mData.remove(position);
                    mAdapter.notifyItemRemoved(position);
                }
            });
        }
    }

    private void deleteteacher(String id_string, final int position){
        String url_deleteteacher = "http://172.18.156.214:8000/memberinterface_deleteteacher/" + id_string + "/";
        AsynNetUtils.get(url_deleteteacher, new AsynNetUtils.Callback() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(administratoractivity.this, "删除成功！", Toast.LENGTH_SHORT).show();
                mAdapter.mData.remove(position);
                mAdapter.notifyItemRemoved(position);
            }
        });
    }
}
