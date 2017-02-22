package com.lyz.monitor.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yuqing on 2017/2/16.
 */
public class CalculateHeartRate {

    /**
     * 简单寻峰算法
     * @param datas 原始数据数组
     * @return 峰值下标列表
     */
    public static List<Integer> findPeaks(double[] datas)
    {
        List<Integer> list=new ArrayList<Integer>();
        int length=datas.length;
        for(int i=1;i<length-1;i++)
        {
            if(datas[i]>datas[i-1]&&datas[i]>datas[i+1]) list.add(i);
        }
        return list;
    }

    /**
     * 计算心率
     * @param datas 原始数据数组
     * @param interval 两帧之间的间隔，单位ms
     * @return 心率值
     */
    public static int calHeartRate(double[] datas,int interval)
    {
        int heartRate=0;
        if (datas==null||datas.length==0) return 0;
        List<Integer> list= findPeaks(datas);
        int length=list.size();
        if(length>1)
        {
            int duration=list.get(length-1)-list.get(0);
            heartRate=(length-1)*(60*1000)/interval/duration;
        }
        return heartRate;

    }

    /**
     * 计算RR间隔
     * @param peaks_list 峰值下标列表
     * @return RR间隔列表
     */
    public static List<Integer> calRRInteval(List<Integer> peaks_list)
    {
        if (peaks_list==null||peaks_list.size()==0) return null;
        List<Integer> rrList=new ArrayList<Integer>();
        int length=peaks_list.size();
        for (int i=0;i<length-1;i++)
        rrList.add(peaks_list.get(i+1)-peaks_list.get(i));
        return rrList;
    }
}
