package com.example.newstart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.Nullable;

import com.example.newstart.databinding.ActivityAnimBinding;

public class AnimActivity extends BaseActivity {
    private ActivityAnimBinding mBinding;

    // 动画的分类：视图动画（帧动画、补间动画）、属性动画

    // 1、帧动画 xml
//        final AnimationDrawable animationDrawable = new AnimationDrawable();
//        animationDrawable.addFrame(getResources().getDrawable(R.mipmap.item1),100);
//        animationDrawable.addFrame(getResources().getDrawable(R.mipmap.item2),100);
//        animationDrawable.addFrame(getResources().getDrawable(R.mipmap.item3),100);
//        animationDrawable.addFrame(getResources().getDrawable(R.mipmap.item4),100);
//        animationDrawable.addFrame(getResources().getDrawable(R.mipmap.item5),100);
//        animationDrawable.setOneShot(false);
//        mIv.setBackgroundDrawable(animationDrawable);
//        animationDrawable.start();

    // 2、补间动画  scale 缩放  alpha 渐变  translate 平移  rotate 旋转
    // xml  parent
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityAnimBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        addListener();
    }

    private void addListener() {
        mBinding.btn.setOnClickListener(v -> {
            Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.rotate_anim);
//                animation.setDuration(2000);
            //
//                animation.setFillBefore(true);
//                animation.setFillEnabled(true);
//                animation.setFillAfter(true);
//                //
//                animation.setRepeatCount(Animation.INFINITE);  //无限循环
//                animation.setRepeatMode(Animation.REVERSE);
//                //
//                animation.setInterpolator(new BounceInterpolator());
            mBinding.iv.startAnimation(animation);
        });
    }


    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
//        AnimationDrawable drawable = (AnimationDrawable) mIv.getBackground();
//        drawable.start();
    }

    public static void actionStart(Context context) {
        context.startActivity(new Intent(context, AnimActivity.class));
    }
}