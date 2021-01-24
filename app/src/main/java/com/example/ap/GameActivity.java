package com.example.ap;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {
    private Scene scene;
    private Game game;
    private Car car;
    private TextView gameLabel;
    private Button gameStartBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        gameLabel = findViewById(R.id.game_label);
        gameStartBtn = findViewById(R.id.game_start_btn);


        scene = findViewById(R.id.scene);
        scene.post(() -> {
            game = new Game(new Player("Damian", Color.BLUE), scene, 10, gameLabel);
            game.initializeHud();
            scene.initializeMap();
            car = new Car(scene.getWidth() - (scene.getBaseBlockSize() * 8), scene.getHeight() - (scene.getBaseBlockSize() * 10), game.getPlayer().getColor(), scene.getBaseBlockSize());
            scene.addObject(car);
            scene.invalidate();
        });

        gameStartBtn.setOnClickListener(v -> {
            game.start();
            gameLabel.setVisibility(View.VISIBLE);
            gameStartBtn.setVisibility(View.GONE);
        });
    }
}