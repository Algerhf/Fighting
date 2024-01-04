package com.example.newstart.views;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

import com.example.newstart.R;

import java.util.Calendar;
import java.util.Locale;

public class HwClockView extends View {
    private Paint mClockPaint;
    private Paint mTextPaint;
    private int mTextSize;
    private int mTextColor;

    private Bitmap mClockBitmap, mClockMaskBitmap;

    private RectF mClockRectF;

    private PorterDuffXfermode mPorterDuffXfermode;

    private int mNowClockDegrees;
    private int mInitClockDegrees;

    private Calendar mCalendar;

    private ValueAnimator mValueAnimator;

    private static final int DEFAULT_TEXT_SIZE = 40;
    private static final long SECOND = 1000;
    private static final long MINUTE = SECOND * 60;

    private String mTimeText;

    public HwClockView(Context context) {
        super(context);
        init(context);
    }

    public HwClockView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        init(context);
    }

    public HwClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.HwClockView);
        mTextSize = array.getDimensionPixelSize(R.styleable.HwClockView_timeTextSize, dip2px(DEFAULT_TEXT_SIZE));
        mTextColor = array.getColor(R.styleable.HwClockView_timeTextColor, Color.RED);
        array.recycle();

        init(context);
    }

    private void init(Context context) {

        setLayerType(LAYER_TYPE_SOFTWARE, null);

        mClockPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mClockBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.clock);
        mClockMaskBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.clock_mask);
        mClockRectF = new RectF();

        mPorterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);

        mTextPaint = new Paint();
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setColor(mTextColor);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setTextAlign(Paint.Align.CENTER);

        mCalendar = Calendar.getInstance();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(widthMeasureSpec);

        int resultWidth = widthMode == MeasureSpec.EXACTLY ? widthSize : mClockBitmap.getWidth();
        int resultHeight = heightMode == MeasureSpec.EXACTLY ? heightSize : mClockBitmap.getHeight();

        setMeasuredDimension(resultWidth, resultHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mClockRectF.set(0, 0, mClockBitmap.getWidth(), mClockBitmap.getHeight());

        canvas.saveLayer(mClockRectF, null);
        canvas.drawBitmap(mClockBitmap, 0, 0, mClockPaint);
        mClockPaint.setXfermode(mPorterDuffXfermode);

        canvas.rotate(mNowClockDegrees, mClockBitmap.getWidth() / 2, mClockBitmap.getHeight() / 2);
        canvas.drawCircle(mClockBitmap.getWidth() / 2, 140, 20, mTextPaint);

        canvas.drawBitmap(mClockMaskBitmap, 0, 0, mClockPaint);
        mClockPaint.setXfermode(null);

        canvas.restore();

        updateTextTime(canvas);

    }

    private void updateTextTime(Canvas canvas) {
        mCalendar.setTimeInMillis(System.currentTimeMillis());
        mTimeText = String.format(Locale.getDefault(), "%02d:%02d:%02d", mCalendar.get(Calendar.HOUR_OF_DAY), mCalendar.get(Calendar.MINUTE), mCalendar.get(Calendar.SECOND));
        canvas.drawText(mTimeText, mClockBitmap.getWidth() / 2, mClockBitmap.getHeight() / 2, mTextPaint);
    }

    public void performAnimation() {
        if (null != mValueAnimator && mValueAnimator.isRunning()) {
            return;
        }

        mValueAnimator = ValueAnimator.ofInt(0, 360);
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mNowClockDegrees = (int) animation.getAnimatedValue() + mInitClockDegrees;
                invalidate();
            }
        });

        mValueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                mCalendar.setTimeInMillis(System.currentTimeMillis());
                mInitClockDegrees = mCalendar.get(Calendar.SECOND) * 6;
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        mValueAnimator.setDuration(MINUTE);
        mValueAnimator.setInterpolator(new LinearInterpolator());
        mValueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mValueAnimator.start();
    }

    private int dip2px(int dip) {
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, getResources().getDisplayMetrics()) + 0.5f);
    }
}
