package com.example.ap;

import android.graphics.Color;

public class Walls {
    public Walls(Scene scene) {
        int color = Color.WHITE;
        scene.addObject(new CollideObject(0, 0, scene.getBaseBlockSize(), scene.getHeight(), color));
        scene.addObject(new CollideObject(scene.getBaseBlockSize(), 0, scene.getWidth() - 2 * scene.getBaseBlockSize(), scene.getBaseBlockSize(), color));
        scene.addObject(new CollideObject(scene.getWidth() - scene.getBaseBlockSize(), 0, scene.getBaseBlockSize(), scene.getHeight(), color));
        scene.addObject(new CollideObject(scene.getBaseBlockSize(), scene.getHeight() - scene.getBaseBlockSize(), scene.getWidth() - 2 * scene.getBaseBlockSize(), scene.getBaseBlockSize(), color));
    }
}
