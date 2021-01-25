package com.example.ap;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.concurrent.ThreadLocalRandom;

public class Coin extends CollideObject {
    public Coin(int x, int y, int size) {
        super(x, y, size, size, Color.YELLOW);
    }

    public void generateCoin(int sceneWidth, int sceneHeight) {
        int randomNum1 = ThreadLocalRandom.current().nextInt(getWidth(), sceneWidth - getWidth());
        int randomNum2 = ThreadLocalRandom.current().nextInt(getWidth(), sceneHeight - getWidth());

        setX(randomNum1);
        setY(randomNum2);
    }

    public void update(Canvas canvas) {
        draw((int) getX(), (int) getY(), canvas);
    }

    private void draw(int x, int y, Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(getColor());
        canvas.drawRect(x, y, x + getWidth(), y + getHeight(), paint);

        paint.setColor(Color.BLACK);
        paint.setTextSize(getWidth() - (int)(0.1 * getWidth()));

        canvas.drawText("$", x + (getWidth() / 4), y + getHeight() - (int)(0.2 * getHeight()), paint);
    }
}
