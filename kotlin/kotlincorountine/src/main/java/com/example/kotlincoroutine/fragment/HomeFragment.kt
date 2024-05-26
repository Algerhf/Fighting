package com.example.kotlincoroutine.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.kotlincorountine.R
import com.example.kotlincorountine.databinding.FragmentHomeBinding
import kotlinx.coroutines.CoroutineExceptionHandler


class HomeFragment : Fragment() {

    private val mBinding: FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val handler = CoroutineExceptionHandler { _, throwable ->
            Log.d("hf","handle exception: $throwable")
        }

        mBinding.btnDownload.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_downloadFragment)
        }
        mBinding.btnRoom.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_userFragment)
        }
    }
}