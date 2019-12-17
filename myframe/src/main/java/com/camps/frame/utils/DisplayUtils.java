package com.camps.frame.utils;

import android.content.Context;
import android.graphics.Paint;
import android.util.TypedValue;

/**
 * 显示相关工具类
 */
public class DisplayUtils {

    public static int dp2px(Context context, float dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, context.getResources().getDisplayMetrics());
    }

    /**
     * 该方法的作用:px转换为sp（文字大小单位）
     *
     * @param context
     * @param pxValue
     */
    public static float px2sp(Context context, float pxValue)
    {
        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        return (pxValue / scaledDensity + 0.5f);
    }



    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight(Context context){
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static int calcStatusBarHeight(Context context) {
        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height").get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }

    public static boolean calculateShowCheckAllText(Context context, String content) {
        Paint textPaint = new Paint();
        textPaint.setTextSize(DisplayUtils.dp2px(context,16f));
        float textWidth = textPaint.measureText(content);
        float maxContentViewWidth = DisplayUtils.getScreenWidth(context) - DisplayUtils.dp2px(context, 74f);
        float maxLines = textWidth / maxContentViewWidth;
        return maxLines > 4;
    }



}
