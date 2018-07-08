package com.example.administrator.finalterm;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
@LargeTest

public class addclasstest {
    @Rule
    public ActivityTestRule<addclassactivity> mActivityRule = new ActivityTestRule<>(addclassactivity.class);


    @Test
    //测试不输入任何东西时，弹出错误提示
    public void test1(){
        onView(withId(R.id.class_create)).perform(click());
    }

    @Test
    //测试只输入课程名字时，弹出错误提示
    public void test2(){
        onView(withId(R.id.class_name)).perform(typeText("123"),closeSoftKeyboard());
        onView(withId(R.id.class_create)).perform(click());
    }

    @Test
    //测试只输入课程名字和课程时间时，弹出错误提示
    public void test3(){
        onView(withId(R.id.class_name)).perform(typeText("123"),closeSoftKeyboard());
        onView(withId(R.id.class_time)).perform(typeText("12:00"),closeSoftKeyboard());
        onView(withId(R.id.class_create)).perform(click());
    }

    @Test
    //测试只输入课程名字、课程时间和课程容量时，成功创建课程
    public void test4(){
        onView(withId(R.id.class_name)).perform(typeText("123"),closeSoftKeyboard());
        onView(withId(R.id.class_time)).perform(typeText("12:00"),closeSoftKeyboard());
        onView(withId(R.id.class_num)).perform(typeText("123"),closeSoftKeyboard());
        onView(withId(R.id.class_create)).perform(click());
    }
}
