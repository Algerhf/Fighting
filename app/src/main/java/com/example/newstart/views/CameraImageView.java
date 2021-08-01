package com.example.newstart.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.example.newstart.R;

public class CameraImageView extends AppCompatImageView {

    private int mProgress;
    private Camera mCamera;
    private Bitmap mBitmap;
    private Paint mPaint;
    private Matrix matrix = new Matrix();

    public CameraImageView(@NonNull Context context) {
        super(context);
        init();
    }

    public CameraImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CameraImageView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.photo1);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mCamera = new Camera();
    }

    public void setProgress(int progress) {
        mProgress = progress;
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        mCamera.save();
        canvas.save();

        mPaint.setAlpha(100);
        canvas.drawBitmap(mBitmap, 0, 0, mPaint);


        mCamera.rotateZ(mProgress);

//        int centerX = getWidth()/2/72;
//        int centerY = getHeight()/2/72;
//
//        mCamera.setLocation(centerX,-centerY,mCamera.getLocationZ());

        mCamera.getMatrix(matrix);

        canvas.setMatrix(matrix);

//        mCamera.translate(0,0,-2*mProgress);
//        mCamera.applyToCanvas(canvas);
        mCamera.restore();
        super.onDraw(canvas);

        canvas.restore();
    }
}
