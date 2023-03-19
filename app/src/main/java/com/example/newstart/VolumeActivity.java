package com.example.newstart;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.newstart.views.VolumeLayout;
import com.example.newstart.views.VolumeView;

public class VolumeActivity extends BaseActivity {

    private VolumeLayout mVolumeLayout;

    @Override
    protected int getResId() {
        return R.layout.activity_volume;
    }

    @Override
    public void initView() {
        super.initView();
        mVolumeLayout = findViewById(R.id.volume_layout);
    }

    @Override
    public void initListener() {
        super.initListener();
        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVolumeLayout.volumeUp();
            }
        });

        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVolumeLayout.volumeDown();
            }
        });
    }

    public static void actionStart(Context context){
        context.startActivity(new Intent(context,VolumeActivity.class));
    }
}