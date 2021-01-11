package com.example.ap;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Car extends CollideObject {
    public Car(int x, int y, int color) {
        super(x, y, 30, 50, color);
    }

    public void update(Canvas canvas) {
        draw(this.getX(), this.getY(), canvas);
    }

    private void draw(int x, int y, Canvas canvas) {
        drawWheels(x, y, canvas);
        drawBase(x, y, canvas);
        drawLights(x, y, canvas);
        drawWindows(x, y, canvas);
    }

    private void drawBase(int x, int y, Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(this.getColor());
        canvas.drawRect(x, y, x + this.getWidth(), y + this.getHeight(), paint);
    }

    private void drawWheels(int x, int y, Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        canvas.drawRect(x - 2, y + 10, x, y + 10 + 8, paint);
        canvas.drawRect(x + this.getWidth(), y + 10, x + this.getWidth() + 2, y + 10 + 8, paint);
        canvas.drawRect(x - 2, y + 35, x, y + 35 + 8, paint);
        canvas.drawRect(x + this.getWidth(), y + 35, x + this.getWidth() + 2, y + 35 + 8, paint);
    }

    private void drawLights(int x, int y, Canvas canvas) {
        
    }

    private void drawWindows(int x, int y, Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.argb(200, 255, 255, 255));
        canvas.drawRect(x + 2, y + 15, x + 2 + this.getWidth() - 4, y + 15 + 8, paint);
        canvas.drawRect(x + 2, y + 35, x + 2 + this.getWidth() - 4, y + 35 + 6, paint);
        paint.setColor(Color.argb(50, 0, 0, 0));
        canvas.drawRect(x + 2, y + 23, x + 2 + this.getWidth() - 4, y + 23 + 12, paint);
    }
}
