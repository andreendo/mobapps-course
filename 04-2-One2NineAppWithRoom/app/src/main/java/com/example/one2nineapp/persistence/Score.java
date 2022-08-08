package com.example.one2nineapp.persistence;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.DecimalFormat;

@Entity(tableName = "scores")
public class Score {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "playername")
    private String playerName;

    @ColumnInfo(name = "time")
    private double time;

    @ColumnInfo(name = "when")
    private String when;

    public Score(String playerName, double time, String when) {
        this.playerName = playerName;
        this.time = time;
        this.when = when;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.00");
        return playerName + ": " + df.format(time) + "s in " + when;
    }

    public double getTime() {
        return time;
    }

    public int getId() {
        return id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getWhen() {
        return when;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public void setWhen(String when) {
        this.when = when;
    }
}
