package com.example.kotlincoroutine.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kotlincorountine.databinding.FragmentUserBinding

class UserFragment : Fragment() {

    private val mBinding: FragmentUserBinding by lazy {
        FragmentUserBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mBinding.btnAdd.setOnClickListener {

        }
    }
}