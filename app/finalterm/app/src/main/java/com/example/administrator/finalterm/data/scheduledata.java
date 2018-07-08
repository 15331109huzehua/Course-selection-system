package com.example.administrator.finalterm.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.administrator.finalterm.AsynNetUtils;
import com.example.administrator.finalterm.adapters.result_class_data;
import com.example.administrator.finalterm.adapters.student_grade_data;
import com.example.administrator.finalterm.adapters.teacher_class_data;

import java.util.ArrayList;

public class scheduledata {
    private Context myContent;
    public scheduledata(Context context){
        myContent = context;
    }
    public void changeresult(int id,int classid,int result){
        String id_string = String.valueOf(id);
        String classid_string = String.valueOf(classid);
        String result_string = String.valueOf(result);
        String url_addmember = "http://172.18.156.214:8000/scheduleinterface_changeresult/" + id_string + "/" + classid_string + "/" + result_string + "/";
        AsynNetUtils.get(url_addmember, new AsynNetUtils.Callback() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(myContent, "修改成绩成功！", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
