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
        for(int i=1;i<length-4;i++)
        {
            if(datas[i]>datas[i-1]&&datas[i]>datas[i+1])
            {
                if(i+2<length)
                {
                    if(datas[i]>datas[i+2])
                    {
//                        if(calDiff(datas[i-2],datas[i-1],datas[i+1],datas[i+2])<0)
//                        {
//                            list.add(i);
//                        }

                        if(i+3<length)
                        {
                            if (datas[i]>datas[i+3])
                            {
                                if(i+4<length)
                                {
                                    if (datas[i]>datas[i+4])
                                    {
                                        list.add(i);
                                    }
                                }
                                else
                                {
                                    list.add(i);
                                }

                            }
                        }
                        else
                        {
                            list.add(i);
                        }

                    }
                }
                else
                {
                    list.add(i);
                }

            }
        }
        System.out.println("findPeaks()");
        System.out.println(list);
        return list;
    }

    /**
     * 简单寻峰算法
     * @param list 原始数据列表
     * @return 峰值下标列表
     */
    public static List<Integer> findPeaks(List<Double> list)
    {
        double[] datas=new double[list.size()];
        for(int i=0;i<list.size();i++)
        {
            datas[i]=list.get(i);
        }
        List<Integer> res_list=findPeaks(datas);
        return res_list;
    }

    /**
     * 计算心率
     * @param datas 数据数组
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

    public static int calHeartRate(List<Integer> peaks_list,int interval)
    {
        int heartRate=0;
        if (peaks_list==null||peaks_list.size()==0) return 0;
        int length=peaks_list.size();
        if(length>1)
        {
            int duration=peaks_list.get(length-1)-peaks_list.get(0);
            heartRate=(length-1)*(60*1000)/interval/duration;
        }
        return heartRate;

    }

    /**
     * 计算RR间隔
     * @param peaks_list 峰值下标列表
     * @return RR间隔列表
     */
    public static List<Double> calRRInteval(List<Integer> peaks_list)
    {
        if (peaks_list==null||peaks_list.size()==0) return null;
        List<Double> rrList=new ArrayList<Double>();
        int length=peaks_list.size();
        for (int i=0;i<length-1;i++)
        rrList.add((double)(peaks_list.get(i+1)-peaks_list.get(i)));
        return rrList;
    }

    public static double calDiff(double x1,double x2,double x3,double x4)
    {
        return x3+2*x4-x2-2*x1;
    }

}
