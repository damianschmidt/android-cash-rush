package com.example.ap;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Car extends CollideObject {
    public Car(int x, int y, int color) {
        super(x, y, 60, 100, color);
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
        canvas.drawRect(x - 4, y + 20, x, y + 20 + 16, paint);
        canvas.drawRect(x + this.getWidth(), y + 20, x + this.getWidth() + 4, y + 20 + 16, paint);
        canvas.drawRect(x - 4, y + 70, x, y + 70 + 16, paint);
        canvas.drawRect(x + this.getWidth(), y + 70, x + this.getWidth() + 4, y + 70 + 16, paint);
    }

    private void drawLights(int x, int y, Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.YELLOW);
        canvas.drawRect(x + 4, y + 4, x + 4 + 16, y + 4 + 8, paint);
        canvas.drawRect(x + 40, y + 4, x + 40 + 16, y + 4 + 8, paint);

        paint.setColor(Color.RED);
        canvas.drawRect(x + 6, y + 90, x + 6 + 12, y + 90 + 6, paint);
        canvas.drawRect(x + 40, y + 90, x + 40 + 12, y + 90 + 6, paint);
    }

    private void drawWindows(int x, int y, Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.argb(200, 255, 255, 255));
        canvas.drawRect(x + 4, y + 30, x + 4 + this.getWidth() - 8, y + 30 + 16, paint);
        canvas.drawRect(x + 4, y + 70, x + 4 + this.getWidth() - 8, y + 70 + 16, paint);
        paint.setColor(Color.argb(50, 0, 0, 0));
        canvas.drawRect(x + 4, y + 46, x + 4 + this.getWidth() - 8, y + 46 + 24, paint);
    }
}
