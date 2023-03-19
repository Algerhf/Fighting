package com.example.newstart.views;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.BounceInterpolator;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

/**
 * Camera：仿小米时钟
 */
public class ClockViewGroup extends RelativeLayout {

    private float  mRotateX;
    private float  mRotateY;
    private Matrix matrix  = new Matrix();
    private Camera mCamera = new Camera();
    private int    mCenterX;
    private int    mCenterY;

    private ObjectAnimator objectAnimator;

    private static final float MAX_ROTATE_DEGREE = 20;

    public ClockViewGroup(Context context) {
        super(context);
    }

    public ClockViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ClockViewGroup(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCenterX = w / 2;
        mCenterY = h / 2;
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {

        mCamera.save();
        mCamera.rotateX(mRotateX);
        mCamera.rotateY(mRotateY);
        mCamera.getMatrix(matrix);
        mCamera.restore();

        matrix.preTranslate(-mCenterX, -mCenterY);
        matrix.postTranslate(mCenterX, mCenterY);

        canvas.save();
        canvas.setMatrix(matrix);
        super.dispatchDraw(canvas);
        canvas.save();
    }

    public void setRotateX(float rotateX) {
        mRotateX = rotateX;
        postInvalidate();
    }

    public void setRotateY(float rotateY) {
        mRotateY = rotateY;
        postInvalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                if (null != objectAnimator && (objectAnimator.isStarted() || objectAnimator.isRunning())) {
                    objectAnimator.cancel();
                }
                rotateCanvasWhenMove(x, y);
                return true;
            case MotionEvent.ACTION_UP:
                startNewSteadyAnim();
                return true;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    private void rotateCanvasWhenMove(float x, float y) {
        float dx = x - mCenterX;
        float dy = y - mCenterY;

        float percentX = dx / (getWidth() / 2);
        float percentY = dy / (getHeight() / 2);

        if (percentX > 1.0f) {
            percentX = 1.0f;
        } else if (percentX < -1.0f) {
            percentX = -1.0f;
        }

        if (percentY > 1.0f) {
            percentY = 1.0f;
        } else if (percentY < -1.0f) {
            percentY = -1.0f;
        }

        mRotateY = percentX * MAX_ROTATE_DEGREE;
        mRotateX = -percentY * MAX_ROTATE_DEGREE;
        postInvalidate();
    }

    public void startNewSteadyAnim() {
        PropertyValuesHolder rotateXHolder = PropertyValuesHolder.ofFloat("rotateX", mRotateX, 0);
        PropertyValuesHolder rotateYHolder = PropertyValuesHolder.ofFloat("rotateY", mRotateY, 0);

        objectAnimator = ObjectAnimator.ofPropertyValuesHolder(this, rotateXHolder, rotateYHolder);
        objectAnimator.setDuration(1000);
        objectAnimator.setInterpolator(new BounceInterpolator());
        objectAnimator.start();
    }
}
