package com.example.ap;

public class Point {
    private double x;
    private double y;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void rotate(double rotation, double xRot, double yRot) {
        double x1 = x - xRot;
        double y1 = y - yRot;

        double x2 = x1 * Math.cos(rotation * Math.PI / 180) - y1 * Math.sin(rotation * Math.PI / 180);
        double y2 = x1 * Math.sin(rotation * Math.PI / 180) + y1 * Math.cos(rotation * Math.PI / 180);

        setX(x2 + xRot);
        setY(y2 + yRot);
    }
}
