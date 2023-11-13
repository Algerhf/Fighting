package com.cuibaby.eat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cuibaby.eat.databinding.ItemFoodBinding
import com.cuibaby.eat.db.Food

class FoodAdapter(var mList: MutableList<Food>) :
    RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    private var mListener: DeleteListener? = null

    inner class FoodViewHolder(val mBinding: ItemFoodBinding) :
        RecyclerView.ViewHolder(mBinding.root)

    fun updateData(list: MutableList<Food>) {
        this.mList = list
        notifyDataSetChanged()
    }

    fun removeData(position: Int) {
        mList.removeAt(position)
        notifyItemRemoved(position)
    }

    fun addData(food: Food) {
        mList.add(food)
        notifyItemInserted(mList.size - 1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val binding = ItemFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FoodViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val item = mList.getOrNull(position)
        item?.let {
            holder.mBinding.model = it
            holder.mBinding.executePendingBindings()
            holder.mBinding.ivDelete.setOnClickListener { v ->
                v?.let { view ->
                    mListener?.onDelete(view, position, item)
                }
            }
        }
    }

    override fun getItemCount() = mList.size

    fun setOnItemDeleteListener(listener: DeleteListener?) {
        this.mListener = listener
    }

    interface DeleteListener {
        fun onDelete(view: View, position: Int, food: Food)
    }
}