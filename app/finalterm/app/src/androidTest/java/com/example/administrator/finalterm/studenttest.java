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
import static android.support.test.espresso.matcher.RootMatchers.isDialog;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
public class studenttest {
    @Rule
    public ActivityTestRule<studentactivity> mActivityRule = new ActivityTestRule<>(
            studentactivity.class
    );

    //测试能否跳转到选课页面
    @Test
    public void test1(){
        onView(withId(R.id.student_view_checkclass)).perform(click());
    }

    //测试能否跳转到查成绩页面
    @Test
    public void test2(){
        onView(withId(R.id.student_view_checkgrade)).perform(click());
    }
}
