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

    public boolean isCollideSAT(BaseObject object) {
        Point p1 = new Point(getX(), getY());
        Point p2 = new Point(getX() + getWidth(), getY());
        Point p3 = new Point(getX(), getY() + getHeight());
        Point p4 = new Point(getX() + getWidth(), getY() + getHeight());

        p2.rotate(getRotation(), p1.getX(), p1.getY());
        p3.rotate(getRotation(), p1.getX(), p1.getY());
        p4.rotate(getRotation(), p1.getX(), p1.getY());

        double xMyObjectMax = Math.max(Math.max(p1.getX(), p2.getX()), Math.max(p3.getX(), p4.getX()));
        double xMyObjectMin = Math.min(Math.min(p1.getX(), p2.getX()), Math.min(p3.getX(), p4.getX()));
        double yMyObjectMax = Math.max(Math.max(p1.getY(), p2.getY()), Math.max(p3.getY(), p4.getY()));
        double yMyObjectMin = Math.min(Math.min(p1.getY(), p2.getY()), Math.min(p3.getY(), p4.getY()));

        double xOtherObjectMax = object.getX() + object.getWidth();
        double xOtherObjectMin = object.getX();
        double yOtherObjectMax = object.getY() + object.getHeight();
        double yOtherObjectMin = object.getY();

        boolean isOverlapOnX;
        boolean isOverlapOnY;

        isOverlapOnX = !(xMyObjectMax < xOtherObjectMin) && !(xOtherObjectMax < xMyObjectMin);
        isOverlapOnY = !(yMyObjectMax < yOtherObjectMin) && !(yOtherObjectMax < yMyObjectMin);

        return (isOverlapOnX && isOverlapOnY);
    }

    public boolean isCollide(BaseObject object) {
        return collide(object) != null;
    }
}
