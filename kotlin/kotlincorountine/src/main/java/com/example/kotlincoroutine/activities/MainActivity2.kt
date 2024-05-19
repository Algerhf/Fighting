package com.example.kotlincoroutine.activities

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlincoroutine.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

class MainActivity2 : AppCompatActivity(), CoroutineScope by MainScope() {

    private val viewModel: MainViewModel by viewModels()

    // 挂起函数 只能在协程体内或其他挂起函数内调用
    // 协程    只能在调度器中运行

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        val binding = DataBindingUtil.setContentView<ActivityMain2Binding>(this, R.layout.activity_main2)
//        binding.mainViewModel = viewModel
//        binding.lifecycleOwner = this
//
//        binding.btnSubmit.setOnClickListener {
//            viewModel.getUser("XXX")
//        }
//    }
}