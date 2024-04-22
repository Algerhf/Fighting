package com.example.kotlincoroutine.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.kotlincorountine.R
import com.example.kotlincorountine.databinding.FragmentHomeBinding
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


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

        mBinding.apply {

            btnDownload.setOnClickListener {
                GlobalScope.launch {
                    Log.d("hf","onClick")
                    "abc".substring(10)
                }
                // findNavController().navigate(R.id.action_homeFragment_to_downloadFragment)
            }

            btnRoom.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_userFragment)
            }
        }
    }
}