package com.example.lifecycles.databinding

/**
 *
 * @author hufan
 * 创建日期：2023/4/3 12:19
 * 描述：
 *
 */
class StarUtils {

    companion object {

        @JvmStatic
        fun getStar(star: Int): String {
            return when (star) {
                1 -> "一星"
                2 -> "二星"
                3 -> "三星"
                4 -> "四星"
                5 -> "五星"
                else -> ""
            }
        }
    }
}