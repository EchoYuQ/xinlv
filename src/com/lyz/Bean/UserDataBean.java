package com.lyz.Bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuqing on 2016/12/23.
 */
public class UserDataBean implements Serializable {
    private static final long serialVersionUID = -7620435178023928252L;
    private String userName = "";
    private String currentTime = "";
    // 性别 0表示男 1表示女
    private int sex = 0;
    private int age = -1;
    private int fatigue = -1;

    /**
     * 心率
     */
    private int heartrate=0;
    private List<Double> datas = new ArrayList<Double>();
    private List<Double> red_datas = new ArrayList<Double>();
    private List<Double> green_datas = new ArrayList<Double>();
    private List<Double> blue_datas = new ArrayList<Double>();

    private List<Double> new_datas = new ArrayList<Double>();

    private List<Double> rr_datas = new ArrayList<Double>();

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

    public List<Double> getNew_datas() {
        return new_datas;
    }

    public void setNew_datas(List<Double> new_datas) {
        this.new_datas = new_datas;
    }

    public List<Double> getRr_datas() {
        return rr_datas;
    }

    public void setRr_datas(List<Double> rr_datas) {
        this.rr_datas = rr_datas;
    }

    public List<Double> getRed_datas() {
        return red_datas;
    }

    public void setRed_datas(List<Double> red_datas) {
        this.red_datas = red_datas;
    }

    public List<Double> getGreen_datas() {
        return green_datas;
    }

    public void setGreen_datas(List<Double> green_datas) {
        this.green_datas = green_datas;
    }

    public List<Double> getBlue_datas() {
        return blue_datas;
    }

    public void setBlue_datas(List<Double> blue_datas) {
        this.blue_datas = blue_datas;
    }

    public int getHeartrate() {
        return heartrate;
    }

    public void setHeartrate(int heartrate) {
        this.heartrate = heartrate;
    }
}
