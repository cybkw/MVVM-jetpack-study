package com.bkw.study.utils;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;

import androidx.core.content.ContextCompat;


/**
 * des 颜色处理工具类
 *
 * @date 2020/03/07
 */
public class ColorUtils {

    private static Context context;

    public static void init(Context context) {
        ColorUtils.context = context;
    }

    /**
     * 解析颜色
     *
     * @param colorStr
     * @param defaultColor
     * @return
     */
    public static int parseColor(String colorStr, int defaultColor) {
        if (TextUtils.isEmpty(colorStr)) {
            return defaultColor;
        }
        try {
            if (!colorStr.startsWith("#")) {
                colorStr = "#" + colorStr;
            }
            int color = Color.parseColor(colorStr);
            return color;
        } catch (Exception e) {
            return defaultColor;
        }
    }

    public static int parseColor(String colorStr) {
        if (TextUtils.isEmpty(colorStr)) {
            return 0;
        }
        try {
            if (!colorStr.startsWith("#")) {
                colorStr = "#" + colorStr;
            }
            return Color.parseColor(colorStr);
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 解析颜色
     *
     * @param color
     * @return
     */
    public static int parseColor(int color) {
        return ContextCompat.getColor(ColorUtils.context, color);
    }

    /**
     * 设置html字体色值
     *
     * @param text
     * @param color
     * @return
     */
    public static String setTextColor(String text, String color) {
        return "<font color=#" + color + ">" + text + "</font>";
    }

}
