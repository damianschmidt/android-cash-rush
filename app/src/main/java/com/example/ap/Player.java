package com.example.ap;

public class Player {
    private final String name;
    private int score;
    private final int color;

    public Player(String name, int color) {
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
