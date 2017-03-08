package com.lyz.monitor.utils;

import android.util.Log;

/**
 * 图像处理类
 *
 * @author liuyazhuang
 */
public abstract class ImageProcessing {

    /**
     * 内部调用的处理图片的方法
     *
     * @param yuv420sp
     * @param width
     * @param height
     * @return
     */
    private static int[] decodeYUV420SPtoRedSum(byte[] yuv420sp, int width, int height) {
        int[] res = new int[]{0, 0, 0, 0};
        if (yuv420sp == null)
            return res;
        final int frameSize = width * height;
        // sum为灰度，sum1为红色通道，sum2为绿色通道，sum3为蓝色通道
        int sum = 0;
        int sum1 = 0;
        int sum2 = 0;
        int sum3 = 0;
        // 计算红色通道的平均值算法。
        for (int j = 0, yp = 0; j < height; j++) {
            int uvp = frameSize + (j >> 1) * width, u = 0, v = 0;
            for (int i = 0; i < width; i++, yp++) {
                int y = (0xff & ((int) yuv420sp[yp])) - 16;
                if (y < 0)
                    y = 0;
                if ((i & 1) == 0) {
                    v = (0xff & yuv420sp[uvp++]) - 128;
                    u = (0xff & yuv420sp[uvp++]) - 128;
                }
                int y1192 = 1192 * y;
                int r = (y1192 + 1634 * v);
                int g = (y1192 - 833 * v - 400 * u);
                int b = (y1192 + 2066 * u);

                if (r < 0)
                    r = 0;
                else if (r > 262143)
                    r = 262143;
                if (g < 0)
                    g = 0;
                else if (g > 262143)
                    g = 262143;
                if (b < 0)
                    b = 0;
                else if (b > 262143)
                    b = 262143;

                int pixel = 0xff000000 | ((r << 6) & 0xff0000)
                        | ((g >> 2) & 0xff00) | ((b >> 10) & 0xff);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = pixel & 0xff;
                sum1 += red;
                sum2 += green;
                sum3 += blue;

                int temp = (int) (0.229 * red + 0.587 * green + 0.114 * blue);
                sum += temp;

            }

        }

        // 计算灰度值平均值算法
        /*for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
				int index=i+j*width;
				int tempV=yuv420sp[index]&0xff;
				sum+=tempV;
			}
		}*/
//        Log.i("value sum", sum + "");
        res[0] = sum;
        res[1] = sum1;
        res[2] = sum2;
        res[3] = sum3;
        return res;
    }

    /**
     * 对外开放的图像处理方法
     *
     * @param yuv420sp
     * @param width
     * @param height
     * @return res[0]为红色通道，res[1]为灰度
     */
    public static double[] decodeYUV420SPtoRedAvg(byte[] yuv420sp, int width, int height) {
        double[] res = new double[]{0, 0, 0, 0};
        if (yuv420sp == null)
            return res;
        final int frameSize = width * height;
        int[] sum = decodeYUV420SPtoRedSum(yuv420sp, width, height);
        res[0] = Math.log((double) sum[0] / (double) frameSize);
        res[1] = Math.log((double) sum[1] / (double) frameSize);
        res[2] = Math.log((double) sum[2] / (double) frameSize);
        res[3] = Math.log((double) sum[3] / (double) frameSize);
//        Log.i("average value", aver + "");
//        Log.i("framesize",width+":"+height);
        return res;
    }
}