package com.example.javademo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hufan
 * 创建日期：2023/4/13 12:30
 * 描述：
 */
public class SkinManager {

    private Map<String, Resources> mCachedHashMap = new HashMap<>();

    private SkinManager() {

    }

    public static SkinManager getInstance() {
        return SingleInstanceHolder.mInstance;
    }

    static class SingleInstanceHolder {
        private static final SkinManager mInstance = new SkinManager();
    }

    public Resources getResource(Context context, String path) {

        if (mCachedHashMap.containsKey(path)) {
            return mCachedHashMap.get(path);
        }

        try {

            AssetManager assetManager = AssetManager.class.newInstance();

            @SuppressLint("DiscouragedPrivateApi")
            Method method = AssetManager.class.getDeclaredMethod("addAssetPath", String.class);

            method.setAccessible(true);

            /// 反射执行方法
            method.invoke(assetManager, path);

            // 创建自己的Resources
            Resources resources = new Resources(assetManager, createDisplayMetrics(context), createConfiguration(context));
            mCachedHashMap.put(path, resources);

            return resources;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public DisplayMetrics createDisplayMetrics(Context context) {
        return context.getResources().getDisplayMetrics();
    }

    public Configuration createConfiguration(Context context) {
        return context.getResources().getConfiguration();
    }
}
