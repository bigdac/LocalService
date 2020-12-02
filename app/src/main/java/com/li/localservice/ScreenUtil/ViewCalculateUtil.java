package com.li.localservice.ScreenUtil;

import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.IntDef;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.xml.transform.Source;

/**
 * @author li
 * 版本：1.0
 * 创建日期：2020-12-01 08
 * 描述：
 */
public class ViewCalculateUtil {
    public static final int TYPE_ALL = 1;//宽高适配 默认宽高适配
    public static final int TYPE_WIDTH = 2; //宽度适配
    public static final int TYPE_HEIGHT = 3;//高度适配


    @Documented
    @IntDef({TYPE_ALL, TYPE_WIDTH, TYPE_HEIGHT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface TypeStyle {
    }

    /** *
     *
     * @param layoutParams 目标布局的layoutParams
     * @param view  需要适配的view
     * @param width 宽 px
     * @param height 高 px
     * @param topMargin 居上 px
     * @param bottomMargin 居下 px
     * @param lefMargin 局左 px
     * @param rightMargin 居右px
     * @param type 类型
     */
    private static void setParams(ViewGroup.MarginLayoutParams layoutParams, View view, int width, int height, int topMargin
            , int bottomMargin, int lefMargin, int rightMargin, @TypeStyle int type) {
        if (width != ViewGroup.LayoutParams.MATCH_PARENT && width != ViewGroup.LayoutParams.WRAP_CONTENT ) {
            if (type == TYPE_ALL || type == TYPE_WIDTH) {
                layoutParams.width = UIUtils.getInstance().getWidth(width);
            } else {
                layoutParams.width = width;
            }
        } else {
            layoutParams.width = width;
        }
        if (height != ViewGroup.LayoutParams.MATCH_PARENT && height != ViewGroup.LayoutParams.WRAP_CONTENT) {
            if (type == TYPE_ALL || type == TYPE_HEIGHT)
                layoutParams.height = UIUtils.getInstance().getHeight(height);
            else
                layoutParams.height = height;
        } else {
            layoutParams.height = height;
        }
        layoutParams.topMargin = UIUtils.getInstance().getHeight(topMargin);
        layoutParams.bottomMargin = UIUtils.getInstance().getHeight(bottomMargin);
        layoutParams.leftMargin = UIUtils.getInstance().getWidth(lefMargin);
        layoutParams.rightMargin = UIUtils.getInstance().getWidth(rightMargin);
        view.setLayoutParams(layoutParams);
    }

    /**
     * RelativeLayout
     * 根据屏幕的大小设置view的高度，间距
     */
    public static void setViewRelativeLayoutParam(View view, int width, int height,
                                                  int topMargin, int bottomMargin, int lefMargin, int rightMargin,
                                                  @TypeStyle int type) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
        if (layoutParams != null) {
            setParams(layoutParams, view, width, height, topMargin, bottomMargin, lefMargin, rightMargin, type);
        } else {
            throw new NullPointerException("No find   RelativeLayout.LayoutParams");
        }

    }

    public static void setViewRelativeLayoutParam(View view, int width, int height,
                                                  int topMargin, int bottomMargin, int lefMargin, int rightMargin
    ) {
        setViewRelativeLayoutParam(view, width, height, 0, 0, 0, 0, TYPE_ALL);

    }

    public static void setViewRelativeLayoutParam(View view, int width, int height, @TypeStyle int type) {
        setViewRelativeLayoutParam(view, width, height, 0, 0, 0, 0, type);
    }

    public static void setViewRelativeLayoutParam(View view, int width, int height,
                                                  int topMargin, int lefMargin, @TypeStyle int type) {
        setViewRelativeLayoutParam(view, width, height, topMargin, 0, lefMargin, 0, type);
    }

    /**
     * 设置LinearLayout中 view的高度宽度
     *
     * @param view
     * @param width
     * @param height
     */
    public static void setViewLinearLayoutParam(View view, int width, int height, int topMargin, int bottomMargin, int lefMargin,
                                                int rightMargin, @TypeStyle int type) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
        if (layoutParams != null) {
            setParams(layoutParams, view, width, height, topMargin, bottomMargin, lefMargin, rightMargin, type);
        } else {
            throw new NullPointerException("No find LinearLayout.LayoutParams");
        }
    }

    public static void setViewLinearLayoutParam(View view, int width, int height, int topMargin, int bottomMargin, int lefMargin,
                                                int rightMargin) {
        setViewLinearLayoutParam(view, width, height, 0, 0, 0, 0, TYPE_ALL);

    }

    public static void setViewLinearLayoutParam(View view, int width, int height, @TypeStyle int type) {
        setViewLinearLayoutParam(view, width, height, 0, 0, 0, 0, type);
    }

    public static void setViewLinearLayoutParam(View view, int width, int height,
                                                int topMargin, int lefMargin, @TypeStyle int type) {
        setViewLinearLayoutParam(view, width, height, topMargin, 0, lefMargin, 0, type);
    }

    /**
     * 设置FrameLayout view的高度宽度中 view的高度宽度
     *
     * @param view
     * @param width
     * @param height
     */
    public static void setViewFrameLayoutParam(View view, int width, int height, int topMargin, int bottomMargin,
                                               int lefMargin, int rightMargin, @TypeStyle int type) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        if (layoutParams != null) {
            setParams(layoutParams, view, width, height, topMargin, bottomMargin, lefMargin, rightMargin, type);
        } else {
            throw new NullPointerException("No find FrameLayout.LayoutParams");
        }
    }

    public static void setViewFrameLayoutParam(View view, int width, int height, int topMargin, int bottomMargin,
                                               int lefMargin, int rightMargin) {
        setViewFrameLayoutParam(view, width, height, 0, 0, 0, 0, TYPE_ALL);
    }

    public static void setViewFrameLayoutParam(View view, int width, int height, @TypeStyle int type) {
        setViewFrameLayoutParam(view, width, height, 0, 0, 0, 0, type);
    }

    public static void setViewFrameLayoutParam(View view, int width, int height,
                                               int topMargin, int lefMargin, @TypeStyle int type) {
        setViewFrameLayoutParam(view, width, height, topMargin, 0, lefMargin, 0, type);
    }

    /**
     * 设置ConstraintLayout view的高度宽度中 view的高度宽度
     *
     * @param view
     * @param width
     * @param height
     */
    public static void setViewConstraintLayoutParam(View view, int width, int height, int topMargin, int bottomMargin
            , int lefMargin, int rightMargin, @TypeStyle int type) {
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) view.getLayoutParams();
        if (layoutParams != null) {
            setParams(layoutParams, view, width, height, topMargin, bottomMargin, lefMargin, rightMargin, type);
        } else {
            throw new NullPointerException("No find ConstraintLayout.LayoutParams");
        }
    }

    public static void setViewConstraintLayoutParam(View view, int width, int height, int topMargin, int bottomMargin
            , int lefMargin, int rightMargin) {
        setViewConstraintLayoutParam(view, width, height, 0, 0, 0, 0, TYPE_ALL);
    }

    public static void setViewConstraintLayoutParam(View view, int width, int height, @TypeStyle int type) {
        setViewConstraintLayoutParam(view, width, height, 0, 0, 0, 0, type);
    }

    public static void setViewConstraintLayoutParam(View view, int width, int height,
                                                    int topMargin, int lefMargin, @TypeStyle int type) {
        setViewConstraintLayoutParam(view, width, height, topMargin, 0, lefMargin, 0, type);
    }

    public static void setTextSize(TextView view, int size) {
        view.setTextSize(TypedValue.COMPLEX_UNIT_PX, UIUtils.getInstance().getHeight(size));
    }
}
