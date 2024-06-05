package com.example.lifecycles.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class DataBinding5Activity : AppCompatActivity() {

    private val mBinding by lazy{
        ActivityDataBinding5Binding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        val viewModel = BindingViewModel5(User("wangyibo"))
        mBinding.model = viewModel

        mBinding.button.setOnClickListener {
            if("wangyibo" == viewModel.userName.get()){
                viewModel.userName.set("baibing")
            }else{
                viewModel.userName.set("wangyibo")
            }
        }
    }

}