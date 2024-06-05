package com.example.lifecycles.room

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.lifecycles.databinding.ItemLayoutStudentBinding

class StudentAdapter(private val context: Context, private val list: MutableList<Student>) :
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
        holder.binding.root.setOnClickListener {
            Toast.makeText(context,"student = $student ",Toast.LENGTH_SHORT).show()
        }
    }
}