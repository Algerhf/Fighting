package com.example.lifecycles.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lifecycles.R
import com.example.lifecycles.databinding.ActivityRoom1Binding

class Room1Activity : AppCompatActivity() {

    private val mViewModel: RoomViewModel1 by viewModels()

    val students = mutableListOf(Student("王二"), Student("张三"), Student("李四"), Student("老六"))
    val mList = mutableListOf<Student>()

    private val mBinding by lazy {
        ActivityRoom1Binding.inflate(layoutInflater)
    }

    private val mAdapter: StudentAdapter by lazy {
        StudentAdapter(this@Room1Activity, mList)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        mBinding.model = mViewModel
        mBinding.lifecycleOwner = this

        mViewModel.queryAll().observe(this) { data ->
            mList.clear()

            data?.let {
                if (it.isNotEmpty()) {
                    mList.addAll(it)
                }
            }
            mAdapter.notifyDataSetChanged()
        }

        mBinding.recyclerView.layoutManager = LinearLayoutManager(this)
        mBinding.recyclerView.adapter = mAdapter

        mBinding.btnAdd.setOnClickListener {
            mViewModel.insert(*students.toTypedArray())
        }

        mBinding.btnDelete.setOnClickListener {
            mViewModel.delete(Student(58,"张三",0))
        }

        mBinding.btnUpdate.setOnClickListener {
            val stu = Student()
            stu.id = 60
            stu.name = "老六"
            stu.age = 20
            mViewModel.update(stu)
        }

        mBinding.btnDeleteAll.setOnClickListener {
            mViewModel.deleteAll()
        }
    }
}