package com.example.ap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String RANKING_TABLE = "RANKING_TABLE";
    public static final String COLUMN_RANKING_NAME = "RANKING_NAME";
    public static final String COLUMN_RANKING_POINTS = "RANKING_POINTS";
    public static final String COLUMN_RANKING_ID = "ID";

    public static final String PLAYER_TABLE = "PLAYER_TABLE";
    public static final String COLUMN_PLAYER_NAME = "PLAYER_NAME";
    public static final String COLUMN_PLAYER_COLOR = "PLAYER_COLOR";
    public static final String COLUMN_PLAYER_ID = "ID";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "ranking.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createRankingTableStatement = "CREATE TABLE " + RANKING_TABLE + " (" + COLUMN_RANKING_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_RANKING_NAME + " TEXT, " +
                COLUMN_RANKING_POINTS + " INT)";
        db.execSQL(createRankingTableStatement);

        String createPlayerTableStatement = "CREATE TABLE " + PLAYER_TABLE + " (" + COLUMN_PLAYER_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_PLAYER_NAME + " TEXT, " +
                COLUMN_PLAYER_COLOR + " INT)";
        db.execSQL(createPlayerTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public boolean updatePlayerName() {
        return false;
    }

    public String getPlayerName() {
        return "";
    }

    public void setPlayerName() {
    }

    public boolean updatePlayerColor() {
        return false;
    }

    public int getPlayerColor() {
        return 0;
    }

    public void setPlayerColor() {

    }

    public boolean addOne(RankingRecordModel rankingRecordModel) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_RANKING_NAME, rankingRecordModel.getName());
        cv.put(COLUMN_RANKING_POINTS, rankingRecordModel.getPoints());

        long insert = db.insert(RANKING_TABLE, null, cv);
        return insert != -1;
    }

    public boolean deleteOne(RankingRecordModel rankingRecordModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + RANKING_TABLE + " WHERE " + COLUMN_RANKING_ID + " = " + rankingRecordModel.getId();

        Cursor cursor = db.rawQuery(queryString, null);
        return cursor.moveToFirst();
    }

    public List<RankingRecordModel> getAll() {
        List<RankingRecordModel> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + RANKING_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {
                int playerID = cursor.getInt(0);
                String playerName = cursor.getString(1);
                int playerPoints = cursor.getInt(2);

                RankingRecordModel newPlayer = new RankingRecordModel(playerID, playerName, playerPoints);
                returnList.add(newPlayer);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return returnList;
    }
}