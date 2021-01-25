package com.example.ap;

import java.util.UUID;

public class Player {
    private String id, name;
    private int score, color;

    public Player(String name, int color) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.color = color;
        this.score = 0;
    }

    public void incrementScore() {
        score += 1;
    }

    public int getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}
