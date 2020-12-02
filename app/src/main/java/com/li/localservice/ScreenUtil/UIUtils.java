package com.li.localservice.ScreenUtil;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Insets;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.WindowMetrics;

import java.lang.reflect.Field;

/**
 * @author li
 * 版本：1.0
 * 创建日期：2020-11-30 15
 * 描述：
 */
public class UIUtils {
    private static UIUtils instance;

    //标准宽高   以UI图为准
    public static final float STANDARD_WIDTH = 720f;
    public static final float STANDARD_HEIGHT = 1280f;

    public static float displayMetricsWidth;
    public static float displayMetricsHeight;
    public static float systemBarHeight;

    public static UIUtils getInstance(Context context){
        if (instance == null){
            synchronized (UIUtils.class){
                if (instance ==null){
                    instance = new UIUtils(context);
                }
            }
        }
        return instance;
    }

    public static UIUtils getInstance(){
        if (instance == null){
            throw new RuntimeException("UiUtil应该先调用含有构造方法进行初始化");
        }
        return instance;
    }

    private UIUtils(Context context){

        //计算缩放系数
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (displayMetricsWidth == 0.0f || displayMetricsHeight == 0.0f){
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.R){
                WindowMetrics windowMetrics = windowManager.getCurrentWindowMetrics();
                Insets insets = windowMetrics.getWindowInsets()
                        .getInsetsIgnoringVisibility(WindowInsets.Type.systemBars());
                systemBarHeight = getStatusBarHeight(context);
                Log.e("TAG", "UIUtils: -->"+ insets.toString());
                //横屏
                int tempWidth =windowMetrics.getBounds().width() - insets.left - insets.right;
                int tempHeight =windowMetrics.getBounds().height() - insets.top - insets.bottom;
                Log.e("TAG", "UIUtils: -->"+windowMetrics.getBounds().height() + ">>>"+tempHeight);
                if (tempWidth > tempHeight){
                    displayMetricsWidth = (float)(tempHeight);
                    displayMetricsHeight = (float)(tempWidth-systemBarHeight);
                }else {
                    //竖屏
                    displayMetricsWidth = (float)(tempWidth);//1080 1731 48 1779
                    displayMetricsHeight = (float)(tempHeight-systemBarHeight);
                }

            }else {
                //获取设备的真实宽高
                DisplayMetrics displayMetrics = new DisplayMetrics();
                windowManager.getDefaultDisplay().getMetrics(displayMetrics);
                systemBarHeight = getStatusBarHeight(context);
                //横屏
                if (displayMetrics.widthPixels > displayMetrics.heightPixels){
                    displayMetricsWidth = (float)(displayMetrics.heightPixels);
                    displayMetricsHeight = (float)(displayMetrics.widthPixels-systemBarHeight);
                }else {
                    //竖屏
                    displayMetricsWidth = (float)(displayMetrics.widthPixels);//1080 1746 48 1794
                    displayMetricsHeight = (float)(displayMetrics.heightPixels-systemBarHeight);
                }
            }
            Log.e("TAG",
                    "UIUtils: displayMetricsWidth ="+displayMetricsWidth +"displayMetricsHeight ="+displayMetricsHeight+"systemBarHeight ="+ systemBarHeight);
        }
    }

    /**
     * 计算状态栏高度
     * @param context context
     * @return 高度
     */
    public  int getStatusBarHeight(Context context) {
            Resources resources = context.getResources();
            int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
            int height = resources.getDimensionPixelSize(resourceId);
            if (height!=-1){
                return height;
            }
        return getValue(context,"com.android.internal.R$dimen","system_bar_height",48);
    }

    private int getValue(Context context, String dimeClass, String system_bar_height, int defaultValue) {
//        com.android.internal.R$dimen    system_bar_height   状态栏的高度
        try {
            Class<?> clz=Class.forName(dimeClass);
            Object object = clz.newInstance();
            Field field=clz.getField(system_bar_height);
            int id=Integer.parseInt(field.get(object).toString());
            return context.getResources().getDimensionPixelSize(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }

    /**
     * 获取适配后的宽度
     * 例如传入100宽度，单位为像素，计算其在当前设备上应显示的像素宽度
     */
    public int getWidth(int width) {
        return Math.round((float)width * displayMetricsWidth / STANDARD_WIDTH);
    }

    /**
     * 获取适配后的高度
     */
    public int getHeight(int height) {
        return Math.round((float)height * displayMetricsHeight / (STANDARD_HEIGHT-systemBarHeight));
    }

}
