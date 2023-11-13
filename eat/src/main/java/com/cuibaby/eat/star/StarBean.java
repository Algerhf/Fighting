package com.cuibaby.eat.star;

public class StarBean {

    private float x;
    private float y;

    private float r;

    private int color = ColorUtils.getRandomColor();

    private float l;

    private float headR;
    private float headRLight;
    private float headRLight2;

    private float vX;
    private float vY;

    private boolean isPoint = false;

    public float getL() {
        l = r * 10 + getX() * 0.3f;
        return l;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public float getHeadR() {
        return headR;
    }

    public float getHeadRLight() {
        return headRLight;
    }

    public float getHeadRLight2() {
        return headRLight2;
    }

    public float getvX() {
        return vX;
    }

    public float getvY() {
        return vY;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getR() {

        return r;
    }

    public void setR(float r) {
        this.r = r;
        vX = r * 1.5f;
        vY = r * 2f;
        headR = r + r * 0.3f;
        headRLight = r + r * 2f;
        headRLight2 = headRLight + headRLight * 0.6f;
    }

    public boolean isPoint() {
        return isPoint;
    }

    public void setPoint(boolean point) {
        isPoint = point;
    }
}
