package com.example.newstart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.newstart.databinding.ActivityMultiTouchBinding;

public class MultiTouchActivity extends BaseActivity {

    private ActivityMultiTouchBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMultiTouchBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
    }

    public static void actionStart(Context context) {
        context.startActivity(new Intent(context, MultiTouchActivity.class));
    }
}