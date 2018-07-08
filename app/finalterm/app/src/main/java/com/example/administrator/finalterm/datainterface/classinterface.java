package com.example.administrator.finalterm.datainterface;

import android.content.Context;

import com.example.administrator.finalterm.adapters.student_class_data;
import com.example.administrator.finalterm.adapters.teacher_class_data;
import com.example.administrator.finalterm.data.classdata;
import com.example.administrator.finalterm.adapters.admin_member_data;

import java.util.ArrayList;

public class classinterface {
    static classdata claSS;
    public classinterface(Context context){
        claSS=new classdata(context);
    }
    public void adddata(int id,String classname,int number){
        claSS.adddata(id,classname,number);
    }
}
