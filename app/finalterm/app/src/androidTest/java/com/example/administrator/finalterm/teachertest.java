package com.example.administrator.finalterm;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
@LargeTest

public class teachertest {

    @Rule
    public ActivityTestRule<teacheractivity> mActivityRule = new ActivityTestRule<>(teacheractivity.class);

    @Test
    //测试点击创建课程按钮能否跳转到查选课程页面，结果应该为：成功跳转到查选课程界面。
    public void gotoCreatClass(){
        onView(withId(R.id.teacher_view_creatclass)).perform(click());
    }

    @Test
    //测试点击查看课程按钮能否跳转到成绩页面，结果应该为：成功跳转到查看成绩页面。
    public  void gotoCheckClass(){
        onView(withId(R.id.teacher_view_checkclass)).perform(click());
    }

}
