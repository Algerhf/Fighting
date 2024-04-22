package com.example.kotlincoroutine.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlincorountine.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private val mBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        GlobalScope.launch {  }
    }
}