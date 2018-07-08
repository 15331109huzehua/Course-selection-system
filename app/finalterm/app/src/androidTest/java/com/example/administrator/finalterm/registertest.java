package com.example.administrator.finalterm;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
@RunWith(AndroidJUnit4.class)
@LargeTest
public class registertest {
    @Rule
    public ActivityTestRule<registeractivity> mActivityRule = new ActivityTestRule<>(
            registeractivity.class);

    //测试当只输入学号时，弹出错误提示
    @Test
    public void test1(){
        onView(withId(R.id.text_userid)).perform(typeText("12345"), closeSoftKeyboard()); //line 1
        onView(withId(R.id.ui_button)).perform(click()); //line 2
    }
    //测试当只输入学号和密码时，弹出错误提示
    @Test
    public void test2(){
        onView(withId(R.id.text_userid)).perform(typeText("12345"), closeSoftKeyboard()); //line 1
        onView(withId(R.id.text_userpwd)).perform(typeText("hehe"), closeSoftKeyboard()); //line 1
        onView(withId(R.id.ui_button)).perform(click()); //line 2
    }
    //测试当用正常的输入进行注册时，成功的注册了。
    @Test
    public void test3(){
        onView(withId(R.id.text_userid)).perform(typeText("12345"), closeSoftKeyboard()); //line 1
        onView(withId(R.id.text_userpwd)).perform(typeText("hehe"), closeSoftKeyboard()); //line 1
        onView(withId(R.id.text_name)).perform(typeText("hehe"), closeSoftKeyboard()); //line 1
        onView(withId(R.id.radioButton1)).perform(click(), closeSoftKeyboard()); //line 1
        onView(withId(R.id.ui_button)).perform(click()); //line 2
    }
    //但是在此用这个用例注册，会弹出该用户已被注册的错误提示。
    @Test
    public void test4(){
        onView(withId(R.id.text_userid)).perform(typeText("12345"), closeSoftKeyboard()); //line 1
        onView(withId(R.id.text_userpwd)).perform(typeText("hehe"), closeSoftKeyboard()); //line 1
        onView(withId(R.id.text_name)).perform(typeText("hehe"), closeSoftKeyboard()); //line 1
        onView(withId(R.id.radioButton1)).perform(click(), closeSoftKeyboard()); //line 1
        onView(withId(R.id.ui_button)).perform(click()); //line 2
    }
}
