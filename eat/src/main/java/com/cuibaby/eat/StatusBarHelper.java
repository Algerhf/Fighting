package com.cuibaby.eat;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class StatusBarHelper {

    private static final String TAG = StatusBarHelper.class.getSimpleName();

    public static void initStatusBar(Activity activity) {
        Window window = activity.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0 全透明实现
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            int systemUiVisibility = activity.getWindow().getDecorView().getSystemUiVisibility();
            window.getDecorView().setSystemUiVisibility(systemUiVisibility | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.setStatusBarColor(Color.TRANSPARENT);

            //window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4 全透明状态栏
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        }
        setStatusBarTextColor(activity, false);
    }

    private static void setStatusBarTextColor(Activity activity, boolean light) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int systemUiVisibility = activity.getWindow().getDecorView().getSystemUiVisibility();
            if (light) {//白色文字
                systemUiVisibility = systemUiVisibility & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            } else {//黑色文字
                systemUiVisibility = systemUiVisibility | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            }
            activity.getWindow().getDecorView().setSystemUiVisibility(systemUiVisibility);
        }
    }

    public static void initNavigationBar(Activity activity) {
        Window window = activity.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            window.setNavigationBarContrastEnforced(false);
        }
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        int systemUiVisibility = activity.getWindow().getDecorView().getSystemUiVisibility();
        systemUiVisibility = systemUiVisibility | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
        window.getDecorView().setSystemUiVisibility(systemUiVisibility);
        //设置导航栏按钮或导航条颜色
        window.setNavigationBarColor(Color.TRANSPARENT);
        setNavigationBarBtnColor(activity);
    }

    private static void setNavigationBarBtnColor(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int systemUiVisibility = activity.getWindow().getDecorView().getSystemUiVisibility();
            activity.getWindow().getDecorView().setSystemUiVisibility(systemUiVisibility | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        }
    }

    public static int getStatusBarHeight(Context context) {
        int resId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        int statusBarHeight = context.getResources().getDimensionPixelSize(resId);
        return statusBarHeight;
    }

    public static void paddingByStatusBar(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop() + getStatusBarHeight(activity), view.getPaddingRight(), view.getPaddingBottom());
    }
}
