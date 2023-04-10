package com.example.newstart.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringDef;
import androidx.appcompat.widget.AppCompatImageView;

import com.example.newstart.R;

public class GradientImageView extends View {

    private Path mPath;
    private Paint mPaint;

    private RectF mRectF;
    private Rect mBgRect;
    private Rect mSrcRect;
    private Rect mSrcSelectedRect;

    private Rect mSrcDstRect;
    private Rect mSrcSelectedDstRect;

    private PorterDuffXfermode mMode;

    private Bitmap mMaskBitmap;
    private Bitmap mbgBitmap;
    private Bitmap mSrcBitmap;
    private Bitmap mSrcSelectedBitmap;

    private float mBgWidth;
    private float mBgHeight;

    private float mSrcWidth;
    private float mSrcHeight;

    private float mSrcSelectedWidth;
    private float mSrcSelectedHeight;

    @GradientOrientation
    private String mOrientation = GradientOrientation.BOTTOM_TO_TOP;

    private float mGradientPercent = 1.0f;

    private boolean mGradientEnabled = true;


    public GradientImageView(@NonNull Context context) {
        this(context, null);
    }

    public GradientImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GradientImageView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.GradientImageView);
        int bgResourceId = typedArray.getResourceId(R.styleable.GradientImageView_gradient_bg, R.drawable.setting_background);
        mBgWidth = typedArray.getDimension(R.styleable.GradientImageView_gradient_bg_width, 0);
        mBgHeight = typedArray.getDimension(R.styleable.GradientImageView_gradient_bg_height, 0);

        int srcResourceId = typedArray.getResourceId(R.styleable.GradientImageView_gradient_src, R.drawable.wb_auto_normal);
        int srcSelectedResourceId = typedArray.getResourceId(R.styleable.GradientImageView_gradient_src_selected, R.drawable.wb_auto_select);
        mSrcWidth = typedArray.getDimension(R.styleable.GradientImageView_gradient_src_width, 0);
        mSrcHeight = typedArray.getDimension(R.styleable.GradientImageView_gradient_src_height, 0);
        mSrcSelectedWidth = typedArray.getDimension(R.styleable.GradientImageView_gradient_src_selected_width, 0);
        mSrcSelectedHeight = typedArray.getDimension(R.styleable.GradientImageView_gradient_src_selected_height, 0);
        typedArray.recycle();

        mbgBitmap = BitmapFactory.decodeResource(getResources(), bgResourceId);
        mSrcBitmap = BitmapFactory.decodeResource(getResources(), srcResourceId);
        mSrcSelectedBitmap = BitmapFactory.decodeResource(getResources(), srcSelectedResourceId);

        mPath = new Path();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setDither(true);
        mRectF = new RectF();
        mMode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);

        mBgRect = new Rect(0, 0, mbgBitmap.getWidth(), mbgBitmap.getHeight());
        mSrcRect = new Rect(0, 0, mSrcBitmap.getWidth(), mSrcBitmap.getHeight());
        mSrcSelectedRect = new Rect(0, 0, mSrcSelectedBitmap.getWidth(), mSrcSelectedBitmap.getHeight());
    }

    /**
     * 设置内部icon的宽度
     */
    public void setSrcWidth(float width) {
        this.mSrcWidth = width;
        postInvalidate();
    }


    /**
     * 设置内部icon的高度
     */
    public void setSrcHeight(float height) {
        this.mSrcHeight = height;
        postInvalidate();
    }

    /**
     * 设置Background的宽度
     */
    public void setBgWidth(float width) {
        this.mBgWidth = width;
        postInvalidate();
    }


    /**
     * 设置Background的高度
     */
    public void setBgHeight(float height) {
        this.mBgHeight = height;
        postInvalidate();
    }

    /**
     * 设置未选中时的图标
     */
    public void setNormalResourceId(int resourceId) {
        if (resourceId < 0) {
            return;
        }
        mSrcBitmap = BitmapFactory.decodeResource(getResources(), resourceId);
        postInvalidate();
    }

    /**
     * 设置未选中时的图标
     */
    public void setNormalBitmap(Bitmap bitmap) {
        if (bitmap == null) {
            return;
        }
        this.mSrcBitmap = bitmap;
        this.mSrcRect.set(0, 0, mSrcBitmap.getWidth(), mSrcBitmap.getHeight());
        postInvalidate();
    }

    /**
     * 设置选中时的图标
     */
    public void setSelectedResourceId(int resourceId) {
        if (resourceId < 0) {
            return;
        }
        this.mSrcSelectedBitmap = BitmapFactory.decodeResource(getResources(), resourceId);
        this.mSrcSelectedRect.set(0, 0, mSrcSelectedBitmap.getWidth(), mSrcSelectedBitmap.getHeight());
        postInvalidate();
    }

    /**
     * 设置选中时的图标
     */
    public void setSelectedBitmap(Bitmap bitmap) {
        if (bitmap == null) {
            return;
        }
        this.mSrcSelectedBitmap = bitmap;
        this.mSrcSelectedRect.set(0, 0, mSrcSelectedBitmap.getWidth(), mSrcSelectedBitmap.getHeight());
        postInvalidate();
    }

    /**
     * 设置渐变方向
     */
    public void setGradientOrientation(@GradientOrientation String orientation) {
        this.mOrientation = orientation;
        postInvalidate();
    }

    /**
     * 设置渐变百分比，从哪里开始渐变
     */
    public void setGradientPercent(float gradientPercent) {
        this.mGradientPercent = gradientPercent;
        postInvalidate();
    }

    /**
     * 是否开启渐变效果
     */
    public void enableGradient(boolean gradientEnabled) {
        this.mGradientEnabled = gradientEnabled;
        postInvalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(widthMeasureSpec);

        int realWidth;
        int realHeight;
        if (isSelected()) {
            if (mBgWidth > 0 && mSrcSelectedWidth > 0) {
                realWidth = (int) Math.max(mBgWidth, mSrcSelectedWidth);
            } else {
                realWidth = Math.max(mbgBitmap.getWidth(), mSrcSelectedBitmap.getWidth());
            }

            if (mBgHeight > 0 && mSrcSelectedHeight > 0) {
                realHeight = (int) Math.max(mBgHeight, mSrcSelectedHeight);
            } else {
                realHeight = Math.max(mbgBitmap.getHeight(), mSrcSelectedBitmap.getHeight());
            }

        } else {

            if (mBgWidth > 0 && mSrcWidth > 0) {
                realWidth = (int) Math.max(mBgWidth, mSrcWidth);
            } else {
                realWidth = Math.max(mbgBitmap.getWidth(), mSrcBitmap.getWidth());
            }

            if (mBgHeight > 0 && mSrcHeight > 0) {
                realHeight = (int) Math.max(mBgHeight, mSrcHeight);
            } else {
                realHeight = Math.max(mbgBitmap.getHeight(), mSrcBitmap.getHeight());
            }
        }
        int resultWidth = widthMode == MeasureSpec.EXACTLY ? widthSize : realWidth;
        int resultHeight = heightMode == MeasureSpec.EXACTLY ? heightSize : realHeight;

        setMeasuredDimension(resultWidth, resultHeight);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mRectF.set(0, 0, w, h);
        mPath.reset();
        mPath.addCircle((float) (w / 2), (float) (h / 2), (float) getWidth() / 2, Path.Direction.CCW);
        mRectF.set(0, 0, w, h);
        mMaskBitmap = makeSrc(getWidth(), getHeight());

        mSrcDstRect = new Rect((int) (w / 2 - mSrcWidth / 2), (int) (h / 2 - mSrcHeight / 2), (int) (w / 2 + mSrcWidth / 2), (int) (h / 2 + mSrcHeight / 2));
        mSrcSelectedDstRect = new Rect((int) (w / 2 - mSrcSelectedWidth / 2), (int) (h / 2 - mSrcSelectedHeight / 2), (int) (w / 2 + mSrcSelectedWidth / 2), (int) (h / 2 + mSrcSelectedHeight / 2));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.saveLayer(mRectF, null);
        canvas.clipPath(mPath);
        drawBackground(canvas);
        drawSrc(canvas);

        super.onDraw(canvas);
        mPaint.setXfermode(mMode);
        canvas.drawBitmap(mMaskBitmap, 0, 0, mPaint);
        mPaint.setXfermode(null);
        canvas.restore();
    }

    private void drawBackground(Canvas canvas) {
        if (!isSelected()) {
            canvas.drawBitmap(mbgBitmap, mBgRect, mRectF, mPaint);
            if (mGradientEnabled) {
                mPaint.setXfermode(mMode);
                canvas.drawBitmap(mMaskBitmap, 0, 0, mPaint);
                mPaint.setXfermode(null);
            }
        }
    }

    private void drawSrc(Canvas canvas) {
        if (isSelected()) {
            canvas.drawBitmap(mSrcSelectedBitmap, mSrcSelectedRect, mSrcSelectedDstRect, mPaint);
        } else {
            canvas.drawBitmap(mSrcBitmap, mSrcRect, mSrcDstRect, mPaint);
            if (mGradientEnabled) {
                mPaint.setXfermode(mMode);
                canvas.drawBitmap(mMaskBitmap, 0, 0, mPaint);
                mPaint.setXfermode(null);
            }
        }
    }

    Bitmap makeSrc(int w, int h) {
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        LinearGradient linearGradient;
        if (GradientOrientation.BOTTOM_TO_TOP.equals(mOrientation)) {
            linearGradient = new LinearGradient((float) w / 2, h * mGradientPercent, (float) w / 2, 0,
                    0xFFffffff, 0x10ffffff, Shader.TileMode.CLAMP);
        } else {
            linearGradient = new LinearGradient((float) w / 2, h * mGradientPercent, (float) w / 2, 0,
                    0x10ffffff, 0xFFffffff, Shader.TileMode.CLAMP);
        }
        p.setShader(linearGradient);

        c.drawCircle((float) w / 2, (float) h / 2, (float) w / 2, p);
        return bm;
    }

    private int dip2px(float dip) {
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, getResources().getDisplayMetrics()) + 0.5f);
    }

    @StringDef({GradientOrientation.TOP_TO_BOTTOM, GradientOrientation.BOTTOM_TO_TOP})
    public @interface GradientOrientation {
        String TOP_TO_BOTTOM = "TOP_TO_BOTTOM";
        String BOTTOM_TO_TOP = "BOTTOM_TO_TOP";
    }
}
