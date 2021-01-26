package com.example.ap;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {
    EditText name;
    RadioGroup colorPicker;
    DatabaseHelper databaseHelper;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        databaseHelper = new DatabaseHelper(SettingsActivity.this);
        name = findViewById(R.id.playerNameInput);
        String playerName = ((databaseHelper.getPlayerName().equals("Player")) ? "" : databaseHelper.getPlayerName());
        name.setText(playerName);
        colorPicker = findViewById(R.id.colorPickerGroup);
        RadioButton radioButton;
        switch (databaseHelper.getPlayerColor()) {
            case Color.GREEN:
                radioButton = findViewById(R.id.radioGreenColor);
                radioButton.setChecked(true);
                break;

            case Color.BLUE:
                radioButton = findViewById(R.id.radioBlueColor);
                radioButton.setChecked(true);
                break;


            case Color.RED:
                radioButton = findViewById(R.id.radioRedColor);
                radioButton.setChecked(true);
                break;

            case Color.MAGENTA:
                radioButton = findViewById(R.id.radioMagentaColor);
                radioButton.setChecked(true);
                break;

            case Color.CYAN:
                radioButton = findViewById(R.id.radioAquaColor);
                radioButton.setChecked(true);
                break;
        }


        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                String newName = s.toString();
                if (newName.equals("")) {
                    newName = "Player";
                }
                databaseHelper.updatePlayerName(newName);
            }
        });

        colorPicker.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.radioGreenColor:
                    databaseHelper.updatePlayerColor(Color.GREEN);
                    break;

                case R.id.radioBlueColor:
                    databaseHelper.updatePlayerColor(Color.BLUE);
                    break;


                case R.id.radioRedColor:
                    databaseHelper.updatePlayerColor(Color.RED);
                    break;

                case R.id.radioMagentaColor:
                    databaseHelper.updatePlayerColor(Color.MAGENTA);
                    break;

                case R.id.radioAquaColor:
                    databaseHelper.updatePlayerColor(Color.CYAN);
                    break;
            }
        });
    }
}