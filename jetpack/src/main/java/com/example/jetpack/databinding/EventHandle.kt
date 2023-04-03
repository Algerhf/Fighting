package com.example.jetpack.databinding

import android.content.Context
import android.view.View
import android.widget.Toast

/**
 *
 * @author hufan
 * 创建日期：2023/4/3 14:39
 * 描述：
 *
 */
class EventHandle {

    fun clickLike(v:View) {
        Toast.makeText(v.context, "喜欢", Toast.LENGTH_SHORT).show()
    }
}