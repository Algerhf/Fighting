package com.example.kotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.kotlin.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val mBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val scope = MainScope()

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)

        scope.launch(Dispatchers.Main){
            
        }
    }
    
    private suspend fun obtainCacheData(){

    }
}
