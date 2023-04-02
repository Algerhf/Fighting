package com.example.jetpack.databinding

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.jetpack.R

class ImageViewBindingAdapter {
    companion object {
        @JvmStatic
        @BindingAdapter(value = ["url", "localRes"], requireAll = false)
        fun setImage(imageView: AppCompatImageView, url: String?, resId: Int) {
            url?.let {
                Glide.with(imageView.context).load(url).placeholder(R.mipmap.ic_launcher)
                    .into(imageView)
            } ?: run {
                imageView.setImageResource(resId)
            }
        }
    }
}