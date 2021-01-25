package com.example.ap;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {
    private SensorManager sensorManager;
    private Sensor sensorAccelerometer;
    private Scene scene;
    private Game game;
    private Car car;
    private TextView gameLabel;
    private Button gameStartBtn;
    DatabaseHelper databaseHelper;

    private final SensorEventListener accelerometerSensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            if (car != null) {
                car.setControlX(event.values[0]);
                car.setControlY(event.values[1]);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        gameLabel = findViewById(R.id.game_label);
        gameStartBtn = findViewById(R.id.game_start_btn);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        databaseHelper = new DatabaseHelper(GameActivity.this);

        scene = findViewById(R.id.scene);
        scene.post(() -> {
            game = new Game(new Player("Damian", Color.BLUE), scene, 15, gameLabel, databaseHelper);
            game.initializeHud();
            scene.initializeMap();
            car = new Car(scene.getWidth() - (scene.getBaseBlockSize() * 8), scene.getHeight() - (scene.getBaseBlockSize() * 10), game.getPlayer(), scene);
            scene.addObject(car);
            scene.invalidate();
        });

        gameStartBtn.setOnClickListener(v -> {
            game.start();
            gameLabel.setVisibility(View.VISIBLE);
            gameStartBtn.setVisibility(View.GONE);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (sensorAccelerometer != null) {
            sensorManager.registerListener(accelerometerSensorListener, sensorAccelerometer, SensorManager.SENSOR_DELAY_GAME);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (sensorAccelerometer != null) {
            sensorManager.unregisterListener(accelerometerSensorListener);
        }
    }
}
