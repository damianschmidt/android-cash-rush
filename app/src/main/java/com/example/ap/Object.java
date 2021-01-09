package com.example.ap;

import java.util.UUID;

public class Object {
    private String id;
    private int x, y, width, height, color;
    private double rotation;

    public Object(int x, int y, int width, int height, int color, double rotation) {
        this.id = UUID.randomUUID().toString();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.rotation = rotation;
    }

    private void update() {}

    private void draw() {}
}