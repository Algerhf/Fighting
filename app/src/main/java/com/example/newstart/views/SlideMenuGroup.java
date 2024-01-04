package com.example.newstart.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.customview.widget.ViewDragHelper;

public class SlideMenuGroup extends FrameLayout {
    private int mMenuWidth = 500;
    private View mMainView, mMenuView;
    private ViewDragHelper mViewDragHelper;

    public SlideMenuGroup(@NonNull Context context) {
        super(context);
        init(context);
    }

    public SlideMenuGroup(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SlideMenuGroup(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        if (null != mViewDragHelper) {
            return;
        }

        mViewDragHelper = ViewDragHelper.create(this, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(@NonNull View child, int pointerId) {
                return mMainView == child;
            }

            @Override
            public int clampViewPositionHorizontal(@NonNull View child, int left, int dx) {
                if (left > 0) {
                    return Math.min(left, mMenuWidth);
                }
                return 0;
            }

            @Override
            public int getViewHorizontalDragRange(@NonNull View child) {
                return 1;
            }

            @Override
            public void onViewReleased(@NonNull View releasedChild, float xvel, float yvel) {
                super.onViewReleased(releasedChild, xvel, yvel);
                if (releasedChild == mMainView) {
                    if (mMainView.getLeft() < mMenuWidth / 2) {
                        mViewDragHelper.smoothSlideViewTo(mMainView, 0, 0);
                    } else {
                        mViewDragHelper.smoothSlideViewTo(mMainView, mMenuWidth, 0);
                    }
                    invalidate();
                }
            }

            @Override
            public void onViewPositionChanged(@NonNull View changedView, int left, int top, int dx, int dy) {
                super.onViewPositionChanged(changedView, left, top, dx, dy);

                float percent = mMainView.getLeft() / (float) mMenuWidth;
                executeAnimation(percent);
            }
        });
    }

    private void executeAnimation(float percent) {

        mMenuView.setScaleX(0.5f + 0.5f * percent);
        mMenuView.setScaleY(0.5f + 0.5f * percent);

        mMainView.setScaleX(1 - 0.2f * percent);
        mMainView.setScaleY(1 - 0.2f * percent);

        mMenuView.setTranslationX(-mMenuWidth + mMenuWidth * percent);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (null != mViewDragHelper && mViewDragHelper.continueSettling(true)) {
            invalidate();
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (null != mViewDragHelper) {
            return mViewDragHelper.shouldInterceptTouchEvent(ev);
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
        return true;
    }

    public void setView(View mainView, ViewGroup.LayoutParams mainParams, View menuView, ViewGroup.LayoutParams params) {
        this.mMainView = mainView;
        this.mMenuView = menuView;

        addView(mMenuView, params);
        addView(mMainView, mainParams);
    }

    public void closeMenu() {
        if (null != mViewDragHelper) {
            mViewDragHelper.smoothSlideViewTo(mMainView, 0, 0);
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }
}
