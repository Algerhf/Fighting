package com.example.newstart;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.example.newstart.databinding.ActivityCustomDrawableBinding;
import com.example.newstart.views.RoundDrawable;

public class CustomDrawableActivity extends BaseActivity {
    private ActivityCustomDrawableBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityCustomDrawableBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        initView();
    }

    private void initView() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.photo1);
        RoundDrawable drawable = new RoundDrawable(bitmap);
        mBinding.image.setImageDrawable(drawable);
    }

    public static void actionStart(Context context) {
        context.startActivity(new Intent(context, CustomDrawableActivity.class));
    }
}