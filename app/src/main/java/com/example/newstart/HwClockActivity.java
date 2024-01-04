package com.example.newstart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.newstart.databinding.ActivityHwClockBinding;

public class HwClockActivity extends BaseActivity {
    private ActivityHwClockBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityHwClockBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        mBinding.hwClock.post(() -> mBinding.hwClock.performAnimation());
    }

    public static void actionStart(Context context){
        context.startActivity(new Intent(context,HwClockActivity.class));
    }
}