package com.example.newstart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;

import com.example.newstart.views.Rotate3dAnimation;

public class Rotate3dActivity extends AppCompatActivity {

    private boolean isOpen = false;
    private AppCompatButton mBtnOpen;

    private Rotate3dAnimation mOpenAnimation;
    private Rotate3dAnimation mCloseAnimation;
    private LinearLayout mContentRoot;
    private AppCompatImageView mIvLogo;
    private AppCompatImageView mIvLogo2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotate3d);

        mBtnOpen = findViewById(R.id.btn_open);
        mContentRoot = findViewById(R.id.content);
        mIvLogo = findViewById(R.id.iv_logo);
        mIvLogo2 = findViewById(R.id.iv_logo2);

        initOpenAnim();

        initCloseAnim();

        mBtnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOpenAnimation.hasStarted() && !mOpenAnimation.hasEnded()) {
                    return;
                }

                if (mCloseAnimation.hasStarted() && !mCloseAnimation.hasEnded()) {
                    return;
                }

                if (isOpen) {
                    mContentRoot.startAnimation(mOpenAnimation);
                } else {
                    mContentRoot.startAnimation(mCloseAnimation);
                }

                isOpen = !isOpen;
            }
        });
    }

    private void initOpenAnim() {
        mOpenAnimation = new Rotate3dAnimation(-180, -90,true);
        mOpenAnimation.setFillAfter(true);
        mOpenAnimation.setDuration(600);
        mOpenAnimation.setInterpolator(new AccelerateInterpolator());
        mOpenAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mIvLogo.setVisibility(View.GONE);
                mIvLogo2.setVisibility(View.VISIBLE);

                Rotate3dAnimation rotate3dAnimation = new Rotate3dAnimation(-90,0,false);
                rotate3dAnimation.setDuration(600);
                rotate3dAnimation.setFillAfter(true);
                rotate3dAnimation.setInterpolator(new DecelerateInterpolator());
                mContentRoot.startAnimation(rotate3dAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void initCloseAnim() {
        mCloseAnimation = new Rotate3dAnimation(0, -90,true);
        mCloseAnimation.setDuration(600);
        mCloseAnimation.setFillAfter(true);
        mCloseAnimation.setInterpolator(new AccelerateInterpolator());
        mCloseAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mIvLogo.setVisibility(View.VISIBLE);
                mIvLogo2.setVisibility(View.GONE);
                Rotate3dAnimation rotate3dAnimation = new Rotate3dAnimation(-90,-180,false);
                rotate3dAnimation.setDuration(600);
                rotate3dAnimation.setFillAfter(true);
                rotate3dAnimation.setInterpolator(new DecelerateInterpolator());
                mContentRoot.startAnimation(rotate3dAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}