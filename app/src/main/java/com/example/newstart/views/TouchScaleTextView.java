package com.example.newstart.views;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

/**
 * 多点触控：双指缩放控件
 */
public class TouchScaleTextView extends AppCompatTextView {

    private int   mode;
    private float mOldDist;
    private float mTextSize;

    public TouchScaleTextView(@NonNull Context context) {
        super(context);
    }

    public TouchScaleTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchScaleTextView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (mTextSize == 0) {
            mTextSize = getTextSize();
        }

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                mode = 1;
                mOldDist = 0;
                break;
            case MotionEvent.ACTION_UP:
                mode = 0;
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                mOldDist = space(event);
                mode += 1;
                break;
            case MotionEvent.ACTION_POINTER_UP:
                mode -= 1;
                break;
            case MotionEvent.ACTION_MOVE:
                if (mode >= 2) {
                    float newDist = space(event);
                    if (Math.abs(newDist - mOldDist) > 50) {
                        zoom(newDist / mOldDist);
                        mOldDist = newDist;
                    }
                }
                break;
            default:
                break;
        }

        return true;
    }

    private void zoom(float f) {
        mTextSize *= f;
        setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);
    }

    private float space(MotionEvent event) {
        float x = event.getX(0) - event.getX(0);
        float y = event.getY(0) - event.getY(0);
        return (float) Math.sqrt(x * x + y * y);
    }
}
