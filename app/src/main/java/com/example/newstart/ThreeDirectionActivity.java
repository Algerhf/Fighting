package com.example.newstart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.newstart.databinding.ActivityThreeDirectionBinding;

public class ThreeDirectionActivity extends BaseActivity {
    private ActivityThreeDirectionBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityThreeDirectionBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
    }

    public static void actionStart(Context context) {
        context.startActivity(new Intent(context, ThreeDirectionActivity.class));
    }
}