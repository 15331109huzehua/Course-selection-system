package com.example.administrator.finalterm.datainterface;

/**
 * Created by Administrator on 2018/6/4.
 */
//这个文件是对数据库的调用。
import android.content.Context;
import android.widget.Toast;

import com.example.administrator.finalterm.data.*;
public class memberinterface {
    //这里用stataic使变量和属于哪个对象无关相当于单例模式。（我也不太确定了，很久没用）。
    static memberdata member;
    public memberinterface(Context context){
        member=new memberdata(context);
    }

    //添加成员。但是添加之前需要通过isidexist函数判断id是否已经存在了。
    public void register(int id,String name,String password,String identity){
        member.register(id, name, password, identity);
    }
}
