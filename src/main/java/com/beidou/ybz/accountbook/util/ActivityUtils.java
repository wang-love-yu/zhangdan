package com.beidou.ybz.accountbook.util;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Window;
import android.view.WindowManager;


import com.beidou.ybz.accountbook.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by bob on 2016/11/1.
 */
public class ActivityUtils {


    /**
     * 状态栏设置
     */
    public static void setStatusBar(Activity activity) {

        /**
         * 透明状态栏
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = activity.getWindow();
            // Translucent status bar
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }



    /**
     * activity跳转 右侧滑入
     *
     * @param a
     * @param b
     */
    public static void startActivityRightIn(Activity a, Class<?> b) {
        Intent in = new Intent(a, b);
        a.startActivity(in);
        a.overridePendingTransition(R.anim.left_in, 0);
    }

    /**
     * activity跳转
     *
     * @param a
     * @param b
     */
    public static void startActivity(Activity a, Class<?> b) {
        Intent in = new Intent(a, b);
        a.startActivity(in);
    }

    /**
     * activity跳转
     *
     * @param a
     * @param b
     */
    public static void startActivityWithFrom(Activity a, Class<?> b,String from) {
        Intent in = new Intent(a, b);
        in.putExtra("from",from);
        a.startActivity(in);
    }

    /**
     *
     * @param a
     * @param b
     * @param from 来自哪个界面，便于跳转逻辑
     */
    public static void startActivityRightInWithFrom(Activity a, Class<?> b,String from) {
        Intent in = new Intent(a, b);
        in.putExtra("from",from);
        a.startActivity(in);
        a.overridePendingTransition(R.anim.left_in, 0);
    }

    /**
     * activityty跳转，带url
     */
    public static void startActivityRightInWithUrl(Activity a, Class<?> b,String c) {
        Intent in = new Intent(a, b);
        in.putExtra("url",c);
        a.startActivity(in);
        a.overridePendingTransition(R.anim.left_in, 0);
    }

    /**
     * 带list
     * @param a
     * @param b
     * @param c
     */
    public static void startActivityRightInWithList(Activity a, Class<?> b,List c) {
        Intent in = new Intent(a, b);
//        in.putExtra("url",c);
        in.putStringArrayListExtra("list", (ArrayList<String>) c);
        a.startActivity(in);
        a.overridePendingTransition(R.anim.left_in, 0);
    }

    /**
     *
     * @param activity
     */
    public static void finishActivity(Activity activity) {
        try {
            if(activity != null) {
                activity.finish();
                activity.overridePendingTransition(0, R.anim.right_out);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static void startActivityDownIn(Activity a, Class<?> b) {
        Intent in = new Intent(a, b);
        a.startActivity(in);
        a.overridePendingTransition(R.anim.slide_up_in, 0);
    }

    /**
     * 代码调用RefreshLoading,需要用post
     * @param refresh
     */
    public static void setRefreshLoading(@NonNull final SwipeRefreshLayout swipeRefreshLayout, final boolean refresh){
        try {
            swipeRefreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    swipeRefreshLayout.setRefreshing(refresh);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
