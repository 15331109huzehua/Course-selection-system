package com.example.administrator.finalterm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.finalterm.datainterface.*;

/**
 * Created by Administrator on 2018-6-23.
 */

public class addclassactivity extends AppCompatActivity {
    private classinterface classInterface;
    private EditText cname = null;
    private EditText ctime = null;
    private EditText cnum = null;
    private Button creatclass = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_class_view);    //进入创建课程界面
        classInterface=new classinterface(getBaseContext());
        Bundle bundle=this.getIntent().getExtras();
        final int id=bundle.getInt("id");    //获取上一个页面传递的参数，即账号
        initData();    //初始化参数
        creatclass.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                addClass(id);    //创建课程
            }
        });
    }

    //初始化参数
    private void initData(){
        cname = (EditText) findViewById(R.id.class_name);
        ctime = (EditText) findViewById(R.id.class_time);
        cnum = (EditText) findViewById(R.id.class_num);
        creatclass=(Button)findViewById(R.id.class_create);
    }

    //创建课程。参数为：int型的账号
    private void addClass(final int id){
        String name = cname.getText().toString();    //课程名字
        String time = ctime.getText().toString();    //课程时间
        String num = cnum.getText().toString();    //课程容量
        //课程名字未填写
        if(TextUtils.isEmpty(name)) cname.setError("课程名字不能为空!");
        //课程时间未填写
        else if(TextUtils.isEmpty(time)) ctime.setError("课程时间不能为空!");
        //课程容量未填写
        else if(TextUtils.isEmpty(num)) cnum.setError("课程容量不能为空!");
        else {
            int numm = Integer.parseInt(num);
            //class数据库增加一个课程
            classInterface.adddata(id, name, numm);
        }
    }
}
