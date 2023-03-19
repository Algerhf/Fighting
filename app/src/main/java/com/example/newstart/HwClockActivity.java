package com.example.newstart;

import android.content.Context;
import android.content.Intent;

import com.example.newstart.views.HwClockView;

public class HwClockActivity extends BaseActivity {

    private HwClockView mHwClockView;

    @Override
    protected int getResId() {
        return R.layout.activity_hw_clock;
    }

    @Override
    public void initView() {
        super.initView();
        mHwClockView = findViewById(R.id.hw_clock);
        mHwClockView.performAnimation();
    }

    public static void actionStart(Context context){
        context.startActivity(new Intent(context,HwClockActivity.class));
    }
}