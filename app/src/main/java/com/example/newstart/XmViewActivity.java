package com.example.newstart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.newstart.databinding.ActivityXmClockBinding;

public class XmViewActivity extends BaseActivity {
    private ActivityXmClockBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityXmClockBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
    }

    public static void actionStart(Context context) {
        context.startActivity(new Intent(context, XmViewActivity.class));
    }
}