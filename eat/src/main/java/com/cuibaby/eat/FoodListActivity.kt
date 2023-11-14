package com.cuibaby.eat

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.cuibaby.eat.databinding.ActivityFoodListBinding
import com.cuibaby.eat.db.Food

class FoodListActivity : AppCompatActivity() {

    private val mBinding by lazy {
        ActivityFoodListBinding.inflate(layoutInflater)
    }

    private val mAdapter by lazy {
        FoodAdapter(mutableListOf())
    }

    private val mViewModel by viewModels<FoodViewModel>()

    private var mFoodType: String = FoodType.VEGETABLE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarHelper.initStatusBar(this)
        setContentView(mBinding.root)

        mFoodType = if (savedInstanceState == null) {
            intent?.getStringExtra(KEY_FOOD_TYPE) ?: FoodType.VEGETABLE
        } else {
            savedInstanceState.getString(KEY_FOOD_TYPE) ?: FoodType.VEGETABLE
        }
        mViewModel.mCurrentFoodType.value = mFoodType

        mBinding.recyclerView.layoutManager = LinearLayoutManager(this)
        mBinding.recyclerView.adapter = mAdapter
        mBinding.recyclerView.addItemDecoration(ItemDividerDecoration(this))

        mAdapter.setOnItemDeleteListener(object : FoodAdapter.DeleteListener {
            override fun onDelete(view: View, position: Int, food: Food) {
                AlertDialog.Builder(this@FoodListActivity)
                    .setTitle(getString(R.string.delete_title, food.name))
                    .setPositiveButton(
                        getString(R.string.confirm)
                    ) { dialog, _ ->
                        dialog.dismiss()
                        mViewModel.delete(food)
                        mAdapter.removeData(position)
                        showToast(R.string.delete_success)
                    }.setNegativeButton(
                        getString(R.string.cancel)
                    ) { dialog, _ ->
                        dialog.dismiss()
                    }.show()
            }
        })

        mViewModel.mFoodLiveData.observe(this) {
            it?.let { list ->
                mAdapter.updateData(list)
            }
        }

        mViewModel.queryAll()

        mBinding.btnAdd.setOnClickListener {
            hideSoftInput(this@FoodListActivity)

            val text = mBinding.etName.text.toString().trim()
            if (text.isEmpty()) {
                showToast(R.string.please_input_food_name)
                return@setOnClickListener
            }

            mViewModel.queryByName(text) { exist ->
                if (exist) {
                    showToast(R.string.food_exist)
                } else {
                    val foodType = mViewModel.mCurrentFoodType.value ?: FoodType.VEGETABLE
                    val food = Food(text, foodType)
                    mViewModel.insert(food)
                    showToast(R.string.add_food_success)
                    mAdapter.addData(food)
                    mBinding.recyclerView.smoothScrollToPosition(mAdapter.itemCount)
                }
            }
        }
    }

    private fun hideSoftInput(context: Context) {
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(mBinding.etName.windowToken, 0);
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(KEY_FOOD_TYPE, mFoodType)
    }

    companion object {
        const val KEY_FOOD_TYPE = "key_food_type"

        fun startActivityAction(context: Context, foodType: String) {
            context.startActivity(Intent(context, FoodListActivity::class.java).apply {
                putExtra(KEY_FOOD_TYPE, foodType)
            })
        }
    }
}