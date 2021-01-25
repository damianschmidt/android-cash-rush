package com.example.ap;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Car extends CollideObject {
    private Scene scene;
    private Player player;
    private double speed = 0;
    private double acceleration = 1.08;
    private double initialSpeed = 0.5;
    private double speedDecay = 0.96;
    private double maxSpeed = 10;
    private double maxBackSpeed = 4;
    private double breakPower = 0.05;
    private double rotationStep = 4;

    private double controlX = 0;
    private double controlY = 0;

    public void setControlX(double controlX) {
        this.controlX = controlX;
    }

    public void setControlY(double controlY) {
        this.controlY = controlY;
    }

    private boolean isMoving() {
        return !(speed > -initialSpeed && speed < initialSpeed);
    }

    private boolean isReturning() {
        return !(speed > -initialSpeed);
    }

    public Car(int x, int y, Player player, Scene scene) {
        super(x, y, 3 * scene.getBaseBlockSize(), 5 * scene.getBaseBlockSize(), player.getColor());
        this.player = player;
        this.scene = scene;
    }

    public void update(Canvas canvas) {
        for (BaseObject object : new ArrayList<>(scene.getObjects())) {
            if ((object instanceof CollideObject) && !(object instanceof Car)) {
                if (isCollideSAT(object)) {
                    if (object instanceof Coin) {
                        player.incrementScore();
                        List<BaseObject> newObjects = scene.getObjects();
                        newObjects.remove(object);
                        scene.setObjects(newObjects);
                    } else {
                        speed = -speed;
                    }
                }
            }
        }

        if (!isMoving()) {
            speed = 0;
        } else {
            speed *= speedDecay;
        }

        setX(getX() + getAxisX());
        setY(getY() + getAxisY());
        draw((int) getX(), (int) getY(), canvas);
        control();
    }

    private double getAxisX() {
        return Math.sin(getRotation() * (Math.PI / 180)) * speed;
    }

    private double getAxisY() {
        return Math.cos(getRotation() * (Math.PI / 180)) * speed * -1;
    }

    private void accelerate() {
        if (speed < maxSpeed) {
            if (speed < 0) {
                speed += breakPower;
            } else if (speed == 0) {
                speed = initialSpeed;
            } else {
                this.speed *= acceleration;
            }
        }
    }

    private void decelerate() {
        if (speed > 0) {
            speed -= breakPower;
        } else if (speed == 0) {
            speed = -initialSpeed;
        } else {
            if (Math.abs(speed) < maxBackSpeed) {
                speed *= acceleration;
            }
        }
    }

    private void momentum() {
        speed *= speedDecay;
    }

    private void turnLeft() {
        if (isMoving()) {
            setRotation((getRotation() - Math.abs(controlX * 2) * (speed / maxSpeed)) % 360);
        }
    }

    private void turnRight() {
        if (isMoving()) {
            setRotation((getRotation() + Math.abs(controlX * 2) * (speed / maxSpeed)) % 360);
        }
    }

    private void control() {
        if (controlY < -0.3) {
            accelerate();
        } else if (controlY > -0.3) {
            decelerate();
        } else {
            momentum();
        }

        if (controlX > 0.3) {
            turnLeft();
        } else if (controlX < -0.3) {
            turnRight();
        }
    }

    private void draw(int x, int y, Canvas canvas) {
        canvas.save();
        canvas.rotate((float) getRotation(), x, y);
        drawWheels(x, y, canvas);
        drawBase(x, y, canvas);
        drawLights(x, y, canvas);
        drawWindows(x, y, canvas);
        canvas.restore();
    }

    private void drawBase(int x, int y, Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(this.getColor());
        canvas.drawRect(x, y, x + this.getWidth(), y + this.getHeight(), paint);
    }

    private void drawWheels(int x, int y, Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        int wheelWidth = ((scene.getBaseBlockSize() / 5 > 0) ? scene.getBaseBlockSize() / 5 : 1);
        int wheelHeight = ((scene.getBaseBlockSize() - (scene.getBaseBlockSize() / 5) > 0) ? scene.getBaseBlockSize() - (scene.getBaseBlockSize() / 5) : 1);

        canvas.drawRect(x - wheelWidth, y + scene.getBaseBlockSize(), x, y + scene.getBaseBlockSize() + wheelHeight, paint);
        canvas.drawRect(x + getWidth(), y + scene.getBaseBlockSize(), x + getWidth() + wheelWidth, y + scene.getBaseBlockSize() + wheelHeight, paint);
        canvas.drawRect(x - wheelWidth, y + getHeight() - (scene.getBaseBlockSize() / 2) - wheelHeight, x, y + getHeight() - (scene.getBaseBlockSize() / 2), paint);
        canvas.drawRect(x + getWidth(), y + getHeight() - (scene.getBaseBlockSize() / 2) - wheelHeight, x + getWidth() + wheelWidth, y + getHeight() - (scene.getBaseBlockSize() / 2), paint);
    }

    private void drawLights(int x, int y, Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.YELLOW);
        int frontLightEdge = ((scene.getBaseBlockSize() / 5 > 0) ? scene.getBaseBlockSize() / 5 : 1);
        int frontLightWidth = scene.getBaseBlockSize() - frontLightEdge;
        int frontLightHeight = 2 * frontLightEdge;

        int backLightEdge = ((scene.getBaseBlockSize() / 4 > 0) ? scene.getBaseBlockSize() / 4 : 1);
        int backLightWidth = scene.getBaseBlockSize() - (2 * backLightEdge);
        int backLightHeight = ((scene.getBaseBlockSize() / 4 > 0) ? scene.getBaseBlockSize() / 4 : 1);

        canvas.drawRect(x + frontLightEdge, y + frontLightEdge, x + frontLightEdge + frontLightWidth, y + frontLightEdge + frontLightHeight, paint);
        canvas.drawRect(x + getWidth() - frontLightWidth - frontLightEdge, y + frontLightEdge, x + getWidth() - frontLightEdge, y + frontLightEdge + frontLightHeight, paint);

        if (isReturning()) {
            paint.setColor(Color.rgb(255, 179, 179));
        } else {
            paint.setColor(Color.RED);
        }
        canvas.drawRect(x + backLightEdge, y + getHeight() - backLightEdge - backLightHeight, x + backLightEdge + backLightWidth, y + getHeight() - backLightEdge, paint);
        canvas.drawRect(x + getWidth() - backLightEdge - backLightWidth, y + getHeight() - backLightEdge - backLightHeight,
                x + getWidth() - backLightEdge, y + getHeight() - backLightEdge, paint);
    }

    private void drawWindows(int x, int y, Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.argb(200, 255, 255, 255));
        int windowsSideEdge = ((scene.getBaseBlockSize() / 5 > 0) ? scene.getBaseBlockSize() / 5 : 2);
        int windowsVerticalEdge = (int) (1.5 * scene.getBaseBlockSize());
        int windowsHeight = scene.getBaseBlockSize() - windowsSideEdge;
        int roofHeight = windowsHeight + windowsSideEdge;

        canvas.drawRect(x + windowsSideEdge, y + windowsVerticalEdge, x + getWidth() - windowsSideEdge, y + windowsVerticalEdge + windowsHeight, paint);
        canvas.drawRect(x + windowsSideEdge, y + getHeight() - windowsVerticalEdge, x + getWidth() - windowsSideEdge, y + getHeight() - windowsVerticalEdge + windowsHeight, paint);

        paint.setColor(Color.argb(50, 0, 0, 0));
        canvas.drawRect(x + windowsSideEdge, y + windowsVerticalEdge + windowsHeight, x + getWidth() - windowsSideEdge, y + windowsVerticalEdge + windowsHeight + roofHeight, paint);
    }
}
