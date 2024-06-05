package com.example.lifecycles.livedata

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.lifecycle.ViewModelProvider
import com.example.lifecycles.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private lateinit var mBinding: FragmentFirstBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentFirstBinding.inflate(layoutInflater, container, false)
        activity?.let {
            val viewModel = ViewModelProvider(
                it,
                ViewModelProvider.AndroidViewModelFactory(it.application)
            )[LiveViewModel2::class.java]

            viewModel.progress.observe(viewLifecycleOwner) { progress ->
                Log.d(TAG,"observe progress = $progress")
                mBinding.seekBar.progress = progress ?: 0
            }

            mBinding.seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    viewModel.progress.postValue(progress)
                    Log.d(TAG,"onProgressChanged progress = $progress")
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {

                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {

                }
            })
        }

        return mBinding.root
    }

    companion object {
        val TAG: String = FirstFragment::class.java.simpleName
    }
}
