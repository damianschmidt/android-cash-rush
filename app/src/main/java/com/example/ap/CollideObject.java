package com.example.ap;

public class CollideObject extends BaseObject {
    public CollideObject(int x, int y, int width, int height, int color) {
        super(x, y, width, height, color, 0.0);
    }

    private BaseObject collide(BaseObject object) {
        if (getX() < object.getX() + object.getWidth() &&
            getX() + getWidth() > object.getX() &&
            getY() < object.getY() + object.getHeight() &&
            getHeight() + getY() > object.getY()) {
            return object;
        } else {
            return null;
        }
    }

    public boolean isCollide(BaseObject object) {
        return collide(object) != null;
    }
}
