package com.example.newstart.recycler;

import android.graphics.Rect;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

public class CoverFlowLayoutManager extends RecyclerView.LayoutManager {

    private SparseBooleanArray mHasAttachedItems = new SparseBooleanArray();
    private SparseArray<Rect> mItemRects = new SparseArray<>();

    private int mItemWidth;
    private int mItemHeight;

    private int mTotalWidth;
    private int mSumDx;

    private int mIntervalWidth;

    private int mStartX;

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public boolean canScrollHorizontally() {
        return true;
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {

        // 1、容错处理：当item数目为零时，直接清空屏幕
        if (getItemCount() == 0) {
            detachAndScrapAttachedViews(recycler);
            return;
        }

        // 2、初始化，并将正在显示的所有Item剥离屏幕

        // mItemRects是用来保存所有的item的位置信息的，每次走onLayoutChildren方法都需要重新置空
        mItemRects.clear();

        // 用来记录每一个item是否已经在屏幕上布局，每次走onLayoutChildren方法都需要重新置空
        mHasAttachedItems.clear();

        // 先将屏幕上显示的item都保存到 mAttachedScrap 这个集合中
        detachAndScrapAttachedViews(recycler);

        // 3、将item的位置保存起来

        // 因为每个item的高宽都是一样的，所以测量第一个item,拿到item的高度和宽度
        View childView = recycler.getViewForPosition(0);
        measureChildWithMargins(childView, 0, 0);
        mItemWidth = getDecoratedMeasuredWidth(childView);
        mItemHeight = getDecoratedMeasuredHeight(childView);

        // 两张卡片之间的叠加部分
        mIntervalWidth = getIntervalWidth();

        // 然后计算当前屏幕可以显示出几个item
        // mIntervalWidth是实现卡片叠加时添加的变量，将mItemWidth修改成了mIntervalWidth
        int visibleCount = getHorizontalSpace() / mIntervalWidth;

        // 此参数是为了让第一个item初始时，在屏幕上居中显示
        mStartX = getHorizontalSpace() / 2 - mIntervalWidth;

        // 记录每个item的位置，item的高度不变，宽度需要累加
        int offsetX = 0;
        for (int i = 0; i < getItemCount(); i++) {

            // 卡片并排显示时
//            Rect rect = new Rect(offsetX, 0, offsetX + mItemWidth, mItemHeight);
            // 卡片叠加时
            Rect rect = new Rect(mStartX + offsetX, 0, mStartX + offsetX + mItemWidth, mItemHeight);
            mItemRects.put(i, rect);

            // 所有item初始时都没有进行过布局操作，所以全部赋值为false
            mHasAttachedItems.put(i, false);

            // 卡片并排显示时
//            offsetX += mItemWidth;
            // 卡片叠加时
            offsetX += mIntervalWidth;
        }

        //4、布局
        // 得到当前屏幕移动后的位置信息
        Rect visibleRect = getVisibleArea();

        // 开始布局，只布局visibleCount个
        for (int i = 0; i < visibleCount; i++) {
            insertView(i, visibleRect, recycler, false);
        }

        // 这个参数是来计算整个列表的总宽度,因为item数目很少时，offsetX可能没有占满屏幕，所以应该取offsetX和getHorizontalSpace之间的最大值
        mTotalWidth = Math.max(offsetX, getHorizontalSpace());
    }

    private Rect getVisibleArea() {

        // 从右向左滑动时，dx大于0
        // 从左向右滑动时，dx小于0
        // 这里加上mSumDx的原因是：将屏幕进行滚动，可以更好的跟每个item的位置来判断是否有交集，要理解为什么用加号
        return new Rect(getPaddingLeft() + mSumDx, getPaddingTop(), getWidth() - getPaddingRight() + mSumDx, getHeight() - getPaddingBottom());
    }

    private int getHorizontalSpace() {
        return getWidth() - getPaddingLeft() - getPaddingRight();
    }

    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {

        if (getChildCount() <= 0) {
            return dx;
        }

        // travel是用来记录滑动到最左边或者最右边时，实际应该滚动的距离
        // 比如说手指从左往右滑了100个像素，但是80个像素就能到最左边了，这个时候travel就应该是80，而不是100
        int travel = dx;

        // 累计滚动的距离小于0时，说明已经到最左边了   mSumDx + travel = 0 时刚好滑到最左边，此时travel = -mSumDx
        if (mSumDx + dx < 0) {
            travel = -mSumDx;

            // 累计滚动的距离大于总宽度减去屏幕的宽度（就是从0位置开始到最后一个item显示时的偏移距离），说明已经到最右边了
            // mSumDx + travel =  mTotalWidth - getHorizontalSpace() 时刚好滑到最左边，此时travel = mTotalWidth - mSumDx - getHorizontalSpace()
        }/* else if (mSumDx + dx > mTotalWidth-getHorizontalSpace()) {
            travel = mTotalWidth - mSumDx - getHorizontalSpace();
        }*/ else if (mSumDx + dx > getMaxOffset()) {
            travel = getMaxOffset() - mSumDx;
        }

        // 判断完之后，累加mSumDx
        mSumDx += travel;

        Rect visibleRect = getVisibleArea();

        // 1、回收越界的子View
        for (int i = getChildCount() - 1; i >= 0; i--) {

            View child = getChildAt(i);
            int position = getPosition(child);
            Rect rect = mItemRects.get(position);
            if (!Rect.intersects(rect, visibleRect)) {
                removeAndRecycleView(child, recycler);
                mHasAttachedItems.put(position, false);
            } else {
                layoutDecoratedWithMargins(child, rect.left - mSumDx, rect.top, rect.right - mSumDx, rect.bottom);
                handleChildView(child, rect.left - mStartX - mSumDx);
                mHasAttachedItems.put(position, true);
            }
        }

        // 填充空白区域
        View lastView = getChildAt(getChildCount() - 1);
        View firstView = getChildAt(0);

        if (travel >= 0) {
            // item向左滑动时，左边跑到屏幕外的View将被回收，所以从当前屏幕的第一个View开始进行判断和填充
            int minPos = getPosition(firstView);
            for (int i = minPos; i < getItemCount(); i++) {
                insertView(i, visibleRect, recycler, false);
            }
        } else {
            int maxPos = getPosition(lastView);
            for (int i = maxPos; i >= 0; i--) {
                insertView(i, visibleRect, recycler, true);
            }
        }

        return travel;
    }

    private void insertView(int pos, Rect visibleRect, RecyclerView.Recycler recycler, boolean firstPos) {
        Rect rect = mItemRects.get(pos);
        if (Rect.intersects(visibleRect, rect) && !mHasAttachedItems.get(pos)) {
            View child = recycler.getViewForPosition(pos);
            if (firstPos) {
                addView(child, 0);
            } else {
                addView(child);
            }

            measureChildWithMargins(child, 0, 0);
            layoutDecoratedWithMargins(child, rect.left - mSumDx, rect.top, rect.right - mSumDx, rect.bottom);
            handleChildView(child, rect.left - mStartX - mSumDx);
            mHasAttachedItems.put(pos, true);
        }
    }

    public int getIntervalWidth() {
        return mItemWidth / 2;
    }

    public int getCenterPosition() {
        int pos = mSumDx / getIntervalWidth();
        int more = mSumDx % getIntervalWidth();
        if (more > getIntervalWidth() * 0.5f) {
            pos++;
        }
        return pos;
    }

    public int getFirstVisiblePosition() {
        if (getChildCount() <= 0) {
            return 0;
        }
        View view = getChildAt(0);
        return getPosition(view);
    }

    private void handleChildView(View child, int moveX) {
        float radio = computeScale(moveX);
        float rotation = computeRotationY(moveX);

        child.setScaleX(radio);
        child.setScaleY(radio);

        child.setRotationY(rotation);
    }

    private float M_MAX_ROTATION_Y = 30.0F;

    /**
     * 计算旋转系数
     */
    private float computeRotationY(int x) {
        float rotationY = -M_MAX_ROTATION_Y * x / getIntervalWidth();
        if (Math.abs(rotationY) > M_MAX_ROTATION_Y) {
            if (rotationY > 0) {
                rotationY = M_MAX_ROTATION_Y;
            } else {
                rotationY = -M_MAX_ROTATION_Y;
            }
        }
        return rotationY;
    }

    /**
     * 计算缩放系数
     */
    private float computeScale(int x) {
        float scale = 1 - Math.abs(x * 1.0f / (8f * getIntervalWidth()));
        if (scale < 0) scale = 0;
        if (scale > 1) scale = 1;
        return scale;
    }

    public int getMaxOffset() {
        return (getItemCount() - 1) * getIntervalWidth();
    }


    public double calculateDistance(int velocityX, double distance) {
        int extra = mSumDx % getIntervalWidth();
        double realDistance;
        if (velocityX > 0) {
            if (distance < getIntervalWidth()) {
                realDistance = getIntervalWidth() - extra;
            } else {
                realDistance = distance - distance % getIntervalWidth() - extra;
            }
        } else {
            if (distance < getIntervalWidth()) {
                realDistance = extra;
            } else {
                realDistance = distance - distance % getIntervalWidth() + extra;
            }
        }

        return realDistance;
    }
}