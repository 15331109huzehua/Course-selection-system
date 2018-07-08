package com.example.administrator.finalterm.adapters;

public class teacher_class_data {
    public String classname;
    public String teachername;
    public int classnumber;
    public int classid;
    public teacher_class_data( String classname,String teachername,int classnumber,int classid){
        this.classname=classname;
        this.teachername=teachername;
        this.classnumber=classnumber;
        this.classid=classid;
    }

    public String getClassname() {
        return classname;
    }

    public int getClassnumber() {
        return classnumber;
    }

    public String getTeachername() {
        return teachername;
    }

    public int getClassid() {
        return classid;
    }
}

