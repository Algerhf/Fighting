package com.example.jetpack.room

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpack.databinding.ItemLayoutStudentBinding

class StudentAdapter(private val list: MutableList<Student>) :
    RecyclerView.Adapter<StudentAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: ItemLayoutStudentBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemLayoutStudentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val student = list[position]
        holder.binding.stu = student
        holder.binding.executePendingBindings()
    }
}