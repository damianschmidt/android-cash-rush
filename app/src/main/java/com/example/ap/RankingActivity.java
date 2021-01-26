package com.example.ap;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class RankingActivity extends AppCompatActivity {
    private ListView rankingList;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        rankingList = findViewById(R.id.database_value);
        databaseHelper = new DatabaseHelper(RankingActivity.this);
        ShowPlayersOnListView();
    }

    private void ShowPlayersOnListView() {
        List<RankingRecordModel> records = databaseHelper.getAll();
        records.sort((object1, object2) -> object2.getPoints() - object1.getPoints());
        ArrayAdapter playerArrayAdapter = new ArrayAdapter(RankingActivity.this, android.R.layout.simple_list_item_1, records) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text = (TextView) view.findViewById(android.R.id.text1);
                text.setTextColor(Color.WHITE);
                return view;
            }
        };
        rankingList.setAdapter(playerArrayAdapter);
    }
}