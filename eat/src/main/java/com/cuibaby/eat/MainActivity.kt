package com.cuibaby.eat

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.cuibaby.eat.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {
    private val mBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private var random = false
    private var meatRandom = false
    private var vegetableList = mutableListOf<String>()
    private var meatList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)

        loadData()

        mBinding.tvVegetableTitle.text = "素菜 (${vegetableList.size}道)"
        mBinding.tvMeatTitle.text = "荤菜 (${meatList.size}道)"

        mBinding.btnVegetableStart.setOnClickListener {
            random = !random
            mBinding.btnVegetableStart.text = if (random) "停止" else "开始"
            if (random) {
                startVegetable()
            }
        }

        mBinding.btnMeatStart.setOnClickListener {
            meatRandom = !meatRandom
            mBinding.btnMeatStart.text = if (meatRandom) "停止" else "开始"
            if (meatRandom) {
                startMeat()
            }
        }
    }

    private fun loadData() {
        val array = resources.getStringArray(R.array.vegetable)
        vegetableList.addAll(array.toList())

        val meatArray = resources.getStringArray(R.array.meat)
        meatList.addAll(meatArray.toList())
    }

    private fun startVegetable() {
        Thread {
            while (random) {
                val vegetable = vegetableList.shuffled().first()
                runOnUiThread {
                    mBinding.tvVegetable.text = vegetable
                }
                Thread.sleep(80)
            }
        }.start()
    }

    private fun startMeat() {
        Thread {
            while (meatRandom) {
                val vegetable = meatList.shuffled().first()
                runOnUiThread {
                    mBinding.tvMeat.text = vegetable
                }
                Thread.sleep(80)
            }
        }.start()
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    // 这是第一次
    // 这是第二次
    // 这是第三次
    // 这是第四次
    // 这是第五次2

}