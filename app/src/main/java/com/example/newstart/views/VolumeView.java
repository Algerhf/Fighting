package com.example.newstart.views;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.media.AudioManager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

public class VolumeView extends View {

    private Paint mPaint;

    private float mCenterX;
    private float mCenterY;

    private float mBgRadius;
    private float mVolumeRadius;

    private int mBgColor = 0x60000000;
    private int mVolumeBgColor = 0x80000000;

    private float mBorderWidth;

    private int DEFAULT_DIMENSION;

    private RectF mRectF;

    private int mCurVolumeNum;
    private int mMaxVolume = 10;
    private int mUnitVolumeDegree = 360 / mMaxVolume;

    private boolean isVolumeUp;
    private float mValueAnimated;

    private int count = 0;
    private AudioManager mAudioManager;

    public VolumeView(Context context) {
        super(context);
        init(context);
    }

    public VolumeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VolumeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mPaint = new Paint();
        DEFAULT_DIMENSION = dip2px(150);
        mBorderWidth = dip2px(8);

        mRectF = new RectF();

        setVolume(context);
    }

    private void setVolume(Context context) {
        mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        mMaxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        mCurVolumeNum = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        mUnitVolumeDegree = 360 / mMaxVolume;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int resultWith = widthMode == MeasureSpec.EXACTLY ? widthSize : DEFAULT_DIMENSION;
        int resultHeight = heightMode == MeasureSpec.EXACTLY ? heightSize : DEFAULT_DIMENSION;

        setMeasuredDimension(resultWith, resultHeight);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mBgRadius = Math.min(w, h) / 2f;

        mCenterX = w / 2f;
        mCenterY = h / 2f;

        mVolumeRadius = mBgRadius - mBorderWidth;
        mRectF.set(-mVolumeRadius, -mVolumeRadius, mVolumeRadius, mVolumeRadius);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.translate(mCenterX, mCenterY);

        // 画背景
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mBgColor);
        canvas.drawCircle(0, 0, mBgRadius, mPaint);

        // 画音量的背景
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(mVolumeBgColor);
        mPaint.setStrokeWidth(mBorderWidth);
        canvas.drawCircle(0, 0, mVolumeRadius, mPaint);

        // 画音量
        mPaint.setColor(Color.WHITE);

        if (count != 0) {
            if (isVolumeUp) {
                int num = mCurVolumeNum - 1 > 0 ? mCurVolumeNum - 1 : 0;
                canvas.drawArc(mRectF, -90, num * mUnitVolumeDegree + mValueAnimated, false, mPaint);
            } else {
                int num = mCurVolumeNum + 1;
                canvas.drawArc(mRectF, -90, num * mUnitVolumeDegree - mValueAnimated, false, mPaint);
            }
        } else {
            canvas.drawArc(mRectF, -90, mCurVolumeNum * mUnitVolumeDegree, false, mPaint);
        }

        canvas.restore();
    }

    public void volumeUp() {
        count = 1;
        isVolumeUp = true;
        if (mCurVolumeNum < mMaxVolume) {
            mCurVolumeNum++;
            mAudioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE, 0);
            startAnim();
        }
    }

    public void volumeDown() {
        isVolumeUp = false;
        count = 1;
        if (mCurVolumeNum > 0) {
            mCurVolumeNum--;
            mAudioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_LOWER, 0);
            startAnim();
        }
    }

    private void startAnim() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, mUnitVolumeDegree);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mValueAnimated = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.setDuration(300);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.start();
    }

    public int dip2px(int dip) {
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, getResources().getDisplayMetrics()) + 0.5f);
    }
}
