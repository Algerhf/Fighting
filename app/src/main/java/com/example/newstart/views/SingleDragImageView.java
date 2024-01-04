package com.example.newstart.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

/**
 * 多点触控：单手拖动图片
 */
public class SingleDragImageView extends AppCompatImageView {
    private int mLeft, mTop;
    private float mStartX, mStartY;

    public SingleDragImageView(@NonNull Context context) {
        super(context);
    }

    public SingleDragImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SingleDragImageView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mStartX = event.getX();
                mStartY = event.getY();
                mLeft = getLeft();
                mTop = getTop();
                break;
            case MotionEvent.ACTION_MOVE:

                int pointerIndex = event.findPointerIndex(0);
                if (pointerIndex == -1) {
                    return false;
                }
                mLeft = (int) (mLeft + event.getX() - mStartX);
                mTop = (int) (mTop + event.getY() - mStartY);

                layout(mLeft, mTop, mLeft + getWidth(), mTop + getHeight());
                break;
            default:
                break;
        }
        return true;
    }
}
