package com.example.newstart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.newstart.databinding.ActivityEventConflictBinding;

public class EventConflictActivity extends BaseActivity {
    private ActivityEventConflictBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityEventConflictBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
    }

    public static void actionStart(Context context) {
        context.startActivity(new Intent(context, EventConflictActivity.class));
    }
}