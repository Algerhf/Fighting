package com.cuibaby.eat

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.appcompat.app.AppCompatActivity
import com.cuibaby.eat.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private val mBinding by lazy {
        ActivitySplashBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // 针对 Android 11 及以上版本
            val insetsController = window.insetsController
            if (insetsController != null) {
                insetsController.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
                insetsController.systemBarsBehavior =
                    WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else {
            // 针对 Android 10 及以下版本
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // 隐藏导航栏
                    or View.SYSTEM_UI_FLAG_FULLSCREEN // 全屏显示
                    or View.SYSTEM_UI_FLAG_IMMERSIVE)
        }

//        mBinding.sv.setBackgroundColor(Color.BLACK)
//        mBinding.sv.resume()
        mBinding.sv.setOnClickListener {
            startActivity(Intent(this@SplashActivity,MainActivity::class.java))
        }
    }
}