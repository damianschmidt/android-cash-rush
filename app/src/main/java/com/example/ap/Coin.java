package com.example.ap;

import android.graphics.Color;

import java.util.concurrent.ThreadLocalRandom;

public class Coin extends CollideObject {
    public Coin(int x, int y, int size) {
        super(x, y, size, size, Color.YELLOW);
    }

    public Coin generateCoin(int sceneWidth, int sceneHeight) {
        int randomNum1 = ThreadLocalRandom.current().nextInt(getWidth(), sceneWidth - getWidth());
        int randomNum2 = ThreadLocalRandom.current().nextInt(getWidth(), sceneHeight - getWidth());
        return new Coin(randomNum1, randomNum2, getWidth());
    }
}
