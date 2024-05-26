package com.example.kotlincoroutine.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlincorountine.databinding.FragmentUserBinding
import com.example.kotlincoroutine.adapter.UserAdapter
import com.example.kotlincoroutine.viewmodel.UserViewModel
import kotlinx.coroutines.launch

class UserFragment : Fragment() {

    private val viewModel by viewModels<UserViewModel>()

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
            viewModel.insert(
                mBinding.edtFirstName.text.toString(),
                mBinding.edtLastName.text.toString()
            )
        }
        context?.let {
            val adapter = UserAdapter()
            mBinding.rv.layoutManager = LinearLayoutManager(it)
            mBinding.rv.adapter = adapter

            lifecycleScope.launch {
                viewModel.getAll().collect{ values ->
                    adapter.setData(values)
                }
            }
        }
    }
}