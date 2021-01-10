package com.example.ap;

import java.util.concurrent.ThreadLocalRandom;

public class Coins {
    private Scene scene;

    public Coins(Scene scene) {
        this.scene = scene;
        scene.addObject(generateCoin());
        scene.addObject(generateCoin());
        scene.addObject(generateCoin());
    }

    private Coin generateCoin() {
        int randomNum1 = ThreadLocalRandom.current().nextInt(20, this.scene.getWidth() - 20);
        int randomNum2 = ThreadLocalRandom.current().nextInt(20, this.scene.getHeight() - 20);
        return new Coin(randomNum1, randomNum2);
    }
}
