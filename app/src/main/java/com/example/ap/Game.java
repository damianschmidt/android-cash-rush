package com.example.ap;

import android.os.Handler;
import android.widget.TextView;

public class Game {
    private TextView gameLabel;
    private Player player;
    private Scene scene;
    private Hud hud;
    private Handler timerHandler;
    private Runnable timerRunnable;
    private int countdown;
    private int counter = 3;
    private int tick = 0;


    public Game(Player player, Scene scene, int countdown, TextView gameLabel) {
        this.gameLabel = gameLabel;
        this.player = player;
        this.scene = scene;
        this.countdown = countdown;
    }

    public void initializeHud() {
        hud = new Hud(this, scene);
        this.scene.addObject(hud);
    }

    public void start() {
        timerHandler = new Handler();
        timerRunnable = new Runnable() {
            @Override
            public void run() {
                timerHandler.postDelayed(this, 1000 / 60);
                tick += 1;
                if (counter > 0) {
                    gameLabel.setText(String.valueOf(counter));
                    if (tick % 60 == 0) {
                        counter -= 1;
                        tick = 0;
                    }
                } else {
                    scene.invalidate();
                    gameLabel.setText(R.string.collect_coins);
                    if (tick % 60 == 0) {
                        countdown -= 1;
                        if (countdown < 1) {
                            end();
                        }
                    }
                }
            }
        };
        timerHandler.postDelayed(timerRunnable, 0);
    }

    private void end() {
        timerHandler.removeCallbacks(timerRunnable);
        gameLabel.setText(R.string.time_up);
    }

    public int getCountdown() {
        return countdown;
    }

    public Player getPlayer() {
        return player;
    }
}
