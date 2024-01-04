package com.example.newstart.views;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class RoundDrawable extends Drawable {
    private final Bitmap mBmp;
    private final Paint mPaint;
    private RectF mBounds;

    public RoundDrawable(Bitmap bitmap) {
        mBmp = bitmap;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        canvas.drawRoundRect(mBounds, 50, 50, mPaint);
    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public void setBounds(int left, int top, int right, int bottom) {
        super.setBounds(left, top, right, bottom);
        BitmapShader mBitmapShader = new BitmapShader(Bitmap.createScaledBitmap(mBmp, right - left, bottom - top, true), Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mPaint.setShader(mBitmapShader);
        mBounds = new RectF(left, top, right, bottom);
    }

    @Override
    public int getIntrinsicWidth() {
        return mBmp.getWidth();
    }

    @Override
    public int getIntrinsicHeight() {
        return mBmp.getHeight();
    }
}
