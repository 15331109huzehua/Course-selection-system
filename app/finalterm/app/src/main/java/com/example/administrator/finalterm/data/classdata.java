package com.example.administrator.finalterm.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.administrator.finalterm.AsynNetUtils;
import com.example.administrator.finalterm.adapters.student_class_data;
import com.example.administrator.finalterm.adapters.admin_member_data;
import com.example.administrator.finalterm.adapters.teacher_class_data;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class classdata {
    private Context myContent;
    public classdata(Context context){
        myContent=context;
    }

    public void adddata(int id,String classname,int number){
        String id_string = String.valueOf(id);
        String number_string = String.valueOf(number);
        String url_addmember = "http://172.18.156.214:8000/classinterface_adddata/" + id_string + "/" + classname + "/" + number_string + "/";
        AsynNetUtils.get(url_addmember, new AsynNetUtils.Callback() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(myContent, "创建课程成功！", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
