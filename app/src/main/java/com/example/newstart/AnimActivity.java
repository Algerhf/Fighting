package com.example.newstart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;

public class AnimActivity extends BaseActivity {

    private AppCompatTextView mIv;
    private AppCompatButton mBtn;

    @Override
    protected int getResId() {
        return R.layout.activity_anim;
    }

    @Override
    public void initView() {
        super.initView();

        mIv = findViewById(R.id.iv);
        mBtn = findViewById(R.id.btn);
    }

    @Override
    public void initData() {
        super.initData();
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



    }

    @Override
    public void initListener() {
        super.initListener();
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(mContext,R.anim.rotate_anim);
//                animation.setDuration(2000);
//
////                animation.setFillBefore(true);
////                animation.setFillEnabled(true);
//
////                animation.setFillAfter(true);
//                animation.setRepeatCount(Animation.INFINITE);  //无限循环
////                animation.setRepeatMode(Animation.REVERSE);
//
//                animation.setInterpolator(new BounceInterpolator());

                mIv.startAnimation(animation);
            }
        });
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();

//        AnimationDrawable drawable = (AnimationDrawable) mIv.getBackground();
//
//        drawable.start();
    }

    public static void actionStart(Context context){
        context.startActivity(new Intent(context,AnimActivity.class));
    }
}