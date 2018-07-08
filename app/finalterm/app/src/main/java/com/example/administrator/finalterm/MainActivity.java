package com.example.administrator.finalterm;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.administrator.finalterm.datainterface.memberinterface;

public class MainActivity extends AppCompatActivity {
    private RadioGroup mRadioGroup = null;          //默认选择项为空
    private  RadioButton mRadioButton1 = null;      //学生身份选项
    private RadioButton mRadioButton2 = null;       //教师身份选项
    private RadioButton mRadioButton3 = null;       //管理员身份选项
    private int flag = 0;                            //用于标识用户身份
    private ConstraintLayout mRootLayout = null;    //初始化根界面

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);    //加载登录界面
        ImageView mImage = (ImageView) findViewById(R.id.icon);    //初始化头像图标
        initRadioButtons();    //初始化身份选择项与根界面
        Button mButton1 = (Button) findViewById(R.id.button1), mButton2 = (Button) findViewById(R.id.button2);    //获取登录按钮和注册按钮，mButton1为登录按钮，mButton2为注册按钮
        final EditText mNumberText = (EditText) findViewById(R.id.text_userid), mPasswordText = (EditText) findViewById(R.id.text_userpwd);    //获取账号编辑框和密码编辑框
        setListeners(mButton1, mButton2, mNumberText, mPasswordText);    //设置登录按钮与注册按钮的监听器
    }

    //初始化身份选择项与根界面
    private void initRadioButtons(){
        mRadioGroup = (RadioGroup) super.findViewById(R.id.radioButton);
        mRadioButton1 = (RadioButton) super.findViewById(R.id.radioButton1);
        mRadioButton2 = (RadioButton) super.findViewById(R.id.radioButton2);
        mRadioButton3 = (RadioButton) super.findViewById(R.id.radioButton3);
        mRadioGroup.setOnCheckedChangeListener(new mOnCheckedChangeListener());
        mRootLayout = (ConstraintLayout) super.findViewById(R.id.mlayout);
    }

    //设置登录按钮与注册按钮的监听器，参数分别为：登录按钮，注册按钮，账号编辑框，密码编辑框
    private void setListeners(Button mButton1, Button mButton2, final EditText mNumberText, final EditText mPasswordText){
        mButton1.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View view) {
                //先检查两个编辑框是否为空
                checkText(mNumberText, mPasswordText);
            }
        });
        mButton2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                //跳转到注册页面
                intent.setClass(MainActivity.this,registeractivity.class);
                startActivity(intent);
            }
        });
    }

    //判断两个编辑框是否为空。若至少有一个为空则报错；若都不为空，判断身份是否已经选择。参数分别为：账号编辑框，密码编辑框
    private void checkText(final EditText mNumberText, final EditText mPasswordText){
        String number = mNumberText.getText().toString();
        String password = mPasswordText.getText().toString();
        //账号未填写
        if (TextUtils.isEmpty(number)) mNumberText.setError("账号不能为空");
        //密码未填写
        else if (TextUtils.isEmpty(password)) mPasswordText.setError("密码不能为空");
        else {
            //把String型的账号转成int型赋给account
            final int account = Integer.parseInt(number);
            //判断身份是否已选择
            checkIendtity(account, number, password, flag);
        }
    }

    //判断身份是否已选择。若没有选择身份则报错；若已经选择则根据选择的身份向服务器发送登录请求。参数分别为：int型的账号，String型的账号，String的密码，int型的身份标识
    private void checkIendtity(final int account, String number, String password , int flag){
        String url_login;
        //如果选择了学生
        if (flag == 1){
            url_login = "http://172.18.156.214:8000/memberinterface_login/" + number + "/" + password + "/student/";
            sendLogin(url_login, account, 1);    //向服务器发送请求
        }
        //如果选择了教师
        else if (flag == 2){
            url_login = "http://172.18.156.214:8000/memberinterface_login/" + number + "/" + password + "/teacher/";
            sendLogin(url_login, account, 2);    //向服务器发送请求
        }
        //如果选择了管理员
        else if (flag == 3){
            url_login = "http://172.18.156.214:8000/memberinterface_login/" + number + "/" + password + "/administrator/";
            sendLogin(url_login, account, 3);    //向服务器发送请求
        }
        //如果没选择身份，则报错
        else Toast.makeText(MainActivity.this, "您未选择身份！", Toast.LENGTH_SHORT).show();
    }

    //向服务器发送请求。参数分别为：String型的URL，int型的账号，int型的身份标识
    private void sendLogin(String url_login,  final int account, int flag){
        final int flag0 = flag;
        AsynNetUtils.get(url_login, new AsynNetUtils.Callback() {
            @Override
            public void onResponse(String response) {
                //重写onResponse(String response)方法，response为服务器的响应字符串
                //根据服务器的回应判断是否登录成功，如果成功则根据flag跳转至相应的页面
                if (IfGotoSucceed(response)) jumpTo(account, flag0);
            }
        });
    }

    //根据服务器的回应判断是否登录成功，成功与失败都会有提示。参数为：String型的响应字符串
    private boolean IfGotoSucceed(String response){
        //服务器未打开，连接失败
        if (response.equals("NoServer")) Toast.makeText(MainActivity.this, "很抱歉！服务器未开启！", Toast.LENGTH_SHORT).show();
            //账号未注册
        else if (response.equals("noid")) Toast.makeText(MainActivity.this, "此账号未注册！", Toast.LENGTH_SHORT).show();
            //账号已注册，但密码错误
        else if (response.equals("nopassword")) Toast.makeText(MainActivity.this, "密码错误！", Toast.LENGTH_SHORT).show();
            //账号已注册，密码正确，但选择的身份不对
        else if (response.equals("noidentity")) Toast.makeText(MainActivity.this, "您选择的身份错误！", Toast.LENGTH_SHORT).show();
            //向服务器发送请求
        else if (response.equals("nostatus")) Toast.makeText(MainActivity.this, "此账号正在登录！", Toast.LENGTH_SHORT).show();
            //服务器回复登录成功
        else if (response.equals("true")){
            Toast.makeText(MainActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    //根据flag跳转至相应的页面。参数分别为：int型的账号，int型的身份标识
    private void jumpTo(final int account, int flag){
        Intent intent=new Intent();
        Bundle bundle=new Bundle();
        //给下一个页面传参，即账号
        bundle.putInt("id", account);
        //跳转至学生界面
        if (flag == 1) intent.setClass(MainActivity.this,studentactivity.class);
        //跳转至教师界面
        else if (flag == 2) intent.setClass(MainActivity.this,teacheractivity.class);
        //跳转至管理员界面
        else if (flag == 3) intent.setClass(MainActivity.this,administratoractivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
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
                        //Toast.makeText(MainActivity.this, "Snackbar的确定按钮被点击了", Toast.LENGTH_SHORT).show();
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
                        //Toast.makeText(MainActivity.this, "Snackbar的确定按钮被点击了", Toast.LENGTH_SHORT).show();
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
                        //Toast.makeText(MainActivity.this, "Snackbar的确定按钮被点击了", Toast.LENGTH_SHORT).show();
                    }
                })
                .setActionTextColor(getResources().getColor(R.color.colorPrimary))
                .setDuration(5000)
                .show();
    }
}
