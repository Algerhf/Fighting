package com.example.demo

import android.app.Activity

object ActivityManager {

    private val list = ArrayList<Activity>()

    fun addActivity(activity: Activity?) {
        if (null != activity) {
            list.add(activity)
        }
    }

    fun removeActivity(activity: Activity?) {
        if (null != activity) {

            list.forEach {
                if (it === activity) {
                    list.remove(it)
                }
            }
        }
    }
}