package com.example.newstart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;

import com.example.newstart.databinding.ActivityRotate3dBinding;
import com.example.newstart.views.Rotate3dAnimation;

public class Rotate3dActivity extends BaseActivity {
    private ActivityRotate3dBinding mBinding;
    private boolean isOpen = false;
    private Rotate3dAnimation mOpenAnimation;
    private Rotate3dAnimation mCloseAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityRotate3dBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        initOpenAnim();
        initCloseAnim();
        addListener();
    }

    private void addListener() {
        mBinding.btnOpen.setOnClickListener(v -> {
            if (mOpenAnimation.hasStarted() && !mOpenAnimation.hasEnded()) {
                return;
            }

            if (mCloseAnimation.hasStarted() && !mCloseAnimation.hasEnded()) {
                return;
            }

            if (isOpen) {
                mBinding.content.startAnimation(mOpenAnimation);
            } else {
                mBinding.content.startAnimation(mCloseAnimation);
            }

            isOpen = !isOpen;
        });
    }

    private void initOpenAnim() {
        mOpenAnimation = new Rotate3dAnimation(-180, -90, true);
        mOpenAnimation.setFillAfter(true);
        mOpenAnimation.setDuration(600);
        mOpenAnimation.setInterpolator(new AccelerateInterpolator());
        mOpenAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mBinding.ivLogo.setVisibility(View.GONE);
                mBinding.ivLogo2.setVisibility(View.VISIBLE);

                Rotate3dAnimation rotate3dAnimation = new Rotate3dAnimation(-90, 0, false);
                rotate3dAnimation.setDuration(600);
                rotate3dAnimation.setFillAfter(true);
                rotate3dAnimation.setInterpolator(new DecelerateInterpolator());
                mBinding.content.startAnimation(rotate3dAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void initCloseAnim() {
        mCloseAnimation = new Rotate3dAnimation(0, -90, true);
        mCloseAnimation.setDuration(600);
        mCloseAnimation.setFillAfter(true);
        mCloseAnimation.setInterpolator(new AccelerateInterpolator());
        mCloseAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mBinding.ivLogo.setVisibility(View.VISIBLE);
                mBinding.ivLogo2.setVisibility(View.GONE);
                Rotate3dAnimation rotate3dAnimation = new Rotate3dAnimation(-90, -180, false);
                rotate3dAnimation.setDuration(600);
                rotate3dAnimation.setFillAfter(true);
                rotate3dAnimation.setInterpolator(new DecelerateInterpolator());
                mBinding.content.startAnimation(rotate3dAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public static void actionStart(Context context) {
        context.startActivity(new Intent(context, Rotate3dActivity.class));
    }
}