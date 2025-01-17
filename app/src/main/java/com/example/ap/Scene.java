package com.example.ap;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class Scene extends View {
    private final List<BaseObject> objects;
    private int baseBlockSize = 20;

    public List<BaseObject> getObjects() {
        return objects;
    }

    public int getBaseBlockSize() {
        return baseBlockSize;
    }

    public void setBaseBlockSize(int baseBlockSize) {
        this.baseBlockSize = baseBlockSize;
    }

    public Scene(Context context) {
        super(context);
        objects = new ArrayList<BaseObject>();
    }

    public Scene(Context context, AttributeSet attrs) {
        super(context, attrs);
        objects = new ArrayList<BaseObject>();
    }

    public Scene(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        objects = new ArrayList<BaseObject>();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.GRAY);
        for (BaseObject object : this.objects) {
            object.update(canvas);
        }
    }

    public void initializeMap() {
        new Walls(this);
        new Coins(this);
    }

    public void addObject(BaseObject item) {
        objects.add(item);
    }
}
