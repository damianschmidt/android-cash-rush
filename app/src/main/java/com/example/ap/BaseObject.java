package com.example.ap;

import android.graphics.Canvas;
import android.graphics.Paint;

public class BaseObject {
    private double x;
    private double y;
    private final int width;
    private final int height;
    private int color;
    private double rotation;

    public void setX(double x) {
        this.x = x;
    }

    public double getX() {
        return x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return y;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public BaseObject(int x, int y, int width, int height, int color, double rotation) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.rotation = rotation;
    }

    public void update(Canvas canvas) {
        draw((int) this.x, (int) this.y, canvas);
    }

    private void draw(int x, int y, Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(this.color);
        canvas.drawRect(x, y, x + this.width, y + this.height, paint);
    }
}