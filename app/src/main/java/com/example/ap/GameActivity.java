package com.example.ap;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewTreeObserver;

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {
    private Scene scene;
    private Game game;
    private Car car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        scene = findViewById(R.id.scene);
        scene.post(() -> {
            game = new Game(new Player("Damian", Color.RED), scene, 30);
            game.initializeHud();
            scene.initializeMap();
            car = new Car(600, 500, game.getPlayer().getColor());
            scene.addObject(car);
            scene.invalidate();
        });


//        game.start();
    }
}