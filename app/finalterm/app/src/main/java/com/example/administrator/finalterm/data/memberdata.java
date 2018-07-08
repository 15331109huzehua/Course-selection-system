package com.example.administrator.finalterm.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.example.administrator.finalterm.AsynNetUtils;
import com.example.administrator.finalterm.NetUtils;

import static java.lang.Thread.sleep;

/**
 * Created by Administrator on 2018/6/4.
 */
//这个文件用于创建一个虚拟数据库。
public class memberdata {
    private Handler handler = new Handler();
    private boolean success;
    private Context myContent;
    public memberdata(Context context){
        myContent=context;
    }

    public void register(int id,String name,String password,String identity){
        String id_string = String.valueOf(id);
        String url_addmember = "http://172.18.156.214:8000/memberinterface_register/" + id_string + "/" + name + "/" + password + "/" + "0/" + identity + "/";
        AsynNetUtils.get(url_addmember, new AsynNetUtils.Callback() {
            @Override
            public void onResponse(String response) {
                if (response.equals("NoServer")) Toast.makeText(myContent, "很抱歉！服务器未开启！", Toast.LENGTH_SHORT).show();
                //账号已被注册
                else if (response.equals("false")) Toast.makeText(myContent, "此账号已被注册！", Toast.LENGTH_SHORT).show();
                //注册成功
                else if (response.equals("true")) Toast.makeText(myContent, "账号注册成功！", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
