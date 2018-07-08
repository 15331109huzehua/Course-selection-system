package com.example.administrator.finalterm;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.administrator.finalterm.datainterface.memberinterface;

public class registeractivity extends AppCompatActivity {
    private RadioGroup mRadioGroup = null;          //默认选择项为空
    private  RadioButton mRadioButton1 = null;      //学生选项
    private RadioButton mRadioButton2 = null;       //教师选项
    private RadioButton mRadioButton3 = null;       //管理员选项
    private int flag = 0;                            //用于标识用户身份
    private ConstraintLayout mRootLayout = null;    //初始化根界面
    private memberinterface memberdatabase;         //数据库
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);    //加载注册界面
        initRadioButtons();    //初始化身份选择项与根界面
        //获取账号编辑框、密码编辑框和姓名编辑框
        final EditText mNumberText = (EditText) findViewById(R.id.text_userid), mPasswordText = (EditText) findViewById(R.id.text_userpwd), mName = (EditText) findViewById(R.id.text_name);
        Button mbutton=(Button)findViewById(R.id.ui_button);    //获取完成注册按钮
        setListener(mbutton, mNumberText, mPasswordText, mName);    //设置完成注册按钮的监听器
    }

    //初始化身份选择项与根界面
    private void initRadioButtons(){
        memberdatabase=new memberinterface(getBaseContext());
        mRadioGroup = (RadioGroup) super.findViewById(R.id.radioButton);
        mRadioButton1 = (RadioButton) super.findViewById(R.id.radioButton1);
        mRadioButton2 = (RadioButton) super.findViewById(R.id.radioButton2);
        mRadioButton3 = (RadioButton) super.findViewById(R.id.radioButton3);
        mRadioGroup.setOnCheckedChangeListener(new registeractivity.mOnCheckedChangeListener());
        mRootLayout = (ConstraintLayout) super.findViewById(R.id.ui_layout);
    }

    //设置登录按钮与注册按钮的监听器，参数分别为：完成注册按钮，账号编辑框，密码编辑框，姓名编辑框
    private void setListener(Button mbutton, final EditText mNumberText, final EditText mPasswordText, final EditText mName){
        mbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //先检查三个编辑框是否为空
                checkText(mNumberText, mPasswordText, mName);
            }
        });
    }

    //判断三个编辑框是否为空。若至少有一个为空则报错；若都不为空，判断身份是否已经选择。参数分别为：账号编辑框，密码编辑框，姓名编辑框
    private void checkText(final EditText mNumberText, final EditText mPasswordText, final EditText mName){
        String number = mNumberText.getText().toString();    //学号
        String password = mPasswordText.getText().toString();    //密码
        String name = mName.getText().toString();    //姓名
        //账号未填写
        if(TextUtils.isEmpty(number)) mNumberText.setError("学号不能为空");
        //密码未填写
        else if(TextUtils.isEmpty(password)) mPasswordText.setError("密码不能为空");
        //姓名未填写
        else if(TextUtils.isEmpty(name)) mName.setError("姓名不能为空");
        else {
            //把String型的账号转成int型赋给account
            int account = Integer.parseInt(number);
            //判断身份是否已选择
            checkIendtity(account, name, password, flag);
        }
    }

    //判断身份是否已选择。若没有选择身份则报错；若已经选择则根据选择的身份调用注册函数。参数分别为：int型的账号，String型的姓名，String的密码，int型的身份标识
    private void checkIendtity(final int account, String name, String password , int flag){
        //注册学生账号
        if (flag == 1) memberdatabase.register(account, name, password, "student");
        //注册教师账号
        else if (flag == 2) memberdatabase.register(account, name, password, "teacher");
        //注册管理员账号
        else if (flag == 3) Toast.makeText(registeractivity.this, "管理员注册功能尚未启用", Toast.LENGTH_SHORT).show();
        //未选择身份，报错
        else Toast.makeText(registeractivity.this, "您未选择身份！", Toast.LENGTH_SHORT).show();

    }

    //内部类，根据用户选择的身份修改flag的值
    private class mOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (mRadioButton1.getId() == checkedId) {
                flag = 1;
                call_Snackbar_10();
            }
            else if (mRadioButton2.getId() == checkedId) {
                flag = 2;
                call_Snackbar_11();
            }
            else {
                flag =3;
                call_Snackbar_12();
            }
        }
    }

    //选择身份时用snackbar来产生提示信息
    private void call_Snackbar_10() {
        Snackbar.make(mRootLayout, "您选择了学生", Snackbar.LENGTH_SHORT)
                .setAction("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       // Toast.makeText(MainActivity.this, "Snackbar的确定按钮被点击了", Toast.LENGTH_SHORT).show();
                    }
                })
                .setActionTextColor(getResources().getColor(R.color.colorPrimary))
                .setDuration(5000)
                .show();
    }

    //选择身份时用snackbar来产生提示信息
    private void call_Snackbar_11() {
        Snackbar.make(mRootLayout, "您选择了教师", Snackbar.LENGTH_SHORT)
                .setAction("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                      //  Toast.makeText(MainActivity.this, "Snackbar的确定按钮被点击了", Toast.LENGTH_SHORT).show();
                    }
                })
                .setActionTextColor(getResources().getColor(R.color.colorPrimary))
                .setDuration(5000)
                .show();
    }

    //选择身份时用snackbar来产生提示信息
    private void call_Snackbar_12() {
        Snackbar.make(mRootLayout, "您选择了管理员", Snackbar.LENGTH_SHORT)
                .setAction("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                      //  Toast.makeText(MainActivity.this, "Snackbar的确定按钮被点击了", Toast.LENGTH_SHORT).show();
                    }
                })
                .setActionTextColor(getResources().getColor(R.color.colorPrimary))
                .setDuration(5000)
                .show();
    }
}
