package com.example.ap;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.UUID;

public class BaseObject {
    private String id;
    private int x, y, width, height, color;
    private double rotation;

    public BaseObject(int x, int y, int width, int height, int color, double rotation) {
        this.id = UUID.randomUUID().toString();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.rotation = rotation;
    }

    public void update(Canvas canvas) {
        this.draw(this.x, this.y, canvas);
    }

    private void draw(int x, int y, Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(this.color);
        canvas.drawRect(x, y, x + this.width, y + this.height, paint);
    }
}