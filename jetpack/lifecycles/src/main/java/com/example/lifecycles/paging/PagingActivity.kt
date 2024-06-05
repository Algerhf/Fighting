package com.example.lifecycles.paging

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lifecycles.R
import com.example.lifecycles.databinding.ActivityPagingBinding

class PagingActivity : AppCompatActivity() {

    private val mBinding:ActivityPagingBinding by lazy {
        ActivityPagingBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
    }

}