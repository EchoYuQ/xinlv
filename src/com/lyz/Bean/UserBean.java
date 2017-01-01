package com.lyz.Bean;

/**
 * Created by yuqing on 2017/1/1.
 */
public class UserBean {
    private static String mUserName="";
    private static int mAge=-1;
    // 性别： true为男，false为女
    private static boolean mSex=true;

    public static String getmUserName() {
        return mUserName;
    }

    public static void setmUserName(String mUserName) {
        UserBean.mUserName = mUserName;
    }

    public static int getmAge() {
        return mAge;
    }

    public static void setmAge(int mAge) {
        UserBean.mAge = mAge;
    }

    public static boolean ismSex() {
        return mSex;
    }

    public static void setmSex(boolean mSex) {
        UserBean.mSex = mSex;
    }
}
