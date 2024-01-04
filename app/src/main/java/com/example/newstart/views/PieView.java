package com.example.newstart.views;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import androidx.annotation.Nullable;

import com.example.newstart.beans.PieBean;

import java.util.ArrayList;
import java.util.List;

public class PieView extends View {
    private final int DEFAULT_DIMENSION = 50;

    private List<PieBean> mList = new ArrayList<>();

    private float mCenterX, mCenterY;
    private RectF mRectF;
    private Paint mPaint;

    private ValueAnimator mValueAnimator;
    private float mValue;

    public PieView(Context context) {
        super(context);
        init();
    }

    public PieView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        init();
    }

    public PieView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int resultWith = widthMode == MeasureSpec.EXACTLY ? widthSize : dip2px(DEFAULT_DIMENSION);
        int resultHeight = heightMode == MeasureSpec.EXACTLY ? heightSize : dip2px(DEFAULT_DIMENSION);

        setMeasuredDimension(resultWith, resultHeight);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        float radius = Math.min(w, h) / 2f;
        mCenterX = w / 2f;
        mCenterY = h / 2f;

        mPaint.setStrokeWidth(radius * 0.3f);

        // 设置圆心位置
        float rectSize = radius - mPaint.getStrokeWidth() / 2f;
        mRectF.set(-rectSize, -rectSize, rectSize, rectSize);
    }

    private void init() {
        mRectF = new RectF();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.translate(mCenterX, mCenterY);

        float startAngle = 0;
        for (int i = 0; i < mList.size(); i++) {
            PieBean pieBean = mList.get(i);
            if (null != pieBean) {
                float sweepAngle = pieBean.getPercentage();

                // 动画核心代码
                float drawAngle = Math.min(sweepAngle, mValue - startAngle);
                if (drawAngle > 0) {
                    mPaint.setColor(pieBean.getColor());
                    canvas.drawArc(mRectF, startAngle, drawAngle, false, mPaint);
                }

                startAngle += sweepAngle;
            }
        }
        canvas.restore();
    }

    public void setData(List<PieBean> list) {
        mList.clear();
        if (null == list || list.size() == 0) {
            return;
        }

        mList.addAll(list);
    }

    public void performAnimation(long duration) {
        if (null != mValueAnimator && mValueAnimator.isRunning()) {
            mValueAnimator.cancel();
        } else {
            mValueAnimator = ValueAnimator.ofFloat(0, 360);
            mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mValue = (float) animation.getAnimatedValue();
                    invalidate();
                }
            });
            mValueAnimator.setInterpolator(new AccelerateInterpolator());
            mValueAnimator.setDuration(duration);
        }

        mValueAnimator.start();

    }


    private int dip2px(int dip) {
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, getResources().getDisplayMetrics()) + 0.5f);
    }
}
