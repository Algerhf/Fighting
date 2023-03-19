package com.example.newstart.beans;

public class PieBean {

    private int color;
    private float percentage;

    public PieBean(int color, float percentage) {
        this.color = color;
        this.percentage = percentage;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }
}
