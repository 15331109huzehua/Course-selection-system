package com.example.administrator.finalterm.adapters;

/**
 * Created by Administrator on 2018/6/26.
 */

public class result_class_data {
    public String studentname;
    public int studentnum;
    public int studentgrade;
    public result_class_data( String studentname,int studentnum,int studentgrade){
        this.studentname=studentname;
        this.studentnum=studentnum;
        this.studentgrade=studentgrade;
    }

    public int getStudentgrade() {
        return studentgrade;
    }

    public int getStudentnum() {
        return studentnum;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentgrade(int studentgrade) {
        this.studentgrade = studentgrade;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public void setStudentnum(int studentnum) {
        this.studentnum = studentnum;
    }
}
