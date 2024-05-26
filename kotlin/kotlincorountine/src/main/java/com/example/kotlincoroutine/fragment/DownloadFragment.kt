package com.example.kotlincoroutine.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.kotlincorountine.databinding.FragmentDownloadBinding
import com.example.kotlincoroutine.download.DownloadManager
import com.example.kotlincoroutine.download.DownloadStatus
import kotlinx.coroutines.launch
import java.io.File

class DownloadFragment : Fragment() {

    val url =
        "https://img2.baidu.com/it/u=3256616248,1972425356&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=1039"

    private val mBinding: FragmentDownloadBinding by lazy {
        FragmentDownloadBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return mBinding.root
    }

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        context?.apply {
            lifecycleScope.launch {
                lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                    val file = File(filesDir.absolutePath + File.separator + "test.webp")
                    if (file.exists()) {
                        file.delete()
                    }
                    DownloadManager.download(
                        url, file
                    ).collect { downloadStatus ->
                        when (downloadStatus) {
                            is DownloadStatus.None -> {
                                Toast.makeText(requireActivity(), "下载失败", Toast.LENGTH_SHORT)
                                    .show()
                            }

                            is DownloadStatus.Progress -> {
                                mBinding.pb.progress = downloadStatus.progress
                                mBinding.tvProgress.text = "${downloadStatus.progress}%"
                            }

                            is DownloadStatus.Error -> {
                                Toast.makeText(requireActivity(), "请求错误", Toast.LENGTH_SHORT)
                                    .show()
                            }

                            is DownloadStatus.Done -> {
                                Toast.makeText(requireActivity(), "下载完成", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    }
                }
            }
        }
    }

}