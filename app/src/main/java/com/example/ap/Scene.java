package com.example.ap;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Scene extends View {
    private Walls walls;
    private Coins coins;
    private List<BaseObject> objects;

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
        walls = new Walls(this);
        coins = new Coins(this);
    }

    public void addObject(BaseObject item) {
        objects.add(item);
    }
}
