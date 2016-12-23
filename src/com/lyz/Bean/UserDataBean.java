package com.lyz.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuqing on 2016/12/23.
 */
public class UserDataBean {
    private String userName;
    private String currentTime;
    private int fatigue;
    private List<Double> datas=new ArrayList<>();

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
}
