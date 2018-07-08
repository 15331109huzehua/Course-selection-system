package com.example.administrator.finalterm.datainterface;

import android.content.Context;
import com.example.administrator.finalterm.adapters.student_grade_data;
import com.example.administrator.finalterm.data.scheduledata;
import com.example.administrator.finalterm.adapters.result_class_data;
import java.util.ArrayList;

public class scheduleinterface {
    static scheduledata schedule;
    public scheduleinterface(Context context){
        schedule=new scheduledata(context);
    }
    public void changeresult(int id,int classid,int result){
        schedule.changeresult(id,classid,result);
    }
}
