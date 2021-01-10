package com.example.ap;

public class Game {
    private Player player;
    private Scene scene;
    private Hud hud;
    private int countdown;


    public Game(Player player, Scene scene, int countdown) {
        this.player = player;
        this.scene = scene;
        this.countdown = countdown;
    }

    public void initializeHud() {
        hud = new Hud(this);
        this.scene.addObject(hud);
    }

    public void start() {}
}
