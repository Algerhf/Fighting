package com.example.newstart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.newstart.databinding.ActivityVolumeBinding;

public class VolumeActivity extends BaseActivity {
    private ActivityVolumeBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityVolumeBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        addListener();
    }

    private void addListener() {
        mBinding.btn1.setOnClickListener(v -> mBinding.volumeLayout.volumeUp());
        mBinding.btn2.setOnClickListener(v -> mBinding.volumeLayout.volumeDown());
    }

    public static void actionStart(Context context) {
        context.startActivity(new Intent(context, VolumeActivity.class));
    }
}