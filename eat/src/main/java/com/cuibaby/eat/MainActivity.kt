package com.cuibaby.eat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.cuibaby.eat.databinding.ActivityMainBinding
import com.cuibaby.eat.db.Food
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    private val mBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val mViewModel by viewModels<MainViewModel>()

    private var random = false
    private var meatRandom = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarHelper.initStatusBar(this)
        setContentView(mBinding.root)
        addListener()
        addObserver()
        importData()
    }

    override fun onResume() {
        super.onResume()
        mViewModel.queryAllVegetable()
        mViewModel.queryAllMeat()
    }

    private fun addObserver() {
        mViewModel.mVegetableLiveData.observe(this) {
            it?.let { list ->
                mBinding.tvVegetable.text = ""
                mBinding.tvVegetableTitle.text = getString(R.string.vegetable_number, list.size)
            }
        }

        mViewModel.mMeatLiveData.observe(this) {
            it?.let { list ->
                mBinding.tvMeat.text = ""
                mBinding.tvMeatTitle.text = getString(R.string.meat_number, list.size)
            }
        }
    }

    private fun addListener() {
        mBinding.btnVegetableStart.setOnClickListener {
            random = !random
            mBinding.btnVegetableStart.text =
                if (random) getString(R.string.stop) else getString(R.string.start)
            if (random) {
                startVegetable()
            }
        }

        mBinding.btnMeatStart.setOnClickListener {
            meatRandom = !meatRandom
            mBinding.btnMeatStart.text =
                if (meatRandom) getString(R.string.stop) else getString(R.string.start)
            if (meatRandom) {
                startMeat()
            }
        }

        mBinding.tvVegetableTitle.setOnClickListener {
            FoodListActivity.startActivityAction(this@MainActivity, FoodType.VEGETABLE)
        }

        mBinding.tvMeatTitle.setOnClickListener {
            FoodListActivity.startActivityAction(this@MainActivity, FoodType.MEAT)
        }
    }

    private fun importData() {
        val hasImportVeg = SpUtils.getBoolean(KEY_IMPORT_VEGETABLE, false)
        if (!hasImportVeg) {
            val array = resources.getStringArray(R.array.vegetable)
            val list = array.map {
                Food(it, FoodType.VEGETABLE)
            }
            mViewModel.insert(*list.toTypedArray())

            SpUtils.putBoolean(KEY_IMPORT_VEGETABLE, true)

            lifecycleScope.launch {
                delay(1000)
                mViewModel.queryAllVegetable()
            }
        }

        val hasImportMeat = SpUtils.getBoolean(KEY_IMPORT_MEAT, false)
        if (!hasImportMeat) {
            val array = resources.getStringArray(R.array.meat)
            val list = array.map {
                Food(it, FoodType.MEAT)
            }
            mViewModel.insert(*list.toTypedArray())

            SpUtils.putBoolean(KEY_IMPORT_MEAT, true)

            lifecycleScope.launch {
                delay(1000)
                mViewModel.queryAllMeat()
            }
        }
    }

    private fun startVegetable() {
        val vegetableList = mViewModel.mVegetableLiveData.value ?: mutableListOf()
        if (vegetableList.isEmpty()) {
            showToast(R.string.no_vegetable)
            return
        }

        lifecycleScope.launch(Dispatchers.IO) {
            while (random) {
                val vegetable = vegetableList.shuffled().first()
                withContext(Dispatchers.Main) {
                    mBinding.tvVegetable.text = vegetable.name
                }
                delay(80)
            }
        }
    }

    private fun startMeat() {
        val meatList = mViewModel.mMeatLiveData.value ?: mutableListOf()
        if (meatList.isEmpty()) {
            showToast(R.string.no_meat)
            return
        }

        lifecycleScope.launch(Dispatchers.IO) {
            while (meatRandom) {
                val meat = meatList.shuffled().first()
                withContext(Dispatchers.Main) {
                    mBinding.tvMeat.text = meat.name
                }
                delay(80)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        random = false
        meatRandom = false
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
        const val KEY_IMPORT_VEGETABLE = "KEY_IMPORT_VEGETABLE"
        const val KEY_IMPORT_MEAT = "KEY_IMPORT_MEAT"
    }
}