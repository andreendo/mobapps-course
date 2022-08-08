package com.example.one2nineapp.persistence;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface ScoreDao {

    // select all scores and observes for changes
    @Query("SELECT * FROM scores ORDER BY time ASC")
    public Flowable<List<Score>> getAllScores();

    // return when the insertion is completed
    @Insert
    public Completable insertScore(Score score);

    // return the number of rows affected
    @Query("DELETE FROM scores")
    public Single<Integer> deleteAllScores();

    // select all scores
    @Query("SELECT * FROM scores ORDER BY time ASC LIMIT 1")
    public Single<Score> getBestScore();

}
