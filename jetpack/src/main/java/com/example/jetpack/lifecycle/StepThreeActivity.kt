package com.example.jetpack.lifecycle

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpack.databinding.ActivityStepThreeBinding

class StepThreeActivity : AppCompatActivity() {

    // private val mViewModel by viewModels(ThreeViewModel::class.java)

    private val mBinding by lazy{
        ActivityStepThreeBinding.inflate(layoutInflater)
    }

    private val mConn by lazy{
        object: ServiceConnection{
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {

            }

            override fun onServiceDisconnected(name: ComponentName?) {

            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)

        mBinding.btnStart.setOnClickListener {
            bindService(Intent(this,MyLocationService::class.java),mConn,Context.BIND_AUTO_CREATE);
        }

        mBinding.btnStop.setOnClickListener {
            unbindService(mConn)
        }
    }
}