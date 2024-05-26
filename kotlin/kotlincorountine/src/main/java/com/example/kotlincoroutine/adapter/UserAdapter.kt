package com.example.kotlincoroutine.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlincorountine.databinding.ItemUserBinding
import com.example.kotlincoroutine.db.User

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    val data = ArrayList<User>()
    fun setData(list: List<User>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val holder = UserViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        return holder
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = data[position]
        holder.binding.tvUserInfo.text = "${item.uid},${item.firstName},${item.lastName}"
    }

    override fun getItemCount() = data.size

    inner class UserViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)
}