package com.example.ap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;

import androidx.annotation.Nullable;

import java.util.ArrayList;
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
        super(context, "game.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createPlayerTableStatement = "CREATE TABLE IF NOT EXISTS " + PLAYER_TABLE +
                " (" + COLUMN_PLAYER_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_PLAYER_NAME + " TEXT, " +
                COLUMN_PLAYER_COLOR + " INTEGER)";

        String createRankingTableStatement = "CREATE TABLE IF NOT EXISTS " + RANKING_TABLE +
                " (" + COLUMN_RANKING_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_RANKING_NAME + " TEXT, " +
                COLUMN_RANKING_POINTS + " INTEGER)";

        db.execSQL(createPlayerTableStatement);
        db.execSQL(createRankingTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + RANKING_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + PLAYER_TABLE);
        onCreate(db);
    }

    public void dropTables() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + RANKING_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + PLAYER_TABLE);
    }

    public void updatePlayerName(String name) {
        String queryString = "SELECT * FROM " + PLAYER_TABLE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            ContentValues cv = new ContentValues();
            cv.put(COLUMN_PLAYER_NAME, name);
            db.update(PLAYER_TABLE, cv, COLUMN_PLAYER_ID + " = ? ",
                    new String[]{String.valueOf(1)});
        } else {
            addOnePlayerSettings("Player", Color.GREEN);
        }

        cursor.close();
        db.close();
    }

    public boolean addOnePlayerSettings(String name, int color) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_PLAYER_ID, 1);
        cv.put(COLUMN_PLAYER_NAME, name);
        cv.put(COLUMN_PLAYER_COLOR, color);

        long insert = db.insert(PLAYER_TABLE, null, cv);
        return insert != -1;
    }

    public String getPlayerName() {
        String playerName = "Player";

        try {
            String queryString = "SELECT * FROM " + PLAYER_TABLE;
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(queryString, null);

            if (cursor.moveToFirst()) {
                playerName = cursor.getString(1);
            }

            cursor.close();
            db.close();
            return playerName;
        } catch (Exception error) {
            return playerName;
        }
    }

    public void updatePlayerColor(int color) {
        String queryString = "SELECT * FROM " + PLAYER_TABLE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            ContentValues cv = new ContentValues();
            cv.put(COLUMN_PLAYER_COLOR, color);
            db.update(PLAYER_TABLE, cv, COLUMN_PLAYER_ID + " = ? ",
                    new String[]{String.valueOf(1)});
        } else {
            addOnePlayerSettings("Player", color);
        }

        cursor.close();
        db.close();
    }

    public int getPlayerColor() {
        int playerColor = Color.GREEN;
        try {
            String queryString = "SELECT * FROM " + PLAYER_TABLE;
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(queryString, null);

            if (cursor.moveToFirst()) {
                playerColor = cursor.getInt(2);
            }

            cursor.close();
            db.close();
            return playerColor;
        } catch (Exception error) {
            return playerColor;
        }
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

    public boolean deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + RANKING_TABLE;

        Cursor cursor = db.rawQuery(queryString, null);
        return cursor.moveToFirst();
    }

    public boolean deleteAllPlayers() {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + PLAYER_TABLE;

        Cursor cursor = db.rawQuery(queryString, null);
        return cursor.moveToFirst();
    }

    public List<PlayerModel> getAllPlayers() {
        List<PlayerModel> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + PLAYER_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {
                int playerID = cursor.getInt(0);
                String playerName = cursor.getString(1);
                int playerColor = cursor.getInt(2);

                PlayerModel newPlayer = new PlayerModel(playerID, playerName, playerColor);
                returnList.add(newPlayer);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return returnList;
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
