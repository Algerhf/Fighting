package com.example.newstart.recycler;

import android.content.Context;
import android.hardware.SensorManager;
import android.util.AttributeSet;
import android.view.ViewConfiguration;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerCoverFlowView extends RecyclerView {

    public RecyclerCoverFlowView(@NonNull Context context) {
        super(context);
        init();
    }

    public RecyclerCoverFlowView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RecyclerCoverFlowView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setChildrenDrawingOrderEnabled(true);
    }

    @Override
    protected int getChildDrawingOrder(int childCount, int i) {
        int center = getCoverFlowLayout().getCenterPosition() - getCoverFlowLayout().getFirstVisiblePosition();
        int order;
        if (i == center) {
            order = childCount - 1;
        } else if (i > center) {
            order = center + childCount - 1 - i;
        } else {
            order = i;
        }
        return order;
    }

    public CoverFlowLayoutManager getCoverFlowLayout() {
        return ((CoverFlowLayoutManager) getLayoutManager());
    }

    @Override
    public boolean fling(int velocityX, int velocityY) {
        //最小滑动距离
        int flingX = (int) (velocityX * 0.4f);
        CoverFlowLayoutManager manager = getCoverFlowLayout();
        double distance = getSplineFlingDistance(flingX);
        double newDistance = manager.calculateDistance(velocityX, distance);
        int fixVelocityX = getVelocity(newDistance);
        if (velocityX > 0) {
            flingX = fixVelocityX;
        } else {
            flingX = -fixVelocityX;
        }
        return super.fling(flingX, velocityY);
    }

    private static float DECELERATION_RATE = (float) (Math.log(0.78) / Math.log(0.9));
    private static final float INFLEXION = 0.35f; // Tension lines cross at (INFLEXION, 1)
    private float mPhysicalCoeff;
    // Fling friction
    private float mFlingFriction = ViewConfiguration.getScrollFriction();

    private double getSplineFlingDistance(int velocity) {
        final double l = getSplineDeceleration(velocity);
        final double decelMinusOne = DECELERATION_RATE - 1.0;
        return mFlingFriction * mPhysicalCoeff * Math.exp(DECELERATION_RATE / decelMinusOne * l);
    }

    private int getVelocity(double distance) {
        final double decelMinusOne = DECELERATION_RATE - 1.0;
        double aecel = Math.log(distance / (mFlingFriction * mPhysicalCoeff)) * decelMinusOne / DECELERATION_RATE;
        return Math.abs((int) (Math.exp(aecel) * (mFlingFriction * mPhysicalCoeff) / INFLEXION));
    }

    /**
     * 39.37英寸 = 1米
     * 0.84f 摩擦因子
     */
    private double getSplineDeceleration(int velocity) {
        final float ppi = this.getResources().getDisplayMetrics().density * 160.0f;
        mPhysicalCoeff = SensorManager.GRAVITY_EARTH // g (m/s^2)  重力加速度
                * 39.37f // inch/meter
                * ppi    // pixels per inch 每一英寸的像素
                * 0.84f; // look and feel tuning  外观与感觉调整
        return Math.log(INFLEXION * Math.abs(velocity) / (mFlingFriction * mPhysicalCoeff));
    }
}
