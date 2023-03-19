package com.example.newstart.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.animation.LinearInterpolator;
import android.widget.Scroller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.newstart.R;

public class ScrollConstraintLayout extends ConstraintLayout {

    private static final String TAG = ScrollConstraintLayout.class.getSimpleName();

    private Scroller mScroller;

    private int mLastX = 0;

    private int mPreX, mPreY;

    private int outsideWith = 0;

    public ScrollConstraintLayout(@NonNull Context context) {
        this(context, null);
        init(context);
    }

    public ScrollConstraintLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        init(context);
    }

    public ScrollConstraintLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ScrollConstraintLayout);
        outsideWith = (int) array.getDimension(R.styleable.ScrollConstraintLayout_outside_width, 100);
        Log.e(TAG, "outsideWith = " + outsideWith);
        array.recycle();
    }

    private void init(Context context) {
        mScroller = new Scroller(context, new LinearInterpolator());
    }

    public void startScroll(int startX, int dx) {
        mScroller.startScroll(startX, 0, dx, 0);
        invalidate();
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, "onTouchEvent:" + event.getAction());

        int maxLength = outsideWith;

        int scrollX = getScrollX();
        int x = (int) event.getX();
        int newScrollX = scrollX + mLastX - x;

        if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
            mPreX = (int) event.getX();
            mPreY = (int) event.getY();
            return true;
        } else if (event.getActionMasked() == MotionEvent.ACTION_MOVE) {
            int pointerIndex = event.findPointerIndex(0);
            if (pointerIndex == -1) {
                return false;
            }
            int dx = (int) (event.getX() - mPreX);
            int dy = (int) (event.getY() - mPreY);

            if (Math.abs(dx) > Math.abs(dy)) {
                getParent().requestDisallowInterceptTouchEvent(true);
            } else {
                getParent().requestDisallowInterceptTouchEvent(false);
            }

            if (newScrollX < 0) {
                newScrollX = 0;
            } else if (newScrollX > maxLength) {
                newScrollX = maxLength;
            }
            scrollTo(newScrollX, 0);
        } else if (event.getActionMasked() == MotionEvent.ACTION_UP) {
            if (scrollX > maxLength / 2) {
                newScrollX = maxLength;
                if (null != mListener) {
                    mListener.onScrolled(this);
                }
            } else {
                newScrollX = 0;
            }
            startScroll(scrollX, newScrollX - scrollX);
            invalidate();
        }
        mLastX = x;
        return super.onTouchEvent(event);
    }

    private int dip2px(int dip) {
        return (int) (dip * getResources().getDisplayMetrics().density + 0.5f);
    }

    public interface OnScrollListener {
        void onScrolled(ScrollConstraintLayout layout);
    }

    private OnScrollListener mListener;

    public void setListener(OnScrollListener listener) {
        this.mListener = listener;
    }
}
