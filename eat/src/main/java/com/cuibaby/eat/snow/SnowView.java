package com.cuibaby.eat.snow;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.AppCompatImageView;

import com.cuibaby.eat.star.Tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class SnowView extends AppCompatImageView {
    //    private final String SNOW = "❄";
//    private final String SNOW = "☀❆★❉❈❀✿❃❁";
    private final String SNOW = "❄";
    private float vX = 2.5f;//风向 >0 右边飘 <0 左边飘
    private float vY = 5f;//下落速度 <0你的雪花要往上飘呀
    private int snowCount = 50;//雪花个数
    private List<SnowBean> snowBeanList = new ArrayList<>();

    private int XB;
    private int YB;

    private Paint paint = new Paint();
    private Timer timer;

    private boolean isStart = false;

    public SnowView(Context context) {
        this(context, null);
    }

    public SnowView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SnowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        XB = Tools.getWindowsWidth(context);
        YB = Tools.getWindowsHeight(context);
        initView();
    }

    private void initView() {
        paint.setAntiAlias(true);
        initSnowData();
    }

    public void start() {
        if (timer == null) {
            timer = new Timer();
        }
        isStart = true;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                if (!isStart) return;
                for (int i = 0; i < snowBeanList.size(); i++) {

                    snowBeanList.get(i).setX(snowBeanList.get(i).getX() + vX);
                    snowBeanList.get(i).setY(snowBeanList.get(i).getY() + vY);

                    if (snowBeanList.get(i).getX() < 0 || snowBeanList.get(i).getX() > XB) {
                        snowBeanList.get(i).setX(getRandomX());
                    }
                    if (snowBeanList.get(i).getY() < 0 || snowBeanList.get(i).getY() > YB) {
                        snowBeanList.get(i).setY(0f);
                    }
                }

                postInvalidate();
            }
        }, 0, 15);
    }

    public void resume() {
        if (timer == null) {
            start();
        }
        isStart = true;
    }

    public void pause() {
        isStart = false;
    }

    public void destroy() {
        isStart = false;
        if (snowBeanList != null) {
            snowBeanList.clear();
        }
        invalidate();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    private void initSnowData() {
        for (int i = 0; i < snowCount; i++) {
            SnowBean bean = new SnowBean();
            bean.setX(getRandomX());
            bean.setY(getRandomY());
            bean.setSize((float) (Math.random() * 50) + 5);
            snowBeanList.add(bean);
        }
    }

    private float getRandomX() {
        return (float) (Math.random() * Tools.getWindowsWidth(getContext()));
    }

    private float getRandomY() {
        return (float) (Math.random() * Tools.getWindowsHeight(getContext()));
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < snowBeanList.size(); i++) {
            SnowBean bean = snowBeanList.get(i);
            paint.setTextSize(bean.getSize());
            paint.setColor(bean.getColor());
            canvas.drawText(SNOW, bean.getX(), bean.getY(), paint);
        }
    }


    private GestureDetector detector = new GestureDetector(getContext(), new MyGestureDetector());
    private boolean isPoint = false;
    private long pointTime = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                pointTime = 0;
//                int pCount = event.getPointerCount();
//                if (pCount >= 2) {
//                    isPoint = true;
//                    pointTime = System.currentTimeMillis();
//                }
//                break;
//            case MotionEvent.ACTION_MOVE:
//                break;
//            case MotionEvent.ACTION_UP:
//                isPoint = false;
//                pointTime = 0;
//                break;
//        }

//        return super.onTouchEvent(event);
        return detector.onTouchEvent(event);
    }

    private class MyGestureDetector implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return false;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            return false;
        }
    }
}
