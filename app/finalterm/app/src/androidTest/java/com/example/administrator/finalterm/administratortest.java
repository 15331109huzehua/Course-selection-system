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

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2018/7/6.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class administratortest {
    @Rule
    public ActivityTestRule<administratoractivity> mActivityRule = new ActivityTestRule<>(
            administratoractivity.class);

    int titleId = mActivityRule.getActivity().getResources()
            .getIdentifier( "修改个人信息", "id", "android" );


    //测试当只输入姓名时，弹出错误提示
    @Test
    public void test1(){
        /*onView(withId(titleId))
                .inRoot(isDialog())
                .check(matches(withText(R.string.my_title)))
                .check(matches(isDisplayed()));*/

        onView(withId(R.id.admin_id_change_name)).inRoot(isDialog()).perform(typeText("666"), closeSoftKeyboard()); //line 1
        onView(withId(R.id.button1)).perform(click()); //line 2
    }

    //测试当只输入密码时，弹出错误提示
    @Test
    public void test2(){
        onView(withId(R.id.admin_id_change_password)).perform(typeText("666"), closeSoftKeyboard()); //line 1
        onView(withId(R.id.button1)).perform(click()); //line 2
    }

    //测试当输入姓名和密码时，成功修改
    @Test
    public void test3(){
        onView(withId(R.id.admin_id_change_name)).perform(typeText("666"), closeSoftKeyboard()); //line 1
        onView(withId(R.id.admin_id_change_password)).perform(typeText("666"), closeSoftKeyboard()); //line 1
        onView(withId(R.id.button2)).perform(click()); //line 2
    }
}
