package com.example.ap;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Car extends CollideObject {
    int baseSize;

    public Car(int x, int y, int color, int baseSize) {
        super(x, y, 3 * baseSize, 5 * baseSize, color);
        this.baseSize = baseSize;
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
        int wheelWidth = ((baseSize / 5 > 0) ? baseSize / 5 : 1);
        int wheelHeight = ((baseSize - (baseSize / 5) > 0) ? baseSize - (baseSize / 5) : 1);

        canvas.drawRect(x - wheelWidth, y + baseSize, x, y + baseSize + wheelHeight, paint);
        canvas.drawRect(x + this.getWidth(), y + baseSize, x + this.getWidth() + wheelWidth, y + baseSize + wheelHeight, paint);
        canvas.drawRect(x - wheelWidth, y + this.getHeight() - (baseSize / 2) - wheelHeight, x, y + this.getHeight() - (baseSize / 2), paint);
        canvas.drawRect(x + this.getWidth(), y + this.getHeight() - (baseSize / 2) - wheelHeight, x + this.getWidth() + wheelWidth, y + this.getHeight() - (baseSize / 2), paint);
    }

    private void drawLights(int x, int y, Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.YELLOW);
        int frontLightEdge = ((baseSize / 5 > 0) ? baseSize / 5 : 1);
        int frontLightWidth = baseSize - frontLightEdge;
        int frontLightHeight = 2 * frontLightEdge;

        int backLightEdge = ((baseSize / 4 > 0) ? baseSize / 4 : 1);
        int backLightWidth = baseSize - (2 * backLightEdge);
        int backLightHeight = ((baseSize / 4 > 0) ? baseSize / 4 : 1);

        canvas.drawRect(x + frontLightEdge, y + frontLightEdge, x + frontLightEdge + frontLightWidth, y + frontLightEdge + frontLightHeight, paint);
        canvas.drawRect(x + this.getWidth() - frontLightWidth - frontLightEdge, y + frontLightEdge, x + this.getWidth() - frontLightEdge, y + frontLightEdge + frontLightHeight, paint);

        paint.setColor(Color.RED);
        canvas.drawRect(x + backLightEdge, y + this.getHeight() - backLightEdge - backLightHeight, x + backLightEdge + backLightWidth, y + this.getHeight() - backLightEdge, paint);
        canvas.drawRect(x + this.getWidth() - backLightEdge - backLightWidth, y + this.getHeight() - backLightEdge - backLightHeight,
                x + this.getWidth() - backLightEdge, y + this.getHeight() - backLightEdge, paint);
    }

    private void drawWindows(int x, int y, Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.argb(200, 255, 255, 255));
        int windowsSideEdge = ((baseSize / 5 > 0) ? baseSize / 5 : 2);
        int windowsVerticalEdge = (int)(1.5 * baseSize);
        int windowsHeight = baseSize - windowsSideEdge;
        int roofHeight = windowsHeight + windowsSideEdge;

        canvas.drawRect(x + windowsSideEdge, y + windowsVerticalEdge, x + this.getWidth() - windowsSideEdge, y + windowsVerticalEdge + windowsHeight, paint);
        canvas.drawRect(x + windowsSideEdge, y + this.getHeight() - windowsVerticalEdge, x + this.getWidth() - windowsSideEdge, y + this.getHeight() - windowsVerticalEdge + windowsHeight, paint);

        paint.setColor(Color.argb(50, 0, 0, 0));
        canvas.drawRect(x + windowsSideEdge, y + windowsVerticalEdge + windowsHeight, x + this.getWidth() - windowsSideEdge, y + windowsVerticalEdge + windowsHeight + roofHeight, paint);
    }
}
