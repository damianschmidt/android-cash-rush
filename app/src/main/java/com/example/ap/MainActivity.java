package com.example.ap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnPlay = findViewById(R.id.play_btn);
        Button btnRanking = findViewById(R.id.ranking_btn);
        Button btnSettings = findViewById(R.id.settings_btn);

        btnPlay.setOnClickListener(v -> openView(GameActivity.class));
        btnRanking.setOnClickListener(v -> openView(RankingActivity.class));
        btnSettings.setOnClickListener(v -> openView(SettingsActivity.class));
    }

    private void openView(Class activityClass) {
        Intent intent = new Intent(MainActivity.this, activityClass);
        startActivity(intent);
    }
}