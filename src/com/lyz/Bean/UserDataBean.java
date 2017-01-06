package com.lyz.Bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuqing on 2016/12/23.
 */
public class UserDataBean implements Serializable{
    private static final long serialVersionUID = -7620435178023928252L;
    private String userName="";
    private String currentTime="";
    // 性别 0表示男 1表示女
    private int sex=0;
    private int age=-1;
    private int fatigue=-1;
    private List<Double> datas=new ArrayList<Double>();

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public int getFatigue() {
        return fatigue;
    }

    public void setFatigue(int fatigue) {
        this.fatigue = fatigue;
    }

    public List<Double> getDatas() {
        return datas;
    }

    public void setDatas(List<Double> datas) {
        this.datas = datas;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
