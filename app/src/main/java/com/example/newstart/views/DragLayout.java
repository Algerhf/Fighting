package com.example.newstart.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.customview.widget.ViewDragHelper;

import com.example.newstart.R;

public class DragLayout extends LinearLayout {

    private static final String TAG = DragLayout.class.getSimpleName();

    private ViewDragHelper mViewDragHelper;

    public DragLayout(Context context) {
        super(context);
        init();
    }

    public DragLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DragLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mViewDragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {

            //

            /**
             *  这个函数主要用于决定拦截 ViewGroup 中的哪个控件的触摸事件
             * @param child 当前用户触摸的子控件的View对象
             * @param pointerId 当前触摸此控件的手指所对应的pointerId
             * @return 返回值，表示是否对这个View进行各种事件的捕捉，这里返回true--表示对所有的View都进行捕捉
             */
            @Override
            public boolean tryCaptureView(@NonNull View child, int pointerId) {
                return true;
            }

            @Override
            public int clampViewPositionHorizontal(@NonNull View child, int left, int dx) {
                return left;
            }

            @Override
            public int clampViewPositionVertical(@NonNull View child, int top, int dy) {
                return top;
            }

            @Override
            public int getViewHorizontalDragRange(@NonNull View child) {
                return 1;
            }

            @Override
            public void onEdgeTouched(int edgeFlags, int pointerId) {
                super.onEdgeTouched(edgeFlags, pointerId);
                Log.e(TAG, "onEdgeTouched  edgeFlags：" + edgeFlags);
            }

            @Override
            public void onEdgeDragStarted(int edgeFlags, int pointerId) {
                super.onEdgeDragStarted(edgeFlags, pointerId);

                // 暂时开启捕获tv3的动作
//                mViewDragHelper.captureChildView(findViewById(R.id.tv3), pointerId);
                Log.e(TAG, "onEdgeDragStarted  edgeFlags：" + edgeFlags);
            }

            @Override
            public boolean onEdgeLock(int edgeFlags) {
                Log.e(TAG, "onEdgeLock  edgeFlags：" + edgeFlags);
                if (edgeFlags == ViewDragHelper.EDGE_LEFT) {
                    return true;
                }
                return super.onEdgeLock(edgeFlags);
            }

            @Override
            public void onViewReleased(@NonNull View releasedChild, float xvel, float yvel) {
//                if(releasedChild.getId()==R.id.tv2){
//                    TextView tv1 = findViewById(R.id.tv1);
////                    mViewDragHelper.smoothSlideViewTo(releasedChild,tv1.getLeft(),tv1.getTop());
//                    mViewDragHelper.settleCapturedViewAt(tv1.getLeft(),tv1.getTop());
//                    invalidate();
//                }
            }
        });

        mViewDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT | ViewDragHelper.EDGE_TOP);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(mViewDragHelper.continueSettling(true)){
            invalidate();
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mViewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
        return true;
    }
}
