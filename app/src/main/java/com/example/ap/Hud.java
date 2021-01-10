package com.example.ap;

import android.graphics.Color;

public class Hud extends BaseObject {
    private Game game;

    public Hud(Game game) {
        super(0, 0, 0, 0, Color.BLACK, 0.0);
        this.game = game;
    }
}
