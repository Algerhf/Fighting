package com.example.newstart.views;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Camera：翻转动画效果
 */
public class Rotate3dAnimation extends Animation {
    private float mFromDegree;
    private float mEndDegree;
    private Camera mCamera;
    private int mCenterX, mCenterY;
    private boolean mReverse;
    private float mDepthZ = 400;

    public Rotate3dAnimation(float fromDegree, float endDegree, boolean reverse) {
        mFromDegree = fromDegree;
        mEndDegree = endDegree;
        mReverse = reverse;
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        mCenterX = width / 2;
        mCenterY = height / 2;
        mCamera = new Camera();
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {

        float degree = mFromDegree + (mEndDegree - mFromDegree) * interpolatedTime;
        mCamera.save();

        float z;
        if (mReverse) {
            z = mDepthZ * interpolatedTime;
        } else {
            z = mDepthZ * (1.0f - interpolatedTime);
        }
        mCamera.translate(0, 0, z);


        Matrix matrix = t.getMatrix();
        mCamera.rotateY(degree);
        mCamera.getMatrix(matrix);
        mCamera.restore();

        matrix.preTranslate(-mCenterX, -mCenterY);
        matrix.postTranslate(mCenterX, mCenterY);

        super.applyTransformation(interpolatedTime, t);
    }
}
