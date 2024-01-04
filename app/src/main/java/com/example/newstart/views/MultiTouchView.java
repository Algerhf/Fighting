package com.example.newstart.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class MultiTouchView extends View {
    private boolean hasSecondPoint = false;
    private PointF  mPointF        = new PointF();
    private Paint   mDefaultPaint;

    private static final String TAG = MultiTouchView.class.getSimpleName();

    public MultiTouchView(Context context) {
        super(context);
        init();
    }

    public MultiTouchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MultiTouchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mDefaultPaint = new Paint();
        mDefaultPaint.setAntiAlias(true);
        mDefaultPaint.setColor(Color.WHITE);
        mDefaultPaint.setTextAlign(Paint.Align.CENTER);
        mDefaultPaint.setTextSize(40);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.GREEN);

        if (hasSecondPoint) {
            canvas.drawCircle(mPointF.x, mPointF.y, 100, mDefaultPaint);
        }

        canvas.save();
        canvas.translate(getMeasuredWidth() / 2, getMeasuredHeight() / 2);
        canvas.drawText("追踪第2个按下手指的位置", 0, 0, mDefaultPaint);
        canvas.restore();


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {


        int actionIndex = event.getActionIndex();
        switch (event.getActionMasked()) {

            case MotionEvent.ACTION_POINTER_DOWN:
                if (event.getPointerId(actionIndex) == 1) {
                    Log.e(TAG,"onTouchEvent--> 第二根手指按下了");
                    hasSecondPoint = true;
                    mPointF.set(event.getX(), event.getY());
                }
                break;

            case MotionEvent.ACTION_MOVE:
                try {
                    if (hasSecondPoint) {
                        int pointerIndex = event.findPointerIndex(1);
                        mPointF.set(event.getX(pointerIndex), event.getY(pointerIndex));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    hasSecondPoint = false;
                }
                break;

            case MotionEvent.ACTION_POINTER_UP:
                if (event.getPointerId(actionIndex) == 1) {
                    hasSecondPoint = false;
                    Log.e(TAG,"onTouchEvent--> 第二根手指离开了");
                }
                break;

            case MotionEvent.ACTION_UP:
                hasSecondPoint = false;
                break;

            default:
                break;
        }
        postInvalidate();
        return true;
    }
}
