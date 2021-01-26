package com.example.ap;

import java.util.concurrent.ThreadLocalRandom;

public class Coins {
    private final Scene scene;

    public Coins(Scene scene) {
        this.scene = scene;
        scene.addObject(generateCoin());
        scene.addObject(generateCoin());
        scene.addObject(generateCoin());
        scene.addObject(generateCoin());
        scene.addObject(generateCoin());
        scene.addObject(generateCoin());
    }

    private Coin generateCoin() {
        int randomNum1 = ThreadLocalRandom.current().nextInt(scene.getBaseBlockSize(), this.scene.getWidth() - scene.getBaseBlockSize());
        int randomNum2 = ThreadLocalRandom.current().nextInt(scene.getBaseBlockSize(), this.scene.getHeight() - scene.getBaseBlockSize());
        return new Coin(randomNum1, randomNum2, scene.getBaseBlockSize());
    }
}
