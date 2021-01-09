package com.example.ap;

public class Game {
    private Player player;
    private Scene scene;
    private int countdown;


    public Game(Player player, Scene scene, int countdown) {
        this.player = player;
        this.scene = scene;
        this.countdown = countdown;
    }

    private void initializedHud() {}

    public void start() {}
}
