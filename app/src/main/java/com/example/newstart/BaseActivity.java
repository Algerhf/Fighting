package com.example.newstart;

import android.content.Context;
import android.os.Bundle;
import android.view.Window;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    public Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getResId());
        mContext = this;

        initView();
        initData();
        initListener();
    }

    protected abstract int getResId();

    public void initView() {

    }

    public void initData() {

    }

    public void initListener() {
        
    }
}
