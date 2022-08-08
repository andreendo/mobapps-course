package com.example.one2nineapp.persistence;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Score.class}, version = 1)
public abstract class ScoreDatabase extends RoomDatabase {

    private static volatile ScoreDatabase INSTANCE;

    public abstract ScoreDao scoreDao();

    public static ScoreDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (ScoreDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ScoreDatabase.class, "score.db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}