package com.cuibaby.eat.star;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;

import androidx.appcompat.widget.AppCompatImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class StarView extends AppCompatImageView {
    private static final String TAG = "StarView";

    private Paint paint;
    private Paint paintHead;
    private Paint paintHeadLight;
    private Paint paintHeadLight2;

    private int bw;
    private int bh;

    private double sweep = 340;//角度
    private int count = 100;
    private double pointCount = 100;
    private List<StarBean> list = new ArrayList<>();
    private Timer timer;
    private boolean isStart = false;

    private Timer playTimer;
    private int s = 0;
//    private int currentColor = ColorUtils.getRandomColor();

    public StarView(Context context) {
        this(context, null);
    }

    public StarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        bw = Tools.getWindowsWidth(context);
        bh = Tools.getWindowsHeight(context);

        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        LinearGradient linearGradient = new LinearGradient(
                0f, 0f, 0f, 1000,
                new int[]{Color.TRANSPARENT, Color.RED},
                new float[]{0f, 1f},
                Shader.TileMode.CLAMP
        );
        paint.setShader(linearGradient);

        paintHead = new Paint();
        paintHead.setStyle(Paint.Style.FILL);
        paintHead.setAntiAlias(true);
        paintHead.setColor(Color.WHITE);

        paintHeadLight = new Paint();
        paintHeadLight.setStyle(Paint.Style.FILL);
        paintHeadLight.setAntiAlias(true);
        paintHeadLight.setColor(Color.argb(100, 255, 255, 255));

        paintHeadLight2 = new Paint();
        paintHeadLight2.setStyle(Paint.Style.FILL);
        paintHeadLight2.setAntiAlias(true);
        paintHeadLight2.setColor(Color.argb(30, 255, 255, 255));


        initStar();
        initPoint();

        start();

        //播放组合
        initPlay();
    }

    private void initPlay() {
        playTimer = new Timer();

        s = 0;
        playTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                s++;
                if (s != 0 && s % 2 == 0) {
                    int color = ColorUtils.getRandomColor();
                    for (int i = 0; i < list.size(); i++) {
                        if (!list.get(i).isPoint()) {
                            list.get(i).setColor(color);
                        }
                    }
                }
                if (s >= 1000) {//防止数据过大
                    s = 0;
                }
            }
        }, 0, 1000);
    }

    private void initStar() {
        int color = ColorUtils.getRandomColor();
        for (int i = 0; i < count; i++) {
            StarBean bean = getStarBean();
            bean.setColor(color);
            list.add(bean);
        }
    }

    public StarBean getStarBean() {
        StarBean bean = new StarBean();
//        setBeanXY(bean);
        bean.setX((float) (Math.random() * bw));
        bean.setY((float) (Math.random() * bh));
        bean.setR((float) (Math.random() * 3 + 0.3f));
        return bean;
    }

    public void initPoint() {
        for (int i = 0; i < pointCount; i++) {
            StarBean bean = getStarBean();
            bean.setPoint(true);
            bean.setColor(Color.WHITE);
            bean.setR((float) (Math.random() * 1.5 + 0.1f));
            list.add(bean);
        }
    }

    private void setBeanXY(StarBean bean) {
        if (Math.random() >= 0.5) {
            bean.setX(0);
            bean.setY((float) (Math.random() * bh));
        } else {
            bean.setX((float) (Math.random() * bw));
            bean.setY(0);
        }
    }

    public void addStar(int count) {
        for (int i = 0; i < count; i++) {
            StarBean bean = getStarBean();
            list.add(bean);
        }
    }

    public void addStar(int count, int color) {
        for (int i = 0; i < count; i++) {
            StarBean bean = getStarBean();
            bean.setColor(color);
            list.add(bean);
        }
    }


    public void start() {
        if (timer == null) {
            timer = new Timer();
        }
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                for (int i = 0; i < list.size(); i++) {

                    list.get(i).setX(list.get(i).getX() + list.get(i).getvX());
                    list.get(i).setY(list.get(i).getY() + list.get(i).getvY());

                    if (
                            list.get(i).getX() < 0
                                    || list.get(i).getX() > (bw + Math.sin(sweep) * list.get(i).getL())
                                    || list.get(i).getY() > (bh + Math.cos(sweep) * list.get(i).getL())
                                    || list.get(i).getY() < 0
                    ) {
                        setBeanXY(list.get(i));
//                        list.get(i).setX((float) (Math.random() * bw));
//                        list.get(i).setY((float) (Math.random() * bh));
                    }

                }
                postInvalidate();
            }
        }, 0, 15);
    }

    public void stop() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }

        if (playTimer != null) {
            playTimer.cancel();
            playTimer = null;
        }
    }


    private Path getPath(float x, float y, double sweep, float r, StarBean bean) {
        Path path = new Path();

        float l = bean.getL();
        float startX = (float) (x + r * Math.cos(sweep));
        float startY = (float) (y - r * Math.sin(sweep));
        float twoX = (float) (x - r * Math.cos(sweep));
        float twoY = (float) (y + r * Math.sin(sweep));
        float threeX = (float) (x - l * Math.sin(sweep));
        float threeY = (float) (y - l * Math.cos(sweep));

        path.moveTo(startX, startY);
        path.lineTo(twoX, twoY);
        path.lineTo(threeX, threeY);
        path.lineTo(startX, startY);
        path.close();

        LinearGradient linearGradient = new LinearGradient(
                threeX, threeY, x, y,
                new int[]{Color.TRANSPARENT, bean.getColor()},
                new float[]{0f, 1f},
                Shader.TileMode.CLAMP
        );
        paint.setShader(linearGradient);
        return path;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(TAG,"onDraw called");
        canvas.drawColor(Color.BLACK);

//        path.moveTo(startX, startY);
//        path.lineTo((mStartSize - mEndSize) / 2, Height);
//        path.lineTo(((mStartSize - mEndSize) / 2 + mEndSize), Height);
//        path.lineTo(mStartSize, 0f);
//        path.lineTo(startX, startY);

        for (int i = 0; i < list.size(); i++) {
            StarBean bean = list.get(i);
            if (!bean.isPoint()) {
                Path path = getPath(bean.getX(), bean.getY(), sweep, bean.getR(), bean);
                canvas.drawPath(path, paint);
                canvas.drawCircle(bean.getX(), bean.getY(), bean.getHeadRLight2(), paintHeadLight2);
                canvas.drawCircle(bean.getX(), bean.getY(), bean.getHeadRLight(), paintHeadLight);
                canvas.drawCircle(bean.getX(), bean.getY(), bean.getHeadR(), paintHead);
            } else {
                canvas.drawCircle(bean.getX(), bean.getY(), bean.getR(), paintHead);
            }
        }

    }
}
