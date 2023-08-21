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

    // 协程的调度器
    // 1、Dispatchers.Main：Android上的主线程，用来处理UI交互和一些轻量级任务： 调用suspend函数、调用UI函数、更新LiveData
    // 2、Dispatchers.IO：非主线程，专为磁盘和网络IO进行了优化  数据库、文件读写、网络处理
    // 3、Dispatchers.Default：非主线程，专为CPU密集型任务进行了优化  数组排序，JSON数据解析，处理差异判断

    // 协程的启动模式：
    // 1、DEFAULT：协程创建后，立即开始调度，在调度前如果协程被取消，其将直接进入取消响应的状态
    // 2、ATOMIC：协程创建后，立即开始调度，协程执行到第一个挂起点之前不响应取消
    // 3、LAZY：只有协程被需要时，包括主动调用协程的start、join或者await等函数时才会开始调度
    //         如果调度前被取消，那么该协程将直接进入异常结束状态
    // 4、UNDISPATCHED：协程创建后立即在当前函数调用栈中执行，直到遇到第一个真正挂起的点

    // 协程的作用域构建器
    // 1、coroutineScope：一个协程失败了，所有其他兄弟协程也会被取消
    // 2、supervisorScope:一个协程失败了，不会影响其他兄弟协程
    //                    当自身异常时，作用域内的其他协程也会被取消

    // Job对象的生命周期
    // new -> active -> Completing -> Completed
    // (active、Completing) -> canceling -> canceled

    // 协程的取消
    // 1、取消作用域会取消它的子协程
    // 2、被取消的子协程并不会影响其余兄弟协程
    // 3、协程通过抛出一个特殊的异常CancellationException来处理取消操作
    // 4、所有Kotlinx.coroutines中的挂起函数（withContext、delay等），都是可取消的

    // 协程的上下文
    // job
    // CoroutineDispatcher
    // CoroutineName
    // CoroutineExceptionHandler

    // 异常的传播特性
    // 1、取消它自己的子级
    // 2、取消它自己
    // 3、将异常传播并传递给他的父级

    // 异常的捕获
    // 使用CoroutineExceptionHandle对协程的异常进行捕获
    // 以下条件被满足时，异常就会被捕获
    // 时机：异常是被自动抛出异常的协程所抛出的（使用Launch,而不是async)
    // 位置: 在CoroutineScope的CoroutineContext中
    //      或在一个根协程（CoroutineScope或supervisorScope的直接子协程）中

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