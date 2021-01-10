package com.example.ap;

import android.graphics.Color;

public class Walls {
    private Scene scene;
    private int wallSize = 20;

    public Walls(Scene scene) {
        this.scene = scene;
        scene.addObject(new CollideObject(0, 0, wallSize, scene.getHeight(), Color.RED));
        scene.addObject(new CollideObject(wallSize, 0, scene.getWidth() -  2 * wallSize, wallSize, Color.RED));
        scene.addObject(new CollideObject(scene.getWidth() - wallSize, 0, wallSize, scene.getHeight(), Color.RED));
        scene.addObject(new CollideObject(wallSize, scene.getHeight() - wallSize, scene.getWidth() -  2 * wallSize, wallSize, Color.RED));
    }
}
