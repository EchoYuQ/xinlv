package com.lyz.utils;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * 保存数据到手机的工具类
 * Created by yuqing on 2016/12/23.
 */
public class SaveData {

    /**
     * 保存数据到SD卡中
     * @param datas
     * @param filename
     */
    public static void saveData2Sdcard(String datas,String filename) {
        // 创建文件对象，由于不同手机SDcard目录不同，通过Environment.getExternalStorageDirectory()获得路径。
        File file = new File(Environment.getExternalStorageDirectory(), filename+".txt");
        Log.i("File path", Environment.getExternalStorageDirectory().getAbsolutePath() + "");
        if (!file.exists()) {
            try {
                boolean result=file.createNewFile();
                Log.i("create file",result+"");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileOutputStream fos = new FileOutputStream(file);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            bw.write(datas);
            bw.flush();
            bw.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
