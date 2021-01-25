package com.example.ap;

public class RankingRecordModel {
    private int id;
    private String name;
    private int points;

    public RankingRecordModel(int id, String name, int points) {
        this.id = id;
        this.name = name;
        this.points = points;
    }

    public RankingRecordModel() {}

    @Override
    public String toString() {
        return "Player: " + name + "          " + "Score: " + points;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
