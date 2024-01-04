package com.example.newstart.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class TestScaleView extends View {
    private Paint mPaint;

    public TestScaleView(Context context) {
        super(context);
        init();
    }

    public TestScaleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TestScaleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();

        RectF rectF = new RectF(0, 0, 200, 200);

        Matrix matrix = new Matrix();
        matrix.preTranslate(getWidth() / 2, getHeight() / 2);
        canvas.setMatrix(matrix);
        mPaint.setColor(Color.BLACK);
        canvas.drawRect(rectF, mPaint);

        matrix.preSkew(1.0f, 0, 200, 200);
        canvas.setMatrix(matrix);
        mPaint.setColor(Color.RED);
        canvas.drawRect(rectF, mPaint);

        canvas.drawCircle(0, 0, 5, mPaint);

        canvas.restore();

    }
}
