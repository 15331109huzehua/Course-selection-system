package com.example.administrator.finalterm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class studentactivity extends AppCompatActivity{
    private Button checkclass=null;
    private Button checkgrade=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_view);    //加载学生界面
        Bundle bundle=this.getIntent().getExtras();
        final int id=bundle.getInt("id");    //获取上一个页面传递的参数，即账号
        checkclass=(Button)findViewById(R.id.student_view_checkclass);    //获取查选课程按钮
        checkgrade=(Button)findViewById(R.id.student_view_checkgrade);    //获取查看成绩按钮
        setListeners(id);    //设置查选课程按钮与查看成绩按钮的监听器
    }

    //设置查选课程按钮与查看成绩按钮的监听器，参数为：int型的账号
    private void setListeners(final int id){
        checkgrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转至查选课程页面
                jumpTo(id, 1);
            }
        });
        checkclass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转至查看成绩页面
                jumpTo(id, 2);
            }
        });
    }

    //根据whichactivity跳转至相应的页面。参数分别为：int型的账号，int型的跳转标识
    private void jumpTo(final int id, int whichactivity){
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        //给下一个页面传参，即账号
        bundle.putInt("id", id);
        //跳转至查选课程页面
        if (whichactivity == 1) intent.setClass(studentactivity.this, studentgradeactivity.class);
        //跳转至查看成绩页面
        else if (whichactivity == 2)intent.setClass(studentactivity.this, studentclassactivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
