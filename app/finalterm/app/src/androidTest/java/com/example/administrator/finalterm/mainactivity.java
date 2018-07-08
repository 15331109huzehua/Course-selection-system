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
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Administrator on 2018-7-6.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest

public class mainactivity {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    /*
    测试前，服务器数据库中已注册有以下账号：
    账号：666，密码：666，身份：管理员
    账号：1，  密码：1，  身份：学生
    账号：2，  密码：2，  身份：教师
     */

    @Test
    //测试点击注册按钮能否跳转到注册界面。结果应该为：成功跳转到注册界面。
    public void gotoRegister() {
        onView(withId(R.id.button2)).perform(click());
    }

    @Test
    //测试不填账号能否登录成功。结果应该为：登录失败并提示账号不能为空。
    public void noAccount() {
        onView(withId(R.id.button1)).perform(click());
    }

    @Test
    //测试不填密码能否登录成功。结果应该为：登录失败并提示密码不能为空。
    public void noPassword() {
        onView(withId(R.id.text_userid)).perform(typeText("666"), closeSoftKeyboard());
        onView(withId(R.id.button1)).perform(click());
    }

    @Test
    //测试不选身份能否登录成功。结果应该为：登录失败并提示您未选择身份。
    public void noIdentity() {
        onView(withId(R.id.text_userid)).perform(typeText("666"), closeSoftKeyboard());
        onView(withId(R.id.text_userpwd)).perform(typeText("666"), closeSoftKeyboard());
        onView(withId(R.id.button1)).perform(click());
    }

    @Test
    //测试使用未注册账号能否登录成功。使用账号为6，密码为6的学生账号测试，结果应该为：登录失败并提示此账号未注册。
    public void noRegister() {
        onView(withId(R.id.text_userid)).perform(typeText("6"), closeSoftKeyboard());
        onView(withId(R.id.text_userpwd)).perform(typeText("6"), closeSoftKeyboard());
        onView(withId(R.id.radioButton1)).perform(click());
        onView(withId(R.id.button1)).perform(click());
    }

    @Test
    //测试使用已注册账号，错误密码登录是否成功。使用账号为666，密码为6的管理员账号测试，结果应该为：登录失败并提示密码错误。
    public void wrongPassword() {
        onView(withId(R.id.text_userid)).perform(typeText("666"), closeSoftKeyboard());
        onView(withId(R.id.text_userpwd)).perform(typeText("6"), closeSoftKeyboard());
        onView(withId(R.id.radioButton3)).perform(click());
        onView(withId(R.id.button1)).perform(click());
    }

    @Test
    //测试使用已注册账号，正确密码，错误身份登录是否成功。使用账号为666，密码为666的学生账号测试，结果应该为：登录失败并提示身份错误。
    public void wrongIdentity() {
        onView(withId(R.id.text_userid)).perform(typeText("666"), closeSoftKeyboard());
        onView(withId(R.id.text_userpwd)).perform(typeText("666"), closeSoftKeyboard());
        onView(withId(R.id.radioButton1)).perform(click());
        onView(withId(R.id.button1)).perform(click());
    }

    @Test
    //测试使用已注册账号，正确密码，正确身份登录是否成功。使用账号为666，密码为666的管理员账号测试，结果应该为：登录成功并跳转到管理员界面。
    public void loginAdministrator() {
        onView(withId(R.id.text_userid)).perform(typeText("666"), closeSoftKeyboard());
        onView(withId(R.id.text_userpwd)).perform(typeText("666"), closeSoftKeyboard());
        onView(withId(R.id.radioButton3)).perform(click());
        onView(withId(R.id.button1)).perform(click());
    }

    @Test
    //测试使用已注册账号，正确密码，正确身份登录是否成功。使用账号为1，密码为1的学生账号测试，结果应该为：登录成功并跳转到学生界面。
    public void loginStudent() {
        onView(withId(R.id.text_userid)).perform(typeText("1"), closeSoftKeyboard());
        onView(withId(R.id.text_userpwd)).perform(typeText("1"), closeSoftKeyboard());
        onView(withId(R.id.radioButton1)).perform(click());
        onView(withId(R.id.button1)).perform(click());
    }

    @Test
    //测试使用已注册账号，正确密码，正确身份登录是否成功。使用账号为2，密码为2的教师账号测试，结果应该为：登录成功并跳转到教师界面。
    public void loginTeacher() {
        onView(withId(R.id.text_userid)).perform(typeText("2"), closeSoftKeyboard());
        onView(withId(R.id.text_userpwd)).perform(typeText("2"), closeSoftKeyboard());
        onView(withId(R.id.radioButton2)).perform(click());
        onView(withId(R.id.button1)).perform(click());
    }
}
